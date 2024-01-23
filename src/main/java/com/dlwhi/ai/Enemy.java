package com.dlwhi.ai;

import java.util.LinkedList;
import java.util.List;

import com.dlwhi.field.Position;
import com.dlwhi.interfaces.IGameObserver;

public class Enemy implements IGameObserver {
    private final List<Position> pathToPlayer = new LinkedList<>();
    private final List<Position> pathToGoal = new LinkedList<>();
    private Position position;

    public Enemy(Position start) {
        position = start;
    }

    public void setTargets(Position player, Position goal) {
        
    }

    public Position getNextMove() {
        if (pathToPlayer.size() < pathToGoal.size()) {
            return pathToPlayer.get(pathToPlayer.size() - 1);
        }
        return pathToGoal.get(pathToGoal.size() - 1);
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void notifyChanged() {
        // Position neg = dir.negative();
        // if (pathToPlayer.contains(neg)) {
        //     pathToPlayer.remove(neg);
        // } else {
        //     pathToPlayer.add(dir);
        // }
    }

    @Override
    public void notifyFinished(String message) {
        pathToGoal.clear();
        pathToPlayer.clear();
    }
}
