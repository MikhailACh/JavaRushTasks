package com.javarush.task.task33.task3309;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws SAXException, JAXBException, ParserConfigurationException {
        StringWriter writer = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        // задаем маршаллеру свойство форматированного вывода (с табами)
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // получаем объект Document
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        // парсер не будет анализировать документ XML на соответствие DTD (схеме описания документа)
        f.setValidating(false);
        // фабрика объектов Document
        DocumentBuilder builder = f.newDocumentBuilder();
        Document doc = builder.newDocument();
        // маршализируем объект XML в объект типа Document
        marshaller.marshal(obj, doc);
        // атрибут «standalone» определяет, подключить ли документу описания разметки извне, ссылается ли на другие DTD-файлы, описывающие, ЧТО документ может содержать
        doc.setXmlStandalone(false);
        // оправляем в метод Document doc в качестве корневого узла и самого исходного объекта типа Document
        textToCDATA(doc, doc);

        // список нодов, содержащих искомый тег
        NodeList nodeList = doc.getElementsByTagName(tagName);
        // итерируем по списку
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node oldChild = nodeList.item(i);
            Node newChild = doc.createComment(comment);
            // вставляем перед существующим узлом новый узел (comment)
            // при этом вызываем метод вставки на узле-родителе того узла, перед которым вставляем новый узел,
            // т.е. явно указываем между какими узлами подразумевается вставка
            oldChild.getParentNode().insertBefore(newChild, oldChild);
        }

        // перобразуем исходное дерево (Document) в результирующее дерево
        Transformer tf = null;
        try {
            tf = TransformerFactory.newInstance().newTransformer();
            // устанавливаем кодировку результриующего дерева
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            // и дополнительые пробелы отступов
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            // трансформируем (источник преобразования - объектная модель DOM, хранилище для результирующего потока в виде исходящего потока - байтового или символьного)
            tf.transform(new DOMSource(doc), new StreamResult(writer));
        } catch (TransformerConfigurationException tce) {
            System.out.println("Ошибка конфигуратора трансформации!");
        } catch (TransformerException te) {
            System.out.println("Ошибка процесса трансформации!");
        }

        return writer.toString();
    }

    public static void textToCDATA(Node root, Document doc) {
        // получаем список детей корневого (на данном этапе) узла
        NodeList children = root.getChildNodes();

        // итерируем по списку детей
        for (int i = 0; i < children.getLength(); i++) {
            Node n = children.item(i);
            // если тип нода - текстовой
            if (n.getNodeType() == Node.TEXT_NODE) {
                // берем текстовое ссодержимое нода
                String nodeContent = root.getTextContent();
                // и, если оно сооветствует признакам CDATA (регулярке)
                if (nodeContent.matches(".*[<>&'\"].*")){
                    // создаем новый дочерний нод с секцией CDATA в данном документе
                    Node newChild = doc.createCDATASection(n.getNodeValue());
                    // и заменяем у родительского нода этим новым ребенком старого ребенка
                    root.replaceChild(newChild, n);
                }
                // рекурсивный вызов метода для каждого из детей корневого (в данный момен) узла
            } else textToCDATA(n, doc);
        }
    }


    public static void main(String[] args) throws SAXException, JAXBException, ParserConfigurationException{
        System.out.println(Solution.toXmlWithComment(new First(), "second", "it's a comment"));
    }
}
