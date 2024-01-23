package com.dlwhi.application;

import com.dlwhi.exceptions.InvalidCommandException;
import com.dlwhi.field.Position;
import com.dlwhi.interfaces.IController;
import com.dlwhi.interfaces.IPrivateModel;

public class FieldController implements IController {
    private final IPrivateModel model;

    public FieldController(IPrivateModel model) {
        this.model = model;
    }

    @Override
    public void accept(String command) {
        switch (command) {
            case "move_left":
                model.movePlayer(Position.DIRECTION_LEFT);
                break;
            case "move_up":
                model.movePlayer(Position.DIRECTION_UP);
                break;
            case "move_right":
                model.movePlayer(Position.DIRECTION_RIGHT);
                break;
            case "move_down":
                model.movePlayer(Position.DIRECTION_DOWN);
                break;
            default:
                throw new InvalidCommandException(command);
        }
        model.update();
    }

    @Override
    public void confirm() {
        model.updateOneEnemy();
    }

    @Override
    public void reset() {
        
    }
}
