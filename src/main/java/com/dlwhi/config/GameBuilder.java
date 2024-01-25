package com.dlwhi.config;

import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.dlwhi.ai.Enemy;
import com.dlwhi.exceptions.IllegalParametersException;
import com.dlwhi.field.Field;
import com.dlwhi.field.Game;
import com.dlwhi.field.GameGenerator;
import com.dlwhi.field.Position;
import com.dlwhi.interfaces.IGameFactory;

@Parameters(separators = "=")
public class GameBuilder implements IGameFactory {
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

        try {
            for (; game == null;) {
                Field field = gen.generateField();
                Position escape = gen.generateEscape(field);
                Position player = gen.generatePlayer(field, escape);
                List<Enemy> enemies = gen.generateEnemies(field, player);

                game = new Game(field, escape, player, enemies); 
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return game;
    }

    @Override
    public Game factoryMethod() {
        return build();
    }
}
