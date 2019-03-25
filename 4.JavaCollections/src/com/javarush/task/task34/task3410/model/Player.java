package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    @Override
    public void move(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        int xCenter = this.getX()-(FIELD_CELL_SIZE / 2);
        int yCenter = this.getY()-(FIELD_CELL_SIZE / 2);

        graphics.fillOval(xCenter, yCenter, FIELD_CELL_SIZE, FIELD_CELL_SIZE);
    }

    public Player(int x, int y) {
        super(x, y);
    }
}