package com.dlwhi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dlwhi.ai.Enemy;
import com.dlwhi.ai.FieldSearch;
import com.dlwhi.field.Position;
import com.dlwhi.field.WallField;

public class GameGenerator {
    private final Position size;
    private int wallCount;
    private int enemyCount;

    private Random random;

    public GameGenerator(Position size, int wallCount, int enemyCount) {
        this.size = size;
        this.wallCount = wallCount;
        this.enemyCount = enemyCount;
    }

    public WallField generateField() {
        random = new Random();

        WallField field = new WallField(size);
        for (int i = 0; i < wallCount; ) {
            Position pos = new Position(
                random.nextInt(size.getX()),
                random.nextInt(size.getY())
            );
            if (field.isFree(pos)) {
                field.addWall(pos);
                ++i;
            }
        }

        return field;
    }

    public Position generateEscape(WallField field) {
        Position escape = new Position();

        do {
            escape.set(random.nextInt(size.getX()), random.nextInt(size.getY()));
        } while (field.isWallAt(escape));

        return escape;
    }

    public Position generatePlayer(WallField game, Position pivot) {
        Position pos = FieldSearch.generatePosition(game, pivot);
        return pos;
    }

    public List<Enemy> generateEnemies(WallField game, Position pivot) {
        List<Enemy> enemies = new ArrayList<>(enemyCount);
        for (int i = 0; i < enemyCount; ++i) {
            Position pos = FieldSearch.generatePosition(game, pivot);
            Enemy enemy = new Enemy(new FieldSearch(game, pos), pos);
            enemies.add(enemy);
        }
        return enemies;
    }
}

