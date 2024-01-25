package com.dlwhi.ai;

import java.util.List;

import com.dlwhi.field.Position;

public class Enemy {
    private final FieldSearch gps;
    private List<Position> pathToPlayer;
    private List<Position> pathToGoal;
    private final Position position = new Position();

    public Enemy(FieldSearch gps, Position start) {
        this.gps = gps;
        position.set(start.getX(), start.getY());
    }

    public void setPosition(Position pos) {
        position.set(pos.getX(), pos.getY());
    }

    public void setTargets(Position player, Position goal) {
        pathToPlayer = gps.pathTo(position, player);
        pathToGoal = gps.pathTo(position, goal);
    }

    public void move() {
        Position move;
        if (pathToPlayer.size() < pathToGoal.size()) {
            move = pathToPlayer.remove(pathToPlayer.size() - 1);
        } else { 
            move = pathToGoal.remove(pathToGoal.size() - 1);
        }
        position.move(move.getX(), move.getY());
    }

    public Position getPosition() {
        return position;
    }

    public void updatePlayerPosition(Position moveDir) {
        Position opposite = new Position(-moveDir.getX(), -moveDir.getY());
        if (pathToPlayer.contains(opposite)) {
            pathToPlayer.remove(opposite);
        } else {
            pathToGoal.add(moveDir);
        }
    }
}
