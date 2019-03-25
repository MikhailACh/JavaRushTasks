package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject{
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLUE);

        int xCenter = this.getX()- this.getWidth() / 2;
        int yCenter = this.getY()- this.getHeight() / 2;

        graphics.drawRect(xCenter, yCenter, this.getWidth(), this.getHeight());
        graphics.fillRect(xCenter, yCenter, this.getWidth(), this.getHeight());
    }
}
