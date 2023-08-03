package com.example.parser;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JsonParser {
    public static void main(String[] args) {
        try {
            Object obj = new JSONParser().parse(new FileReader("C:/Users/017904/eclipse-workspace/Parser/src/main/java/com/example/parser/input.json"));

            JSONArray jsonArray = (JSONArray) obj;

            for (Object jsonElement : jsonArray) {

                JSONObject jsonObject = (JSONObject) jsonElement;

                String firstName = (String) jsonObject.get("firstName");
                String lastName = (String) jsonObject.get("lastName");
                long age = (long) jsonObject.get("age");

                JSONObject address = (JSONObject) jsonObject.get("address");
                String streetAddress = (String) address.get("streetAddress");
                String city = (String) address.get("city");
                String state = (String) address.get("state");
                long postalCode = (long) address.get("postalCode");


                JSONArray phoneNumbers = (JSONArray) jsonObject.get("phoneNumbers");

                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Age: " + age);
                System.out.println("Street Address: " + streetAddress);
                System.out.println("City: " + city);
                System.out.println("State: " + state);
                System.out.println("Postal Code: " + postalCode);

                for (Object phoneNumber : phoneNumbers) {
                    JSONObject phoneObject = (JSONObject) phoneNumber;
                    String type = (String) phoneObject.get("type");
                    String number = (String) phoneObject.get("number");

                    System.out.println("Phone Type: " + type);
                    System.out.println("Phone Number: " + number);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
