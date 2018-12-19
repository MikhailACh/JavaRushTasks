package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Этот класс будет обобщать способ получения данных о вакансиях
public class Provider {
    private Strategy strategy;

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString) throws IOException {
        if (searchString == null)
            return Collections.emptyList();
        
        return strategy.getVacancies(searchString);
    }
}