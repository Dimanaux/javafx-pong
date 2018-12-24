package com.example.pong;

public enum DirectionVertical {
    UP, DOWN;

    private DirectionVertical opposite;

    static {
        UP.opposite = DOWN;
        DOWN.opposite = UP;
    }

    public DirectionVertical opposite() {
        return opposite;
    }
}
