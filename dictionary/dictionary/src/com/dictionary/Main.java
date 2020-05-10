package com.dictionary;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите слово: ");

        while (scanner.hasNextLine()) {
            var foundedArticles = findArticles(scanner.nextLine());

            if (!foundedArticles.isEmpty()) {
                foundedArticles.forEach((k, v) -> System.out.format("Статья: %s\nОписание: %s\n", k, v));
            }
            else {
                System.out.println("Статья не найдена!");
            }
            System.out.println("Введите слово: ");
        }
    }

    private static Map<String, String> findArticles(String term) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("src/com/dictionary/dictionary.xml"));
        NodeList articleNodes = document.getElementsByTagName("article");

        Map<String, String> foundedArticles = new HashMap<>();
        for (int i = 0; i < articleNodes.getLength(); i++) {
            Node articleNode = articleNodes.item(i);
            NodeList articleNodeChilds = articleNode.getChildNodes();

            var key = articleNodeChilds.item(1).getTextContent();
            if (key.toLowerCase().contains(term.toLowerCase())) {
                foundedArticles.put(key, articleNodeChilds.item(3).getTextContent());
            }
        }

        return foundedArticles;
    }
}
