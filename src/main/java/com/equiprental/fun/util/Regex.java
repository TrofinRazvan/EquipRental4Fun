package com.equiprental.fun.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Regex {

    public static final String NAME_SURNAME = "^[a-zA-ZăâîșțĂÂÎȘȚ -']{3,30}$";
    public static final String CNP = "^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d$";
    public static final String EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    public static final String PHONE_NUMBER = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";
    public static final String DATE = "^[0-9]{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    public static final String STREET = "^[a-zA-Z0-9ăâîșțĂÂÎȘȚ -]{2,50}$";
    public static final String BUILDING_NUMBER = "^([0-9]+(?:[a-z]{0,1})){1,5}$";
    public static final String CITY = "^[a-zA-ZăâîșțĂÂÎȘȚ -]{2,70}$";
}