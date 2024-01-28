package com.dlwhi.field;

import java.util.HashSet;

public class WallField implements Field {
    private final Position size;
    
    private final HashSet<Position> walls = new HashSet<>();

    public WallField(Position size) {
        this.size = size;
    }

    @Override
    public Position getFieldSize() {
        return size;
    }

    public void addWall(Position position) {
        if (inBounds(position)) {
            walls.add(position);
        }
    }

    public void removeWall(Position position) {
        if (inBounds(position)) {
            walls.remove(position);
        }
        throw new IndexOutOfBoundsException("Cannot clear position out of field");
    }

    @Override
    public boolean isFree(Position pos) {
        return !isWallAt(pos);
    }

    public boolean isWallAt(Position pos) {
        if (inBounds(pos)) {
            return walls.contains(pos);
        } else {
            return true;
        }
    }

    @Override
    public boolean inBounds(Position pos) {
        return 0 <= pos.getX() && pos.getX() < size.getX() &&
                0 <= pos.getY() && pos.getY() < size.getY();
    }
}
