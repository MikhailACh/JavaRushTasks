package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static com.javarush.task.task34.task3410.model.GameObject.FIELD_CELL_SIZE;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    // 16.1. Открой файл levels.txt и внимательно изучи структуру файла.
    // Символ 'X' - означает стену, '*' - ящик, '.' - дом, '&' - ящик который стоит в доме, а '@' - игрока. Всего в файле 60 уровней.
    //16.2. Реализуй метод GameObjects getLevel(int level). Он должен:
    //16.2.1. Вычитывать из файла данные уровня level.
    // Уровни должны повторяться циклически, если пользователь прошел все 60 уровней и попал на 61 уровень, то ему нужно вернуть 1, вместо 62 уровня вернуть 2 и т.д.
    //16.2.2. Создать все игровые объекты, описанные в указанном уровне. Координаты каждого игрового объекта должны быть рассчитаны согласно следующей логике:
    //16.2.2.1. Самый верхний левый объект (если такой есть) должен иметь координаты x = x0 = FIELD_CELL_SIZE / 2 и y = y0 = FIELD_CELL_SIZE / 2.
    //16.2.2.2. Объект, который находится на одну позицию правее от него должен иметь координаты x = x0 + FIELD_CELL_SIZE и y = y0.
    //16.2.2.3. Объект, который находится на одну позицию ниже от самого верхнего левого должен иметь координаты x = x0 и y = y0 + FIELD_CELL_SIZE.
    //16.2.2.4. Аналогично должны рассчитываться координаты любого объекта на поле.
    //16.2.3. Создать новое хранилище объектов GameObjects и поместить в него все объекты.
    //16.2.4. Вернуть созданное хранилище.
    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        // если номер уровня больше 60 - начинаем с 1
        int loopLvl;
        if (level > 60)
            loopLvl = level - 60;
        else
            loopLvl = level;

        try {
            List<String> lines = Files.readAllLines(levels);

            int index = lines.indexOf("Maze: " + loopLvl) + 7; // номер строки в файле
            int cur = 0; // текущая линия по оси Y

            // проход вниз по строкам файла с уровнями
            for (int i = index; i < lines.size(); i++) {
                String fieldLine = lines.get(i);

                // пока не наткнемся на пустую строку
                if (fieldLine.isEmpty())
                    break;

                // разбираем текущую линию на символы и просматриваем на предмет игровых объектов
                char[] c = fieldLine.toCharArray();
                int offset = FIELD_CELL_SIZE / 2;
                int y = cur * FIELD_CELL_SIZE;

                for (int j = 0; j < c.length; j++) {
                    int x = j * FIELD_CELL_SIZE;

                    switch (c[j]) {
                        case 'X' :
                            walls.add(new Wall(x  + offset, cur * FIELD_CELL_SIZE + offset));
                            break;
                        case '*' :
                            boxes.add(new Box(x  + offset, cur * FIELD_CELL_SIZE + offset));
                            break;
                        case '.' :
                            homes.add(new Home(x  + offset, cur * FIELD_CELL_SIZE + offset));
                            break;
                        case '&' :
                            homes.add(new Home(x  + offset, cur * FIELD_CELL_SIZE + offset));
                            boxes.add(new Box(x  + offset, cur * FIELD_CELL_SIZE + offset));
                            break;
                        case '@' :
                            player = new Player(x  + offset, cur * FIELD_CELL_SIZE + offset);
                            //player = new Player(10, 10);
                            break;
                    }
                }

                cur++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        /*Set<Wall> walls = new HashSet<>();
        walls.add(new Wall(FIELD_CELL_SIZE / 2, FIELD_CELL_SIZE / 2));
        walls.add(new Wall(200 + FIELD_CELL_SIZE / 2, FIELD_CELL_SIZE / 2));
        walls.add(new Wall(FIELD_CELL_SIZE / 2, 200 + FIELD_CELL_SIZE / 2));
        walls.add(new Wall(200 + FIELD_CELL_SIZE / 2, 200 + FIELD_CELL_SIZE / 2));

        Set<Box> boxes = new HashSet<>();
        boxes.add(new Box(20 + FIELD_CELL_SIZE / 2, 20 + FIELD_CELL_SIZE / 2));

        Set<Home> homes = new HashSet<>();
        homes.add(new Home(60 + FIELD_CELL_SIZE / 2, 60 + FIELD_CELL_SIZE / 2));

        Player player = new Player(180 + FIELD_CELL_SIZE / 2, 20 + FIELD_CELL_SIZE / 2);

        return new GameObjects(walls, boxes, homes, player);*/

        /*int loopLevel;
        if (level > 60) {
            loopLevel = level % 60;
        } else {
            loopLevel = level;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            int readLevel = 0;
            int x;
            int y = Model.FIELD_CELL_SIZE / 2;
            boolean isLevelMap = false;

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Maze:")) {
                    readLevel = Integer.valueOf(line.split(" ")[1]);
                    continue;
                }
                if (readLevel == loopLevel) {
                    if (line.length() == 0) {
                        boolean isEnd = isLevelMap;

                        isLevelMap = !isLevelMap;

                        if (isEnd && !isLevelMap) {
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (isLevelMap) {
                        x = Model.FIELD_CELL_SIZE / 2;

                        char[] chars = line.toCharArray();
                        for (char c : chars) {
                            switch (c) {
                                case 'X':
                                    walls.add(new Wall(x, y));
                                    break;
                                case '*':
                                    boxes.add(new Box(x, y));
                                    break;
                                case '.':
                                    homes.add(new Home(x, y));
                                    break;
                                case '&':
                                    boxes.add(new Box(x, y));
                                    homes.add(new Home(x, y));
                                    break;
                                case '@':
                                    player = new Player(x, y);
                            }
                            x += Model.FIELD_CELL_SIZE;
                        }
                        y += Model.FIELD_CELL_SIZE;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return new GameObjects(walls, boxes, homes, player);
    }
}