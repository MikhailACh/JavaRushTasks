package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.orange);

        int xCenter = this.getX()- this.getWidth() / 2;
        int yCenter = this.getY()- this.getHeight() / 2;

        graphics.drawRect(xCenter, yCenter, FIELD_CELL_SIZE, FIELD_CELL_SIZE);
        graphics.fillRect(xCenter, yCenter, FIELD_CELL_SIZE, FIELD_CELL_SIZE);
    }
}
