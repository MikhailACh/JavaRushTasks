package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int objX = gameObject.getX();
        int objY = gameObject.getY();

        switch (direction) {
            case UP: {
                objY = objY + FIELD_CELL_SIZE;
                break;
            }
            case DOWN: {
                objY = objY - FIELD_CELL_SIZE;
                break;
            }
            case LEFT: {
                objX = objX + FIELD_CELL_SIZE;
                break;
            }
            case RIGHT: {
                objX = objX - FIELD_CELL_SIZE;
                break;
            }
            default:
                return false;
        }

        return (objX == this.getX()) && (objY == this.getY());
    }
}