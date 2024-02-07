package com.equiprental.fun.services.utils;

import org.springframework.stereotype.Service;

@Service
public class StringUtilsService {

    public String capitalizeAndRemoveWhiteSpaces(String name) {
        return name.substring(0, 1).toUpperCase().replace(" ","") + name.substring(1).toLowerCase().replace(" ","");
    }
}