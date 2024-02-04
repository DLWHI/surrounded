package com.dlwhi.observer;

public interface GameObserver {
    void notifyChanged();

    void notifyLoss();

    void notifyVictory();
}
