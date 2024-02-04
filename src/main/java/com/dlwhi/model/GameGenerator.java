package com.dlwhi.model;

import java.util.Random;

import com.dlwhi.ai.Enemy;
import com.dlwhi.ai.FieldSearch;
import com.dlwhi.exceptions.RegenerationException;

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

    private void generateWalls(Game field) {
        random = new Random();
        for (int i = 0; i < wallCount;) {
            Position pos = new Position(
                    random.nextInt(size.getX()),
                    random.nextInt(size.getY()));
            if (field.addWall(pos)) {
                ++i;
            }
        }
    }

    private void generateEscape(Game field) {
        while (!field.setEscapePos(new Position(random.nextInt(size.getX()), random.nextInt(size.getY()))))
            ;
    }

    private void generatePlayer(Game field, Position pivot) {
        Position pos = FieldSearch.generatePosition(field, pivot);
        if (pos.equals(pivot)) {
            throw new RegenerationException();
        }
        field.setPlayerPos(pos);
    }

    private void generateEnemies(Game field, Position pivot) {
        FieldSearch navigator = new FieldSearch(field);
        for (int i = 0; i < enemyCount; ++i) {
            Position pos = FieldSearch.generatePosition(field, pivot);
            if (pos.equals(pivot)) {
                throw new RegenerationException();
            }
            field.addEnemy(new Enemy(navigator, pos));
        }
    }

    public Game create() {
        Game game = null;
        while (game == null) {
            try {
                game = new Game(size);
                game.addWall(new Position(0, 1));
                game.addWall(new Position(1, 1));
                game.addWall(new Position(2, 1));
                game.addWall(new Position(3, 1));
                game.addEnemy(new Enemy(new FieldSearch(game), new Position(0, 2)));
                game.setPlayerPos(new Position());
                game.setEscapePos(new Position(0, size.getY() - 1));
                // generateWalls(game);
                // generateEscape(game);
                // generatePlayer(game, game.getEscapePos());
                // generateEnemies(game, game.getPlayerPos());
            } catch (RegenerationException e) {
                game = null;
            }
        }
        return game;
    }
}
