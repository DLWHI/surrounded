package com.dlwhi.interfaces;

public interface IPublicModel {
    enum State {
        MOVE,
        CONFIRM,
        GAME_OVER,
        FINISHED
    }

    void attachObserver(IGameObserver obs);

    void detachObserver(IGameObserver obs);

    Entity[][] getField();

    State getState();
}
