package com.dlwhi.interfaces;

import com.dlwhi.field.Position;

public interface IField {
    public Position getFieldSize();

    public boolean isFree(Position pos);

    public boolean isWallAt(Position pos);

    public boolean inBounds(Position pos);
}
