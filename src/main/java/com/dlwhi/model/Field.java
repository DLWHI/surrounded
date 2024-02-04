package com.dlwhi.model;

public interface Field {
    public Position getFieldSize();

    public boolean isFree(Position pos);

    public boolean inBounds(Position pos);
}
