package com.dlwhi.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.dlwhi.ai.EnemyMovementSystem;
import com.dlwhi.observer.GameObserver;

public class ModelFacade implements GameModelPrivate {
    private final GameGenerator generator;
    private Game game;
    private final EnemyMovementSystem ems = new EnemyMovementSystem();

    private final Set<GameObserver> observers = new HashSet<>();

    public ModelFacade(GameGenerator gameProvider) {
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
        if (newPos.equals(game.getEscapePos())) {
            for (GameObserver observer : observers) {
                observer.notifyVictory();
            }
        } else if (game.setPlayerPos(newPos)) {
            Set<Position> updatedEnemyPos = new HashSet<>();
            for (Position enemy : game.getEnemyPositions()) {
                updatedEnemyPos.add(ems.getNextPosition(game, enemy));
            }
            game.updateEnemies(updatedEnemyPos);
            for (GameObserver observer : observers) {
                observer.notifyChanged();
            }
        }
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
