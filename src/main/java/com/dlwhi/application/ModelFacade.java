package com.dlwhi.application;

import java.util.ArrayList;
import java.util.List;

import com.dlwhi.field.GameGenerator;
import com.dlwhi.field.Game;
import com.dlwhi.field.Position;
import com.dlwhi.interfaces.IGameObserver;
import com.dlwhi.interfaces.Entity;
import com.dlwhi.interfaces.IPrivateModel;

public class ModelFacade implements IPrivateModel {
    private final List<IGameObserver> observers = new ArrayList<>();
    private GameGenerator provider;
    private Game game;
    private State state; 

    public ModelFacade(GameGenerator fieldProvider) {
        provider = fieldProvider;
        game = provider.generate();
        state = State.MOVE;
    }

    @Override
    public void updateAllEnemies() {
        // TODO updates all enemies
    }

    @Override
    public void updateOneEnemy() {
        // TODO updates one enemy
    }

    @Override
    public void attachObserver(IGameObserver obs) {
        observers.add(obs);
    }

    @Override
    public void detachObserver(IGameObserver obs) {
        // not sure wheter it removes by reference or by calling equals()
        observers.remove(obs);
    }

    @Override
    public Entity[][] getField() {
        Position size = game.getFieldSize();
        Entity[][] field = new Entity[size.getX()][size.getY()];

        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                if (game.isWallAt(new Position(x, y))) {
                    field[x][y] = Entity.WALL;
                } else {
                    field[x][y] = Entity.EMPTY;
                }
            }
        }

        field[game.getPlayerPos().getX()][game.getPlayerPos().getY()] = Entity.PLAYER;
        field[game.getEscapePos().getX()][game.getEscapePos().getY()] = Entity.ESCAPE;

        for (Position enemy : game.getEnemyPosisions()) {
            field[enemy.getX()][enemy.getY()] = Entity.ENEMY;
        }

        return field;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void movePlayer(Position direction) {
        Position pl = game.getPlayerPos();
        pl.move(direction.getX(), direction.getY());
        game.setPlayerPos(pl);
    }

    @Override
    public void update() {
        // if (game.getPlayer().equals(game.getEndpoint())) {
        //     state = State.CONFIRM;
        //     for (GameObserver observer : observers) {
        //         observer.notifyFinished("You won!");
        //     }
        // } else {
            // }
        for (IGameObserver observer : observers) {
            observer.notifyChanged();
        }
    }

    @Override
    public void finish() {
        state = State.FINISHED;
    }
}
