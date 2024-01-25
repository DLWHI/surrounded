package com.dlwhi.interfaces;

public interface IPublicModel {
    void attachObserver(IGameObserver obs);

    void detachObserver(IGameObserver obs);

    Entity[][] getField();
}
