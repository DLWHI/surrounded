package com.dlwhi.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.dlwhi.ai.EnemyMovementSystem;
import com.dlwhi.observer.GameObserver;

public class ModelFacade implements GameModelPrivate {
    private final FieldGenerator generator;
    private Field game;
    private final EnemyMovementSystem ems = new EnemyMovementSystem();

    private final Set<GameObserver> observers = new HashSet<>();

    public ModelFacade(FieldGenerator gameProvider) {
        generator = gameProvider;
        game = generator.create();
        ems.cacheField(game);
    }

    @Override
    public Entity[][] getField() {
        Entity[][] fieldArray = game.getFieldSize().makeArray(new Entity[0][0]);

        for (Entity[] row : fieldArray) {
            Arrays.fill(row, Entity.EMPTY);
        }

        for (Position wall : game.getWallsPos()) {
            wall.putToArray(fieldArray, Entity.WALL);
        }
        
        game.getPlayerPos().putToArray(fieldArray, Entity.PLAYER);
        game.getEscapePos().putToArray(fieldArray, Entity.ESCAPE);

        for (Position enemy : game.getEnemyPositions()) {
            enemy.putToArray(fieldArray, Entity.ENEMY);
        }

        return fieldArray;
    }

    @Override
    public void movePlayer(Position direction) {
        Position newPos = game.getPlayerPos().sum(direction);
        if (!game.isWallAt(newPos)) {
            game.setPlayerPos(newPos);
            update();
        }
    }

    private void update() {
        Set<Position> updatedEnemyPos = new HashSet<>();
        for (Position enemy : game.getEnemyPositions()) {
            Position newPos = enemy.sum(ems.getDirection(game, enemy));
            if (game.isFree(newPos)) {
                updatedEnemyPos.add(newPos);
            } else {
                updatedEnemyPos.add(enemy);
            }
        }
        game.updateEnemies(updatedEnemyPos);

        for (GameObserver observer : observers) {
            observer.notifyChanged();
        }

        Position player = game.getPlayerPos();
        Position escape = game.getEscapePos();
        if (player.equals(escape)) {
            for (GameObserver observer : observers) {
                observer.notifyVictory();
            }
        } else if (playerCatched(player)) {
            for (GameObserver observer : observers) {
                observer.notifyLoss();
            }
        }
    }

    private boolean playerCatched(Position pos) {
        Set<Position> enemies = game.getEnemyPositions();
        if (enemies.contains(pos)) {
            return true;
        }
        for (Position dir : Position.DIRECTIONS) {
            if (enemies.contains(pos.sum(dir))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void fortfeit() {
        for (GameObserver observer : observers) {
            observer.notifyLoss();
        }
    }

    @Override
    public void restart() {
        game = null;
        game = generator.create();
        ems.cacheField(game);
        for (GameObserver observer : observers) {
            observer.notifyChanged();
        }
    }

    @Override
    public void attachObserver(GameObserver obs) {
        observers.add(obs);
    }

    @Override
    public void detachObserver(GameObserver obs) {
        observers.remove(obs);
    }
}
