package com.javarush.task.task34.task3410.controller;

import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;
import com.javarush.task.task34.task3410.model.Model;
import com.javarush.task.task34.task3410.view.View;

public class Controller implements EventListener{
    private View view;
    private Model model;

    public Controller() {
        this.view = new View(this);
        this.model = new Model();
        view.init();
        model.restart();
        model.setEventListener(this);
        view.setEventListener(this);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    // move(Direction direction) - должен вызывать move(Direction direction) у модели и update() у представления.
    //  Метода move() у модели еще нет, добавь для него заглушку, мы его реализуем позже
    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    // должен перезапускать модель и обновлять представление
    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    // должен запускать у модели новый уровень и обновлять
    //представление.
    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}