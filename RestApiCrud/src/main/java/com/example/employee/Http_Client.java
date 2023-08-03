package com.example.employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Http_Client {

    public static void main(String[] args) throws IOException {
        // Test GET Request
        String getRequestUrl = "http://localhost:8000/hello";
        String getResponse = sendHttpGetRequest(getRequestUrl);
        System.out.println("GET Response: " + getResponse);

        // Test POST Request
        String postRequestUrl = "http://localhost:8000/hello";
        String postRequestBody = "This is the POST request body.";
        String postResponse = sendHttpPostRequest(postRequestUrl, postRequestBody);
        System.out.println("POST Response: " + postResponse);
    }

    private static String sendHttpGetRequest(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        conn.disconnect();

        return response.toString();
    }

    private static String sendHttpPostRequest(String url, String requestBody) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        conn.disconnect();

        return response.toString();
    }
}
