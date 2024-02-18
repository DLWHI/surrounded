package com.dlwhi.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private final HashSet<Position> walls = new HashSet<>();
    private Position player = new Position();
    private Position escape = new Position();
    private final HashSet<Position> enemies = new HashSet<>();
    private final Position size;

    public Game(Position size) {
        this.size = size;
    }

    public boolean setEscapePos(Position position) {
        if (isObstacleAt(position)) {
            return false;
        }
        escape = position;
        return true;
    }

    public boolean setPlayerPos(Position position) {
        if (isEntityAt(position)) {
            return false;
        }
        player = position;
        player = new Position();
        return true;
    }

    public boolean addEnemy(Position position) {
        if (isEntityAt(position)) {
            return false;
        }
        enemies.add(position);
        return true;
    }

    public void removeEnemy(Position position) {
        enemies.remove(position);
    }

    public boolean addWall(Position position) {
        if (isObstacleAt(position)) {
            return false;
        }
        walls.add(position);
        return true;
    }

    public void removeWall(Position position) {
        walls.remove(position);
    }

    public Position getPlayerPos() {
        return player;
    }

    public Position getEscapePos() {
        return escape;
    }

    public Set<Position> getWallsPos() {
        return Collections.unmodifiableSet(walls);
    }

    public Set<Position> getEnemyPositions() {
        return Collections.unmodifiableSet(enemies);
    }

    public Position getFieldSize() {
        return size;
    }

    public void updateEnemies(Set<Position> updatedPos) {
        enemies.clear();
        for (Position position : updatedPos) {
            if (!isEntityAt(position)) {
                enemies.add(position);
            }
        }
    }

    public boolean isObstacleAt(Position pos) {
        return !inBounds(pos) || walls.contains(pos) || escape.equals(pos);
    }

    public boolean isEntityAt(Position pos) {
        return enemies.contains(pos) || player.equals(pos);
    }

    public boolean isFree(Position pos) {
        return !isObstacleAt(pos) && !isEntityAt(pos);
    }

    public boolean inBounds(Position pos) {
        return 0 <= pos.getX() && pos.getX() < size.getX() &&
                0 <= pos.getY() && pos.getY() < size.getY();
    }
}
