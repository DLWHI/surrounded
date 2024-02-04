package com.dlwhi.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.dlwhi.ai.Enemy;

public class Game implements Field {
    private final HashSet<Position> walls = new HashSet<>();
    private Position player = new Position();
    private Position escape = new Position();
    private final HashSet<Enemy> enemies = new HashSet<>();
    private final Position size;

    public Game(Position size) {
        this.size = size;
    }

    public boolean setEscapePos(Position position) {
        if (isFree(position)) {
            escape = position;
            return true;
        }
        return false;
    }

    public boolean setPlayerPos(Position position) {
        if (isFree(position)) {
            player = position;
            return true;
        }
        return false;
    }

    public boolean addEnemy(Enemy enemy) {
        if (isFree(enemy.getPosition())) {
            enemy.setTargets(player, escape);
            enemies.add(enemy);
            return true;
        }
        return false;
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public boolean addWall(Position position) {
        if (isFree(position)) {
            walls.add(position);
            return true;
        }
        return false;
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

    public Set<Enemy> getEnemies() {
        return Collections.unmodifiableSet(enemies);
    }

    public void updateEnemy(Enemy enemy) {
        if (enemies.contains(enemy)) {
            enemy.move();
        }
    }

    public boolean isEnemyAt(Position position) {
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Position getFieldSize() {
        return size;
    }

    @Override
    public boolean isFree(Position pos) {
        return inBounds(pos) 
            && !walls.contains(pos)
            && !player.equals(pos)
            && !escape.equals(pos)
            && !isEnemyAt(pos);
    }

    @Override
    public boolean inBounds(Position pos) {
        return 0 <= pos.getX() && pos.getX() < size.getX() &&
                0 <= pos.getY() && pos.getY() < size.getY();
    }
}
