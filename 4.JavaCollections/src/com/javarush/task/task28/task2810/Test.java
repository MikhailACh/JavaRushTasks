package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) throws IOException{
        /*Document doc = null;

        try {
            doc = Jsoup.connect("https://saratov.hh.ru/search/vacancy?area=79&clusters=true&enable_snippets=true&text=java&page=0")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                    .referrer("").get();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Elements vacancyElements = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");

        for (Element e : vacancyElements) {
            System.out.println(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
            System.out.println(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
            System.out.println(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
            System.out.println(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
            System.out.println(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").attr("href"));
        }*/

        View view = new HtmlView();
        Path vac = Paths.get("D://Java//JavaRushTasks//4.JavaCollections//src//com//javarush//task//task28//task2810//view//vacancies.html");
        // System.out.println(vac.toFile().exists()+ " " + vac.toFile().length() + " " + vac.toFile().getPath());
        Path vac2 = Paths.get("./4.JavaCollections/src/" + view.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html");
        // System.out.println(vac2.toFile().exists() + " " + vac2.toFile().length() + " " + vac2.toFile().getPath());

        System.out.println("_______________________________________________________________________________");
        File vacancy = vac2.toFile();
        Document document = Jsoup.parse(vacancy, "UTF-8");

        Element template = document.getElementsByClass("template").first();
        //System.out.println(template);
        System.out.println("_______________________________________________________________________________");
        template.removeClass("template").removeAttr("style");
        System.out.println(template);
        System.out.println(" _______________________________________________________________________________");
        // document.getElementsByClass("vacancy").remove();
        // document.getElementsByAttributeValue("class", "vacancy").remove();
        template.getElementsByClass("city").first().html("Саратов");
        template.getElementsByClass("companyName").first().html("EPAM");
        template.getElementsByClass("salary").first().html("100000");
        template.select("a").first().html("JAVA Developer").attr("href", "www.epam.com");
        template.before(template.outerHtml());
        System.out.println(template);
    }

}
