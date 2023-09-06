package com.example.xcale.util;

import com.example.xcale.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConvertJsonToBean {

    public static <T> T getBeanFromJsonFile(String path, Class<T> valueType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);

        return objectMapper.readValue(file, valueType);
    }

    public static <T> List<T> getListFromJsonFile(String path, Class<T> elementType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);

        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
    }


}
