package com.dlwhi.model;

public interface GameModelPrivate extends GameModelPublic {
    public void movePlayer(Position direction);

    public void fortfeit();
    
    public void restart();

}
