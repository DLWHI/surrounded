package com.dlwhi.ai;

import com.dlwhi.model.Game;
import com.dlwhi.model.Position;

public class EnemyMovementSystem {
    private Game cachedGame;
    private FieldSearch gpsToEscape;

    public Position getDirection(Game game, Position current) {
        if (cachedGame != game) {
            cacheField(game);
        }
        FieldSearch gps = new FieldSearch(cachedGame, cachedGame.getPlayerPos());
        if (gps.distanceTo(current) > gpsToEscape.distanceTo(current)) {
            gps = gpsToEscape;
        }
        return gps.directionFrom(current);
    }

    public void cacheField(Game game) {
        gpsToEscape = new FieldSearch(game, game.getEscapePos());
        cachedGame = game;
    }
}
