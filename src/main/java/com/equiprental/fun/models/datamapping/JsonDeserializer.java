package com.equiprental.fun.models.datamapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonDeserializer {

    public static <T> T deserializeResponse(String jsonResponse, Class<T> targetType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, targetType);
    }
}