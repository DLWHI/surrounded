package com.dlwhi.view;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

import com.dlwhi.application.Bindings;
import com.dlwhi.field.Position;
import com.dlwhi.interfaces.Entity;
import com.dlwhi.interfaces.IController;
import com.dlwhi.interfaces.IGameObserver;
import com.dlwhi.interfaces.IPublicModel;

public class ConsoleView implements IGameObserver, Closeable {
    private final Scanner input = new Scanner(System.in).useDelimiter("\\s*");
    private final GameConfig cfg = GameConfig.get();
    private final Bindings binds = Bindings.get();

    private final IController controller;
    private final IPublicModel model;

    private boolean closed = false;

    public ConsoleView(IController controller, IPublicModel model) {
        this.controller = controller;
        this.model = model;
        model.attachObserver(this);
    }

    public void show() {
        Entity[][] field = model.getField();
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                switch (field[x][y]) {
                    case PLAYER:
                        System.out.print(cfg.getOutputFor("player"));
                        break;
                    case ENEMY:
                        System.out.print(cfg.getOutputFor("enemy"));
                        break;
                    case WALL:
                        System.out.print(cfg.getOutputFor("wall"));
                        break;
                    case ESCAPE:
                        System.out.print(cfg.getOutputFor("escape"));
                        break;
                    case EMPTY:
                        System.out.print(cfg.getOutputFor("empty"));
                }
            }
            System.out.println();
        }
    }

    public void waitInput() throws IOException {
        String command = binds.getCommand(input.next().toUpperCase());
        if (command == null) {
            System.err.println("Unknown key provided");
        } else {
            controller.accept(command);
        }
    }

    public boolean shouldClose() {
        return closed;
    }

    @Override
    public void notifyPlayerMoved(Position dir) {
        show();
    }

    @Override
    public void notifyFinished(String message) {
        show();
        System.out.println(message);
    }

    @Override
    public void close() throws IOException {
        model.detachObserver(this);
        closed = true;
    }
}
