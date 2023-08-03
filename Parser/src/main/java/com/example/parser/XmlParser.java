package com.example.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlParser {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("C:/Users/017904/eclipse-workspace/Parser/src/main/java/com/example/parser/input.xml");
       
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(xmlFile);
            
            Element rootElement = document.getDocumentElement();

            NodeList userList = rootElement.getElementsByTagName("user");
            System.out.println(userList.getLength());

            for (int i = 0; i < userList.getLength(); i++) {
                Node userNode = userList.item(i);
                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
//                	System.out.println(Node.ELEMENT_NODE);
                    Element userElement = (Element) userNode;

                    String name = userElement.getElementsByTagName("name").item(0).getTextContent();
                    int age = Integer.parseInt(userElement.getElementsByTagName("age").item(0).getTextContent());
                    boolean isStudent = Boolean.parseBoolean(userElement.getElementsByTagName("isStudent").item(0).getTextContent());
                    String dept = userElement.getElementsByTagName("dept").item(0).getTextContent();
                    
                    System.out.println("Name: " + name);
                    System.out.println("Age: " + age);
                    System.out.println("Is Student: " + isStudent);
                    System.out.println("Dept:" + dept);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
