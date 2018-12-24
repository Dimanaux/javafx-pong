package com.example.pong;

public enum DirectionHorizontal {
    LEFT, RIGHT;

    private DirectionHorizontal opposite;

    static {
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }

    public DirectionHorizontal opposite() {
        return opposite;
    }
}
