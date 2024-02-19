package com.dlwhi.ai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Random;

import com.dlwhi.model.Game;
import com.dlwhi.model.Position;

public class FieldSearch {
    private final Game field;
    private final Position position;

    private final HashMap<Position, Integer> distanceCache = new HashMap<>();

    public FieldSearch(Game game, Position start) {
        field = game;
        position = start;
        distanceCache.put(position, 0);
        mark();
    }

    private void mark() {
        Queue<Position> queue = new LinkedList<>();
        queue.add(position);

        for (; !queue.isEmpty(); ) {
            Position pos = queue.poll();
            int dist = distanceCache.get(pos);
            for (Position dir : Position.DIRECTIONS) {
                Position current = pos.sum(dir);
                if (moveFree(current) && !distanceCache.containsKey(current)) {
                    distanceCache.put(current, dist + 1);
                    queue.add(current);
                }
            }
        }
    }

    private boolean moveFree(Position position) {
        return !field.isObstacleAt(position);
    }

    public Position directionFrom(Position position) {
        int level = distanceCache.get(position);
        for (Position dir : Position.DIRECTIONS) {
            Position current = position.sum(dir);
            if (field.inBounds(current) && distanceCache.getOrDefault(current, -1) == level - 1) {
                return dir;
            }
        }
        return new Position();  // null object pattern
    }

    public int distanceTo(Position position) {
        if (field.inBounds(position) && distanceCache.containsKey(position)) {
            return distanceCache.get(position);
        }
        return -1;
    }

    public Position getPosition() {
        return position;
    }

    static public Position generatePosition(Game field, Position start) {
        Position fieldSize = field.getFieldSize();
        Random random = new Random();
        Stack<Position> queue = new Stack<>();
        Position last = start;
        Set<Position> visited = new HashSet<>();
        float prob = 0;
        float probIncrement = 1f/fieldSize.getX()/fieldSize.getY();

        queue.add(start);

        while (!queue.isEmpty() && prob < random.nextFloat()) {
            Position current = queue.pop();
            for (Position direction : Position.DIRECTIONS) {
                Position pos = current.sum(direction);;

                if (field.isFree(pos) && !visited.contains(pos)) {
                    queue.add(pos);
                }
            }
            visited.add(current);
            prob += probIncrement;
            last = current;
        }
        return last;
    }   
}
