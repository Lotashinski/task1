package com.github.lotashinski.service.property;

import io.github.cdimascio.dotenv.Dotenv;

public class PropertyReader {

    private static final Dotenv dotenv;

    static {
        dotenv = Dotenv
                .load();
    }


    public static String get(String key){
        return dotenv.get(key);
    }

    public static String get(String key, String defaultValue){
        return dotenv.get(key, defaultValue);
    }
}
