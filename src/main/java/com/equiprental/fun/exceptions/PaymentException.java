package com.equiprental.fun.exceptions;

public class PaymentException {

    public static class PaymentCreationException extends AbstractAppException {
        public PaymentCreationException(String message) {
            super(message);
        }
    }

    public static class PaymentProcessingException extends AbstractAppException {
        public PaymentProcessingException(String message, Throwable cause) {
            super(message);
            this.initCause(cause);
        }
    }
}