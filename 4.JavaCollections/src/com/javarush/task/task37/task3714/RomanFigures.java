package com.javarush.task.task37.task3714;

public enum RomanFigures {
    I {
        @Override
        int transform() {
            return 1;
        }
    },
    IV {
        @Override
        int transform() {
            return 4;
        }
    },
    V {
        @Override
        int transform() {
            return 5;
        }
    },
    IX {
        @Override
        int transform() {
            return 9;
        }
    },
    X {
        @Override
        int transform() {
            return 10;
        }
    },
    XL {
        @Override
        int transform() {
            return 40;
        }
    },
    L {
        @Override
        int transform() {
            return 50;
        }
    },
    XC {
        @Override
        int transform() {
            return 90;
        }
    },
    C {
        @Override
        int transform() {
            return 100;
        }
    },
    CD {
        @Override
        int transform() {
            return 400;
        }
    },
    D {
        @Override
        int transform() {
            return 500;
        }
    },
    CM {
        @Override
        int transform() {
            return 900;
        }
    },
    M {
        @Override
        int transform() {
            return 1000;
        }
    };

    abstract int transform();
}