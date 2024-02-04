package com.dlwhi.model;

import java.lang.reflect.Array;

public class Position {
    public static final Position DIRECTION_LEFT = new Position(-1, 0);
    public static final Position DIRECTION_UP = new Position(0, -1);
    public static final Position DIRECTION_RIGHT = new Position(1, 0);
    public static final Position DIRECTION_DOWN = new Position(0, 1);
    public static final Position[] DIRECTIONS = { DIRECTION_RIGHT, DIRECTION_UP, DIRECTION_LEFT, DIRECTION_DOWN };

    private int x;
    private int y;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position sum(Position other) {
        return new Position(x + other.x, y + other.y);
    }

    @SuppressWarnings("unchecked")
    public <T> T[][] makeArray(T[][] type) {
        if (type.length < y) {
            type = (T[][])Array.newInstance(type.getClass().getComponentType(), y);
        }
        if (x > 0 && (type[0] == null || type[0].length < x)) {
            for (int i = 0; i < type.length; i++) {
                Class<?> componentType = type.getClass().getComponentType().getComponentType();
                type[i] = (T[])Array.newInstance(componentType, x);
            }
        }
        return type;
    }

    public <T> T getFromArray(T[][] array) {
        return array[y][x];
    }

    public <T> void putToArray(T[][] array, T value) {
        array[y][x] = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Position))
            return false;
        Position other = (Position) obj;
        if (x != other.x || y != other.y)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Entity [x=" + x + ", y=" + y + "]";
    }
}
