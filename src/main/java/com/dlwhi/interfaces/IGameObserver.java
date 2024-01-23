package com.dlwhi.interfaces;

import com.dlwhi.field.Position;

public interface IGameObserver {
    void notifyPlayerMoved(Position dir);

    void notifyFinished(String message);
}
