package com.dlwhi.controller;

import java.util.HashMap;

import com.dlwhi.exceptions.IllegalCommandException;
import com.dlwhi.field.Position;
import com.dlwhi.interfaces.IController;
import com.dlwhi.interfaces.IPrivateModel;

public class FieldController implements IController {
    private final IPrivateModel model;
    private final HashMap<String, Position> directionsMapping = new HashMap<>();


    public FieldController(IPrivateModel model) {
        this.model = model;
        directionsMapping.put("move_left", Position.DIRECTION_LEFT);
        directionsMapping.put("move_up", Position.DIRECTION_UP);
        directionsMapping.put("move_right", Position.DIRECTION_RIGHT);
        directionsMapping.put("move_down", Position.DIRECTION_DOWN);
    }

    @Override
    public void accept(String command) {
        Position dir = directionsMapping.get(command);
        if (dir == null) {
            throw new IllegalCommandException(command);
        }
        model.movePlayer(dir);
        model.updateAll();
    }

    @Override
    public void reset() {
        
    }
}
