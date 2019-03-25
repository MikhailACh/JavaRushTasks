package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        this.setHeight(20);
        this.setWidth(20);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);

        int xCenter = this.getX()- this.getWidth() / 2;
        int yCenter = this.getY()- this.getHeight() / 2;

        graphics.drawRect(xCenter, yCenter, this.getWidth(), this.getHeight());
        graphics.fillRect(xCenter, yCenter, this.getWidth(), this.getHeight());
    }
}