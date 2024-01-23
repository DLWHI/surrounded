package com.dlwhi.ai;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Random;

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
