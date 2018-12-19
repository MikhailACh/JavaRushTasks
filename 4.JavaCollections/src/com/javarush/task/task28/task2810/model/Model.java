package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {
    View view;
    Provider[] providers;

    public Model(View view, Provider ... providers) {
        if (view == null || providers == null || providers.length == 0)
            throw new IllegalArgumentException();

        this.view = view;
        this.providers = providers;
    }

    // 5.1. получить вакансии с каждого провайдера,
    // 5.2. обновить вью списком вакансий из п.5.1.
    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            for (Provider p : providers) {
                vacancies.addAll(p.getJavaVacancies(city));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        view.update(vacancies);
    }
}