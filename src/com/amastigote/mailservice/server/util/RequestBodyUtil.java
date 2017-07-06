package com.amastigote.mailservice.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestBodyUtil {

    public static Map<String, String> parse(InputStream inputStream) throws IOException {
        String reqBody = read(inputStream);
        Map<String, String> reqParams = new HashMap<>();
        Arrays.asList(reqBody.split("&")).forEach(e -> {
                    String[] keyValue = e.split("=");
                    reqParams.put(keyValue[0], keyValue[1]);
                }
        );
        return reqParams;
    }

    private static String read(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String temp;
        while ((temp = bufferedReader.readLine()) != null)
            stringBuilder.append(temp);
        return URLDecoder.decode(stringBuilder.toString(), "UTF-8");
    }
}
