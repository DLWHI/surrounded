package com.dlwhi.ai;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Random;
import java.util.Queue;

import com.dlwhi.field.Field;
import com.dlwhi.field.Position;

public class FieldSearch {
    private final Field field;

    private final HashMap<Position, Integer> distanceCache = new HashMap<>();
    private Position position;

    public FieldSearch(Field game, Position start) {
        field = game;
        position = start;
        markLenghts(position);
    }

    private void markLenghts(Position from) {
        Queue<Position> queue = new ArrayDeque<>();

        queue.add(from);

        while (!queue.isEmpty()) {
            Position current = queue.remove();
            for (Position direction : Position.DIRECTIONS) {
                Position pos = new Position(current.getX(), current.getY());
                pos.move(direction.getX(), direction.getY());

                if (field.isFree(pos) && distanceCache.getOrDefault(pos, 0) == 0) {
                    distanceCache.put(pos, distanceCache.getOrDefault(current, 0) + 1);
                    queue.add(pos);
                }
            }
        }
    }

    public Queue<Position> pathTo(Position dest) {
        Queue<Position> path = new ArrayDeque<>(distanceCache.get(dest));
        int level = distanceCache.get(dest);
        Position pos = new Position(dest.getX(), dest.getY());

        for (; level != 0; --level) {
            for (Position direction : Position.DIRECTIONS) {
                
                if (distanceCache.getOrDefault(pos.sum(direction), -1) + 1 == level) {
                    path.add(new Position(-direction.getX(), -direction.getY()));
                    break;
                }
            }
        }
        return path;
    }

    static public Position generatePosition(Field field, Position start) {
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
                Position pos = new Position(current.getX(), current.getY());
                pos.move(direction.getX(), direction.getY());

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
