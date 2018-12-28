package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (y < 0 || x < 0 || y >= image.length || x >= image[0].length)
            return false;

        if (desiredColor == null || desiredColor == image[y][x])
            return false;

        image[y][x] = desiredColor;

        return true;
    }
}
