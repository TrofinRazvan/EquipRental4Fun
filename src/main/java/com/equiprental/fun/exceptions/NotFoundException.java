package com.equiprental.fun.exceptions;

import lombok.extern.slf4j.Slf4j;

public class NotFoundException {

    @Slf4j
    public static class UserNotFoundException extends AbstractAppException {
        public UserNotFoundException(Long userId) {
            super("User with ID: " + userId + " has not been found.");
        }
    }
}