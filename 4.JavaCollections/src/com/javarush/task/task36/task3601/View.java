package com.javarush.task.task36.task3601;

public class View {
    public void fireEventShowData() {
        Controller controller = new Controller();
        System.out.println(controller.onDataListShow());
    }
}
