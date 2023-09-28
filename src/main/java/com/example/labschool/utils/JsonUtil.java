package com.example.labschool.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {

    private JsonUtil() {}

    public static String objToJson(Object obj) {
        ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(obj);
        } catch (Exception e) {
            return "";
        }
    }

}
