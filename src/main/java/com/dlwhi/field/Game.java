package com.dlwhi.field;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.dlwhi.interfaces.IField;

public class Game implements IField{
    private final Position size;

    private final Position player = new Position();
    private final Position escape = new Position();

    private final HashSet<Position> walls = new HashSet<>();
    private final HashSet<Position> enemies = new HashSet<>();

    public Game(Position size) {
        this.size = size;
    }

    @Override
    public Position getFieldSize() {
        return size;
    }

    @Override
    public Position getPlayerPos() {
        return new Position(player.getX(), player.getY());
    }

    @Override
    public Position getEscapePos() {
        return new Position(escape.getX(), escape.getY());
    }

    public boolean setPlayerPos(Position pos) {
        if (isFree(pos)) {
            player.set(pos.getX(), pos.getY());
            return true;
        } else {
            return false;
        }
    }

    public boolean setEscapePos(Position pos) {
        if (isFree(pos)) {
            escape.set(pos.getX(), pos.getY());
            return true;
        } else {
            return false;
        }
    }
    
    public void addEnemy(Position position) {
        if (inBounds(position)) {
            enemies.add(position);
        }
    }

    @Override
    public Set<Position> getEnemyPosisions() {
        return Collections.unmodifiableSet(enemies);
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
        return !isWallAt(pos)
            && !player.equals(pos)
            && !escape.equals(pos)
            && !enemies.contains(pos);
    }

    @Override
    public boolean isWallAt(Position position) {
        if (inBounds(position)) {
            return walls.contains(position);
        } else {
            return true;
        }
    }

    @Override
    public boolean inBounds(Position position) {
        return 0 <= position.getX() && position.getX() < size.getX() &&
                0 <= position.getY() && position.getY() < size.getY();
    }
}


