package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";
    Controller controller;
    // final String filePath = "D://Java//JavaRushTasks//4.JavaCollections//src//com//javarush//task//task28//task2810//view//vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Saratov");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document = null;
        try {
            document = getDocument();

            // создаем элемент-шаблон вакансии
            Element original =  document.getElementsByClass("template").first();
            Element template = original.clone();
            template.removeClass("template");
            template.removeAttr("style");

            // удаляем старые вакансии
            //System.out.println(document.getElementsByClass("vacancy").not("vacancy template").remove());
            document.getElementsByAttributeValue("class", "vacancy").remove();

            // для каждой вакансии
            for (Vacancy vacancy : vacancies) {
                Element current = template.clone();
                current.getElementsByClass("city").first().text(vacancy.getCity());
                current.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                current.getElementsByClass("salary").first().text(vacancy.getSalary());

                Element link = current.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                original.before(current.outerHtml());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return "Some exception occurred";
        }

        return document.html();
    }

    private void updateFile(String s) {
        try(FileWriter fw = new FileWriter(new File(filePath))) {
            fw.write(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        File vacancy = new File(filePath);
        return Jsoup.parse(vacancy, "UTF-8");
    }
}