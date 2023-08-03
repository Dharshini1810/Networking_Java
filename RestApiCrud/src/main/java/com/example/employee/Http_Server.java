package com.example.employee;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;


public class Http_Server {
	

    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/hello", new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server is running on port " + port);
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String requestMethod = httpExchange.getRequestMethod();
            if ("GET".equals(requestMethod)) {
                handleGetRequest(httpExchange);
            } else if ("POST".equals(requestMethod)) {
                handlePostRequest(httpExchange);
            } else {
                sendResponse(httpExchange, 405, "Method Not Allowed");
            }
        }

        private void handleGetRequest(HttpExchange httpExchange) throws IOException {
            String response = "Hello, this is the server response for GET request!";
            sendResponse(httpExchange, 200, response);
        }

        private void handlePostRequest(HttpExchange httpExchange) throws IOException {
            // Get the request body from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8));
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            reader.close();

            // Process the request body or do anything you need with it
            String response = "Received POST request with body: " + requestBody.toString();
            sendResponse(httpExchange, 200, response);
        }

        private void sendResponse(HttpExchange httpExchange, int statusCode, String response) throws IOException {
            httpExchange.sendResponseHeaders(statusCode, response.length());
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }
    }
}
