package com.dlwhi.model;

import java.util.HashSet;
import java.util.Set;

import com.dlwhi.field.Game;
import com.dlwhi.field.Position;
import com.dlwhi.interfaces.Entity;
import com.dlwhi.interfaces.IGameFactory;
import com.dlwhi.interfaces.IGameObserver;
import com.dlwhi.interfaces.IPrivateModel;

public class ModelFacade implements IPrivateModel {
    private final Set<IGameObserver> observers = new HashSet<>();
    private final IGameFactory provider;
    private Game game;

    public ModelFacade(IGameFactory gameProvider) {
        provider = gameProvider;
        game = provider.factoryMethod();
    }

    @Override
    public void attachObserver(IGameObserver obs) {
        observers.add(obs);
    }
    
    @Override
    public void detachObserver(IGameObserver obs) {
        observers.remove(obs);
    }

    @Override
    public Entity[][] getField() {
        return game.getField();
    }
    @Override
    public void movePlayer(Position direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movePlayer'");
    }
    @Override
    public void updateOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOne'");
    }
    @Override
    public void updateAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAll'");
    }
}
