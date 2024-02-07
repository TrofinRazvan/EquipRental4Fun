package com.equiprental.fun.exceptions;

import com.equiprental.fun.util.UserRole;
import org.apache.commons.lang3.StringUtils;

public class AlreadyExistException {

    public static class CnpAlreadyExistException extends AbstractAppException {
        public CnpAlreadyExistException(String CNP, UserRole userRole) {
            super(StringUtils.capitalize(userRole.getName()) + " with CNP: " + CNP + " already exists in system.");
        }
    }

    public static class PhoneNumberAlreadyExistException extends AbstractAppException {
        public PhoneNumberAlreadyExistException(String phoneNumber, UserRole userRole) {
            super(StringUtils.capitalize(userRole.getName()) + " with phone number " + phoneNumber + " already exits in system.");
        }
    }

    public static class EmailAddressAlreadyExistException extends AbstractAppException {
        public EmailAddressAlreadyExistException(String email, UserRole userRole) {
            super(StringUtils.capitalize(userRole.getName()) + " with email " + email + " already exist in system.");
        }
    }
}