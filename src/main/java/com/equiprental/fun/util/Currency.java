package com.equiprental.fun.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {

    LEI("RON"),
    EURO("EUR");

    public final String code;
}