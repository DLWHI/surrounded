package com.dlwhi.model;

import com.dlwhi.field.Position;

public class ModelFacade implements GameModel {
    private final GameFactory provider;
    private Game game;
    private int currentMove;

    public ModelFacade(GameFactory gameProvider) {
        provider = gameProvider;
        game = provider.factoryMethod();
        currentMove = game.enemyCount();
    }

    @Override
    public Entity[][] getField() {
        return game.getField();
    }

    @Override
    public void movePlayer(Position direction) {
        currentMove = 0;
        if (game.makePlayerMove(direction)) {
            // for (; currentMove < game.enemyCount(); ++currentMove) {
            //     game.makeEnemyMove(currentMove);
            // }
        }
    }

    @Override
    public GameStatus status() {
        if (game.playerEscaped()) {
            return GameStatus.PLAYER_WON;
        } else if (game.playerCatched()) {
            return GameStatus.ENEMY_WON;
        }
        return GameStatus.ONGOING;
    }

    @Override
    public void restart() {
        game = null;
        game = provider.factoryMethod();
    }
}
