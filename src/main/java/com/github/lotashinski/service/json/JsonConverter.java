package com.github.lotashinski.service.json;

import com.github.lotashinski.service.exception.RequestBodyConvertException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public final class JsonConverter {

    public static HttpServletResponse toJsonResponse(Object obj, HttpServletResponse response) throws IOException {
        Gson gson = configureGson();
        String body = gson.toJson(obj);

        response.setContentType("application/json;charset=UTF-8");
        try (Writer writer = response.getWriter()) {
            writer.write(body);
        }

        return response;
    }


    public static <T> T fromJsonRequest(HttpServletRequest request, Class<T> tClass) throws IOException {
        InputStream inputStream = request.getInputStream();
        if (null == inputStream) {
            throw new RequestBodyConvertException("Request body is empty. \"" + tClass.getSimpleName() + "\" type expected.");
        }

        Gson gson = configureGson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return gson.fromJson(reader, tClass);
    }


    private static Gson configureGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }
}
