package com.dlwhi.ai;

import java.util.Queue;

import com.dlwhi.field.Position;

public class Enemy {
    private final FieldSearch gps;
    private Queue<Position> pathToPlayer;
    private Queue<Position> pathToGoal;
    private final Position position = new Position();

    public Enemy(FieldSearch gps, Position start) {
        this.gps = gps;
        position.set(start.getX(), start.getY());
    }

    public void setTargets(Position player, Position goal) {
        pathToPlayer = gps.pathTo(player);
        pathToGoal = gps.pathTo(goal);
    }

    public void move() {
        Position move;
        if (pathToPlayer.size() < pathToGoal.size()) {
            move = pathToPlayer.poll();
        } else {
            move = pathToGoal.poll();
        }
        position.move(move.getX(), move.getY());
    }

    public Position getPosition() {
        return position;
    }

    public void updatePlayerPosition(Position newPos) {
        // pathToPlayer.addAll(gps.pathTo(pathToPlayer., newPos))
    }
}
