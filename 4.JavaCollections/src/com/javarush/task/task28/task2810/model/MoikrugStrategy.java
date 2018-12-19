package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        int page = 0;
        List<Vacancy> vacancies = new ArrayList<>();

        Document html = null;
        while (true) {
            try {
                html = getDocument(searchString, page);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            Elements vacancyElements = html.getElementsByAttributeValue("class", "job");
            vacancyElements.addAll(html.getElementsByAttributeValue("class", "job marked"));

            if (vacancyElements.size() == 0)
                break;

            for (Element e : vacancyElements) {
                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(e.getElementsByAttributeValue("class", "title").text());
                vacancy.setSalary(e.getElementsByAttributeValue("class", "count").text());
                vacancy.setCity(e.getElementsByAttributeValue("class", "location").text());
                vacancy.setCompanyName(e.getElementsByAttributeValue("class", "company_name").text());
                vacancy.setSiteName(URL_FORMAT);
                vacancy.setUrl("https://moikrug.ru" + e.getElementsByAttributeValueContaining("class", "job_icon").attr("href"));

                vacancies.add(vacancy);
            }

            page++;
        }

        return vacancies;
    }

    /*@Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> Vacancies = new ArrayList<>();
        int pageNum = 0;
        Document doc = null;
        while(true)
        {
            try {
                doc = getDocument(searchString, pageNum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements vacancies = doc.getElementsByClass("job");
            if (vacancies.size()==0) break;
            for (Element element: vacancies)
            {
                if (element != null)
                {
                    Vacancy vac = new Vacancy();
                    vac.setTitle(element.getElementsByAttributeValue("class", "title").text());
                    vac.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                    vac.setSiteName(URL_FORMAT);
                    vac.setUrl("https://moikrug.ru" + element.select("a[class=job_icon]").attr("href"));
                    String salary = element.getElementsByAttributeValue("class", "salary").text();
                    String city = element.getElementsByAttributeValue("class", "location").text();
                    vac.setSalary(salary.length()==0 ? "" : salary);
                    vac.setCity(city.length()==0 ? "" : city);
                    Vacancies.add(vac);
                }
            }
            pageNum++;
        }
        return Vacancies;
    }*/

    protected Document getDocument(String searchString, int page) throws IOException {
        String currentURL = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(currentURL)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                .referrer("")
                .get();
    }
}
