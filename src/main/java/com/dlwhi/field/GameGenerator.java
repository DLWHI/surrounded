package com.dlwhi.field;

import java.util.Random;

import com.dlwhi.ai.FieldSearch;

public class GameGenerator {
    private final Position size;
    private int wallCount;
    private int enemyCount;

    private Random random;
    private Position escape;
    private Position player;
    private FieldSearch posGen;

    public GameGenerator(Position size, int wallCount, int enemyCount) {
        this.size = size;
        this.wallCount = wallCount;
        this.enemyCount = enemyCount;
    }

    public Game generate() {
        random = new Random();
        boolean complete = false;

        Game game = null;

        while(!complete) {
            game = new Game(size);
            posGen = new FieldSearch(game);

            generateWalls(game);
    
            generateEscape(game);
    
            complete = generatePlayer(game);

            complete = generateEnemies(game);
        }

        return game;
    }

    private void generateWalls(Game field) {
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
    }

    private void generateEscape(Game field) {
        escape = new Position();

        do {
            escape.set(random.nextInt(size.getX()), random.nextInt(size.getY()));
        } while (!field.setEscapePos(escape));
    }

    private boolean generatePlayer(Game game) {
        Position pos = posGen.generatePosition(escape);
        if (pos.equals(escape)) {
            return false;
        }
        player = pos;
        game.setPlayerPos(pos);
        return true;
    }

    private boolean generateEnemies(Game game) {
        for (int i = 0; i < enemyCount; ++i) {
            Position pos = posGen.generatePosition(player);
            if (pos.equals(player)) {
                return false;
            }
            game.addEnemy(pos);
        }
        return true;
    }
}

