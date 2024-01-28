package com.dlwhi.model;

import com.dlwhi.field.Position;

public interface GameModel {
    public void movePlayer(Position direction);

    public void restart();

    public GameStatus status();

    Entity[][] getField();
}
