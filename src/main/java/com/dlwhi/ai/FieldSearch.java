package com.dlwhi.ai;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Random;

import com.dlwhi.model.Field;
import com.dlwhi.model.Position;

public class FieldSearch {
    private final Field field;

    private Position lastStart;
    private Queue<Position> queue;

    private HashMap<Position, Integer> distanceCache = new HashMap<>();

    public FieldSearch(Field game) {
        field = game;
    }

    public Deque<Position> pathTo(Position from, Position to) {
        if (from.equals(lastStart) && distanceCache.getOrDefault(to, 0) != 0) {
            return backtrack(to);
        }
        if (!from.equals(lastStart)) {
            queue = new ArrayDeque<>();
        }

        queue.add(from);

        while (distanceCache.getOrDefault(to, 0) == 0 && !queue.isEmpty()) {
            Position current = queue.remove();
            for (Position direction : Position.DIRECTIONS) {
                Position pos = current.sum(direction);
                if (!field.isFree(pos)) {
                    distanceCache.put(pos, -1);
                }
                if (pos.equals(to) || !distanceCache.containsKey(pos)) {
                    distanceCache.put(pos, distanceCache.getOrDefault(current, 0) + 1);
                    queue.add(pos);
                }
            }
        }
        lastStart = from;
        return backtrack(to);
    }

    private Deque<Position> backtrack(Position start) {
        Deque<Position> path = new ArrayDeque<>(distanceCache.get(start));
        int level = distanceCache.get(start);
        Position pos = new Position(start.getX(), start.getY());

        for (; level != 0; --level) {
            for (Position direction : Position.DIRECTIONS) {
                if (distanceCache.getOrDefault(pos.sum(direction), 0) == level - 1) {
                    path.addFirst(new Position(-direction.getX(), -direction.getY()));
                    pos = pos.sum(direction);
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
