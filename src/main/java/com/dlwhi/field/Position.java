package com.dlwhi.field;

public class Position {
    public static final Position DIRECTION_LEFT = new Position(-1, 0);
    public static final Position DIRECTION_UP = new Position(0, -1);   
    public static final Position DIRECTION_RIGHT = new Position(1, 0);
    public static final Position DIRECTION_DOWN = new Position(0, 1);
    public static final Position[] DIRECTIONS = 
        {DIRECTION_RIGHT, DIRECTION_UP, DIRECTION_LEFT, DIRECTION_DOWN}; 
    
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

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
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
