package com.dlwhi.interfaces;

public interface IGameObserver {
    void notifyChanged();

    void notifyFinished(String message);
}
