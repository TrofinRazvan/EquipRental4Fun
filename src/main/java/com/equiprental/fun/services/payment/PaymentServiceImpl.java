package com.equiprental.fun.services.payment;

import com.equiprental.fun.configs.PaypalConfiguration;
import com.equiprental.fun.exceptions.NotFoundException;
import com.equiprental.fun.exceptions.PaymentException;
import com.equiprental.fun.models.datamapping.JsonDeserializer;
import com.equiprental.fun.models.entity.Customer;
import com.equiprental.fun.models.entity.Payment;
import com.equiprental.fun.repositories.CustomerRepository;
import com.equiprental.fun.repositories.PaymentRepository;
import com.equiprental.fun.services.rent.RentService;
import com.equiprental.fun.services.utils.Constants;
import com.equiprental.fun.util.Currency;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RentService rentService;
    private final CustomerRepository customerRepository;
    private final PaypalConfiguration paypalConfiguration;

    public PaymentServiceImpl(PaymentRepository paymentRepository, RentService rentService, CustomerRepository customerRepository,
                              PaypalConfiguration paypalConfiguration) {
        this.paymentRepository = paymentRepository;
        this.rentService = rentService;
        this.customerRepository = customerRepository;
        this.paypalConfiguration = paypalConfiguration;
    }

    @Override
    @Transactional
    public Payment createPayment(Long customerId) {
        try {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new NotFoundException.UserNotFoundException(customerId));

            double total = rentService.calculateTotalRentPriceForCustomer(customerId);
            String responseBody = executePayPalRequest(total);

            if (responseBody != null) {
                if (isResponseValid(responseBody)) {
                    Order order = JsonDeserializer.deserializeResponse(responseBody, Order.class);
                    if (Constants.PAYMENT_STATUS_COMPLETED.equals(order.status())) {
                        Payment payment = createPaymentFromOrder(order, customer, total);
                        return payment;
                    } else {
                        throw new PaymentException.PaymentCreationException("Payment status is not completed");
                    }
                } else {
                    throw new PaymentException.PaymentCreationException("Invalid JSON response from PayPal");
                }
            } else {
                throw new PaymentException.PaymentCreationException("Failed to create payment. Response from PayPal is empty.");
            }

        } catch (IOException ioException) {
            log.error("Error processing payment: " + ioException.getMessage());
            throw new PaymentException.PaymentProcessingException("Error processing payment.", ioException);
        }
    }

    private String executePayPalRequest(double total) throws IOException {
        PayPalHttpClient payPalHttpClient = paypalConfiguration.getPaypalClient();
        String url = Constants.PAYPAL_CHECKOUT_ORDERS_URL;
        String body = "{" +
                "  \"intent\": \"CAPTURE\"," +
                "  \"purchase_units\": [" +
                "    {" +
                "      \"reference_id\": \"PUHF\"," +
                "      \"amount\": {" +
                "        \"currency_code\": \"" + Currency.EURO.code + "\"," +
                "        \"value\": \"" + total + "\"" +
                "      }" +
                "    }" +
                "  ]," +
                "  \"application_context\": {" +
                "    \"return_url\": \"" + Constants.SUCCESS_URL + "\"," +
                "    \"cancel_url\": \"" + Constants.CANCEL_URL + "\"" +
                "  }" +
                "}";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("accept", "application/json");
        httpPost.setHeader("content-type", "application/json");
        httpPost.setHeader("accept-language", "en_US");
        httpPost.setHeader("authorization", Constants.PAYPAL_AUTHORIZATION_TOKEN);
        StringEntity requestEntity = new StringEntity(body);
        httpPost.setEntity(requestEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int responseCode = response.getStatusLine().getStatusCode();
        if (responseCode == HttpStatus.SC_OK) {
            return EntityUtils.toString(response.getEntity());
        } else {
            log.error("Failed to execute PayPal request. HTTP error code: " + responseCode);
            return null;
        }
    }

    private Payment createPaymentFromOrder(Order order, Customer customer, double total) {
        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setTotal(total);
        payment.setCurrency(Currency.EURO);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod("PayPal");
        payment.setStatus("COMPLETED");
        payment.setRedirectUrl(order.purchaseUnits().get(0).payments().captures().get(0).links().get(0).href());
        paymentRepository.save(payment);
        return payment;
    }

    private boolean isResponseValid(String jsonResponse) {
        try {
            Order order = JsonDeserializer.deserializeResponse(jsonResponse, Order.class);
            return order != null && order.status() != null;
        } catch (IOException e) {
            return false;
        }
    }
}