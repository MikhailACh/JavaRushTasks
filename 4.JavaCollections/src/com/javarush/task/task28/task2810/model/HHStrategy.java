package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&page=%d";
    //private static final String URL_FORMAT = "https://saratov.hh.ru/search/vacancy?area=79&clusters=true&enable_snippets=true&text=java&page=0";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        int page = 0;
        List<Vacancy> vacancies = new ArrayList<>();

        Document html = null;
        while (true) {
            // searchString - название города поиска
            try {
                html = getDocument(searchString, page);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            Elements vacancyElements = html.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");

            if (vacancyElements.size() == 0)
                break;

            for (Element e : vacancyElements) {
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                vacancy.setSalary(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());
                vacancy.setCity(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                vacancy.setCompanyName(e.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                vacancy.setSiteName(URL_FORMAT);
                vacancy.setUrl(e.getElementsByAttributeValueContaining("data-qa", "title").attr("href"));

                vacancies.add(vacancy);
            }

            page++;
        }

        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String currentURL = String.format(URL_FORMAT, searchString, page);
        Document doc = Jsoup.connect(currentURL)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                .referrer("").get();
        return doc;
    }
}