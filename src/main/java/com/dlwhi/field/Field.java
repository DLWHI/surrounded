package com.dlwhi.field;

public interface Field {
    public Position getFieldSize();

    public boolean isFree(Position pos);

    public boolean inBounds(Position pos);
}
