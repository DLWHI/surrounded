package com.dlwhi.ai;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Random;
import java.util.Queue;

import com.dlwhi.field.Position;
import com.dlwhi.interfaces.IField;

public class FieldSearch {
    private final IField field;
    private final Position fieldSize;
    private final Random random = new Random();

    public FieldSearch(IField game) {
        field = game;
        fieldSize = game.getFieldSize();
    }

    public List<Position> pathTo(Position from, Position to) {
        Queue<Position> queue = new ArrayDeque<>();
        HashMap<Position, Integer> dist = new HashMap<>();

        queue.add(from);

        while (!queue.isEmpty() && dist.getOrDefault(to, 0) == 0) {
            Position current = queue.remove();
            for (Position direction : Position.DIRECTIONS) {
                Position pos = new Position(current.getX(), current.getY());
                pos.move(direction.getX(), direction.getY());

                if (field.isFree(pos) && dist.getOrDefault(pos, 0) == 0) {
                    dist.put(pos, dist.getOrDefault(current, 0) + 1);
                    queue.add(pos);
                }
            }
        }
        return backtrack(dist, to);
    }

    private List<Position> backtrack(Map<Position, Integer> chain, Position start) {
        List<Position> path = new ArrayList<>(chain.get(start));
        int level = chain.get(start);

        for (; level != 0; --level) {
            for (Position direction : Position.DIRECTIONS) {
                Position pos = new Position(start.getX(), start.getY());
                pos.move(direction.getX(), direction.getY());
                
                if (chain.getOrDefault(pos, -1) + 1 == level) {
                    path.add(direction);
                    start = pos;
                }
            }
        }
        return path;
    }

    public Position generatePosition(Position start) {
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
