package com.dlwhi.controller;

import java.util.HashMap;

import com.dlwhi.model.GameModelPrivate;
import com.dlwhi.model.Position;

public class GameModelController implements Controller {
    private final GameModelPrivate model;

    private final HashMap<String, Position> directionsMapping = new HashMap<>();

    public GameModelController(GameModelPrivate model) {
        this.model = model;

        directionsMapping.put("move_left", Position.DIRECTION_LEFT);
        directionsMapping.put("move_up", Position.DIRECTION_UP);
        directionsMapping.put("move_right", Position.DIRECTION_RIGHT);
        directionsMapping.put("move_down", Position.DIRECTION_DOWN);
    }

    @Override
    public void accept(String command) {
        Position dir = directionsMapping.get(command);
        if (dir != null) {
            model.movePlayer(dir);
        } else if ("fortfeit".equals(command)) {
            model.fortfeit();
        }
    }

    @Override
    public void reset() {
        model.restart();
    }
    
}
