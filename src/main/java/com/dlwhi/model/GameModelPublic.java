package com.dlwhi.model;

import com.dlwhi.observer.GameObserver;

public interface GameModelPublic {
    Entity[][] getField();

    void attachObserver(GameObserver obs);

    void detachObserver(GameObserver obs);
}
