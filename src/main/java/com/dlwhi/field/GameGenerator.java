package com.dlwhi.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dlwhi.ai.Enemy;
import com.dlwhi.ai.FieldSearch;

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

    public Field generateField() {
        random = new Random();

        Field field = new Field(size);
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

    public Position generateEscape(Field field) {
        Position escape = new Position();

        do {
            escape.set(random.nextInt(size.getX()), random.nextInt(size.getY()));
        } while (field.isWallAt(escape));

        return escape;
    }

    public Position generatePlayer(Field game, Position pivot) {
        FieldSearch posGen = new FieldSearch(game);
        Position pos = posGen.generatePosition(pivot);
        if (pos.equals(pivot)) {
            // throw;
        }
        return pos;
    }

    public List<Enemy> generateEnemies(Field game, Position pivot) {
        FieldSearch posGen = new FieldSearch(game);
        List<Enemy> enemies = new ArrayList<>(enemyCount);
        for (int i = 0; i < enemyCount; ++i) {
            Position pos = posGen.generatePosition(pivot);
            Enemy enemy = new Enemy(posGen, pos);
            enemies.add(enemy);
        }
        if (!enemies.isEmpty() && pivot.equals(enemies.get(0).getPosition())) {
            // throw;
        }
        return enemies;
    }
}

