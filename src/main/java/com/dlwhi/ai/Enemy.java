package com.dlwhi.ai;

import java.util.Deque;

import com.dlwhi.model.Position;

public class Enemy {
    private final FieldSearch gps;
    private Deque<Position> pathToPlayer;
    private Deque<Position> pathToGoal;
    private Position position;

    public Enemy(FieldSearch gps, Position start) {
        this.gps = gps;
        position = start;
    }

    public void setTargets(Position player, Position goal) {
        pathToPlayer = gps.pathTo(position, player);
        pathToGoal = gps.pathTo(position, goal);
    }

    public void move() {
        Position move;
        // if (pathToPlayer.size() < pathToGoal.size()) {
            move = pathToPlayer.removeFirst();
        // } else {
        //     move = pathToGoal.poll();
        // }
        position = position.sum(move);
    }

    public Position getPosition() {
        return position;
    }

    public void updatePlayerPosition(Position newPos) {
        // pathToPlayer.addAll(gps.pathTo(pathToPlayer., newPos))
    }
}
