package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

public class Model {
    public static int FIELD_CELL_SIZE = 20; // величина ячейки и перемещения игрового объекта на один шаг

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 60;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("D:\\Java\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    private void restartLevel(int level) {
        this.gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel = currentLevel + 1;
        restartLevel(currentLevel);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();

        // проверяем столкновение со стеной
        if (checkWallCollision(player, direction))
            return;

        // проверяем столкновение с ящиками
        if (checkBoxCollisionAndMoveIfAvaliable(direction))
            return;

        // передвинуть игрока в направлении direction
        switch (direction) {
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
        }

        // проверяем, завершен ли уровень
        checkCompletion();
    }

    // Этот метод проверяет столкновение со стеной. Он должен вернуть true,
    // если при движении объекта gameObject в направлении direction произойдет столкновение со стеной, иначе false.
    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }

        return false;
    }

    // Этот метод проверяет столкновение с ящиками:
    // true, если игрок не может быть сдвинут в направлении direction (там находится: или ящик, за которым стена; или ящик за которым еще один ящик).
    // false, если игрок может быть сдвинут в направлении direction (там находится: или свободная ячейка; или дом; или ящик, за которым свободная ячейка или дом).
    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();
        GameObject obstacle = null;

        // проходим по всем игровым объектам
        for (GameObject go : gameObjects.getAll())
            // если текущий объект не Home и Player, но при этом обнаружена коллизия
            if (!(go instanceof Player) && !(go instanceof Home) && player.isCollision(go, direction))
                obstacle = go;


        // если препятствий перед игроком нет - выход с false
        if ((obstacle == null))
            return false;


        // если препятствие - ящик
        if (obstacle instanceof Box) {
            Box stopBox = (Box) obstacle;

            // проверяем этот ящик на столкновение со стеной
            if (checkWallCollision(stopBox, direction)) {
                return true;
            }

            // столкновения со стеной нет, проверяем на столкновение с другим ящиком
            for (Box box : gameObjects.getBoxes()) {
                if (stopBox.isCollision(box, direction))
                    return true;
            }

            // значит, перед игроком - только другой ящик, двигаем его на фиксированное значение FIELD_CELL_SIZE
            switch (direction) {
                case LEFT:
                    stopBox.move(-FIELD_CELL_SIZE, 0);
                    break;
                case RIGHT:
                    stopBox.move(FIELD_CELL_SIZE, 0);
                    break;
                case UP:
                    stopBox.move(0, -FIELD_CELL_SIZE);
                    break;
                case DOWN:
                    stopBox.move(0, FIELD_CELL_SIZE);
                    break;
            }
        }

        return false;
    }

    public void checkCompletion() {
        int counter = 0;
        Set<Home> homes = gameObjects.getHomes();

        for (Home home : homes) {
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY())
                    counter++;
            }
        }

        if (counter == homes.size())
            eventListener.levelCompleted(currentLevel);
    }
}