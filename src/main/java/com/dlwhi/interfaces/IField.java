package com.dlwhi.interfaces;

import java.util.Set;

import com.dlwhi.field.Position;

public interface IField {
    public Position getFieldSize();

    public Position getPlayerPos();

    public Position getEscapePos();
    
    public Set<Position> getEnemyPosisions();

    public boolean isFree(Position pos);

    public boolean isWallAt(Position position);

    public boolean inBounds(Position position);
}
