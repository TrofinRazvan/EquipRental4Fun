package com.equiprental.fun.exceptions;

public class CredentialException {

    public static class PasswordException extends AbstractAppException {
        public PasswordException(String errorMessage) {
            super(errorMessage);
        }
    }
}