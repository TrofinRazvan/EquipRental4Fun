package com.equiprental.fun.configs;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PaypalConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    public PayPalHttpClient getPaypalClient() {
        String clientId = environment.getProperty("paypal.client.id");
        String clientSecret = environment.getProperty("paypal.client.secret");
        return new PayPalHttpClient(new PayPalEnvironment.Sandbox(clientId, clientSecret));
    }
}