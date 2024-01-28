package com.dlwhi.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.dlwhi.ai.Enemy;
import com.dlwhi.exceptions.IllegalParametersException;
import com.dlwhi.field.WallField;
import com.dlwhi.model.Entity;
import com.dlwhi.model.Game;
import com.dlwhi.model.GameFactory;
import com.dlwhi.model.GameGenerator;
import com.dlwhi.field.Position;

@Parameters(separators = "=")
public class GameBuilder implements GameFactory {
    @Parameter(names = {"--enemiesCount"})
    private int enemyCount;
    @Parameter(names = {"--wallsCount"})
    private int wallCount;
    @Parameter(names = {"--size"})
    private int fieldSize;

    public Game build() {
        if (fieldSize * fieldSize * 0.3 <= enemyCount + wallCount) {
            throw new IllegalParametersException();
        }

        GameGenerator gen = new GameGenerator(
            new Position(fieldSize, fieldSize),
            wallCount,
            enemyCount
        );
        Game game = null;

        for (; game == null;) {
            WallField field = gen.generateField();
            Position escape = gen.generateEscape(field);
            Position player = gen.generatePlayer(field, escape);
            List<Enemy> enemies = gen.generateEnemies(field, player);

            for (Enemy enemy : enemies) {
                enemy.setTargets(player, escape);
            }

            game = new Game(field, player, escape, enemies); 

            if (!checkIntegrity(game)) {
                game = null;
            }
        }

        return game;
    }

    public boolean checkIntegrity(Game game) {
        Entity[][] field = game.getField();
        Set<Entity> entityList = new HashSet<>();

        for (Entity[] fieldRow : field) {
            for (Entity entity : fieldRow) {
                entityList.add(entity);
            }
        }

        return entityList.size() == 5;
    } 

    @Override
    public Game factoryMethod() {
        return build();
    }
}
