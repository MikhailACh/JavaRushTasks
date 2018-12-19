package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.*;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;

public class Aggregator {
    public static void main(String[] args) {
        Strategy hhStrategy = new HHStrategy();
        Strategy mkStrategy = new MoikrugStrategy();

        Provider hhProvider = new Provider(hhStrategy);
        Provider mkProvider = new Provider(mkStrategy);

        View view = new HtmlView();
        Model model = new Model(view, hhProvider, mkProvider);
        Controller controller = new Controller(model);

        view.setController(controller);
        ((HtmlView) view).userCitySelectEmulationMethod();
    }
}