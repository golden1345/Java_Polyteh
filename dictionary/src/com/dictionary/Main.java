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
        //Scanner - класс для считывания данных с консоли
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите слово: ");

        //Бесконечно читаем ввод пользователя с консоли
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
        //Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = documentBuilder.parse(new File("src/com/dictionary/dictionary.xml"));

        //Список элементов с тэгом article
        NodeList articleNodes = document.getElementsByTagName("article");

        //создаем хэш мапу для хранения ключа и его значения
        Map<String, String> foundedArticles = new HashMap<>();

        for (int i = 0; i < articleNodes.getLength(); i++) {
            //итерируем по статьям
            Node articleNode = articleNodes.item(i);
            //достаем child nodes из article node(это будут key и description)
            NodeList articleNodeChilds = articleNode.getChildNodes();

            //получаем ключ словаря
            var key = articleNodeChilds.item(1).getTextContent();

            //если ключ в словаре содержит подстроку введенную пользователем, то добавляем найденную статью в хэшмапу
            if (key.toLowerCase().contains(term.toLowerCase())) {
                foundedArticles.put(key, articleNodeChilds.item(3).getTextContent());
            }
        }

        return foundedArticles;
    }
}
