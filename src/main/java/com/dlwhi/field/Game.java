package com.dlwhi.field;

import java.util.List;

import com.dlwhi.ai.Enemy;
import com.dlwhi.interfaces.Entity;
import com.dlwhi.interfaces.IField;

public class Game {
    private final IField game; 
    private final Position player;
    private final Position escape;
    private final List<Enemy> ais;


    public Game(IField game, Position player, Position escape, List<Enemy> ais) {
        this.game = game;
        this.player = player;
        this.escape = escape;
        this.ais = ais;
    }

    public Entity[][] getField() {
        Position size = game.getFieldSize();
        Entity[][] field = new Entity[size.getX()][size.getY()];

        for (int x = 0; x < size.getX(); x++) {
            for (int y = 0; y < size.getY(); y++) {
                if (game.isWallAt(new Position(x, y))) {
                    field[x][y] = Entity.WALL;
                } else {
                    field[x][y] = Entity.EMPTY;
                }
            }
        }

        field[player.getX()][player.getY()] = Entity.PLAYER;
        field[escape.getX()][escape.getY()] = Entity.ESCAPE;

        for (Enemy enemy : ais) {
            Position pos = enemy.getPosition();
            field[pos.getX()][pos.getY()] = Entity.ENEMY;
        }

        return field;
    }
}
