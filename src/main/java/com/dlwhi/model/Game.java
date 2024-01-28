package com.dlwhi.model;

import java.util.List;

import com.dlwhi.ai.Enemy;
import com.dlwhi.field.Position;
import com.dlwhi.field.WallField;

public class Game {
    private final WallField field; 
    private final Position player;
    private final Position escape;
    private final List<Enemy> ais;

    private boolean catched = false;


    public Game(WallField field, Position player, Position escape, List<Enemy> ais) {
        this.field = field;
        this.player = player;
        this.escape = escape;
        this.ais = ais;
    }

    public Entity[][] getField() {
        Position size = field.getFieldSize();
        Entity[][] fieldArray = new Entity[size.getY()][size.getX()];

        for (int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                if (field.isWallAt(new Position(x, y))) {
                    fieldArray[y][x] = Entity.WALL;
                } else {
                    fieldArray[y][x] = Entity.EMPTY;
                }
            }
        }

        fieldArray[player.getY()][player.getX()] = Entity.PLAYER;
        fieldArray[escape.getY()][escape.getX()] = Entity.ESCAPE;

        for (Enemy enemy : ais) {
            Position pos = enemy.getPosition();
            fieldArray[pos.getY()][pos.getX()] = Entity.ENEMY;
        }

        return fieldArray;
    }

    public boolean makePlayerMove(Position dir) {
        Position newPos = player.sum(dir);
        if (field.isFree(newPos)) {
            player.set(newPos.getX(), newPos.getY());
            for (Enemy enemy : ais) {
                enemy.updatePlayerPosition(dir);
            }
            return true;
        }
        return false;
    }

    public void makeEnemyMove(int enemyIndex) {
        ais.get(enemyIndex).move();
        if (player.equals(ais.get(enemyIndex).getPosition())) {
            catched = true;
        }
    }

    public boolean playerEscaped() {
        return player.equals(escape);
    }

    public boolean playerCatched() {
        return catched;
    }

    public int enemyCount() {
        return ais.size();
    }
}
