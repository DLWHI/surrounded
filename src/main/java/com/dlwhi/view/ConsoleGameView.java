package com.dlwhi.view;

import java.io.IOException;
import java.util.Scanner;

import com.dlwhi.application.App;
import com.dlwhi.controller.Controller;
import com.dlwhi.model.Entity;
import com.dlwhi.model.GameModelPublic;
import com.dlwhi.observer.GameObserver;
import com.diogonunes.jcolor.Command;
import com.diogonunes.jcolor.Ansi;

public class ConsoleGameView implements App, GameObserver {
    private final Controller controller;
    private final GameModelPublic model;

    private final Scanner input = new Scanner(System.in).useDelimiter("\\s*");
    private final Bindings binds = Bindings.get();
    private final ViewConfig cfg = ViewConfig.get();

    private boolean toClose = false;

    public ConsoleGameView(Controller controller, GameModelPublic model) {
        this.controller = controller;
        this.model = model;

        model.attachObserver(this);
    }

    public void showField() {
        if (cfg.shouldClear()) {
            clear();
        }
        Entity[][] field = model.getField();
        for (Entity[] fieldRow : field) {
            for (Entity entity : fieldRow) {
                System.out.print(cfg.getOutputFor(entity));
            }
            System.out.println();
        }
    }

    public void showEnd(String message) {
        System.out.println(message);
        System.out.println("Retry?");
        if (requestConfirm()) {
            controller.reset();
        } else {
            toClose = true;
        }
    }

    public void clear() {
        System.out.println(Ansi.colorize(Command.CLEAR_SCREEN()));
    }

    public void waitInput() {
        String command = binds.getCommand(input.next().toUpperCase());
        if (command == null) {
            System.out.println("Unknown key provided");
        } else if ("exit".equals(command)) {
            toClose = true;
        } else {
            controller.accept(command);
        }
    }

    public boolean shouldClose() {
        return toClose;
    }

    public boolean requestConfirm() {
        while (true) {
            String command = binds.getCommand(input.next().toUpperCase());
            if ("accept".equals(command)) {
                return true;
            } else if ("decline".equals(command)) {
                return false;
            }
            System.out.println("Plese enter either accept or decline command");
        }
    }

    @Override
    public void close() throws IOException {
        toClose = true;
        model.detachObserver(this);
    }

    @Override
    public void run() {
        showField();
        while (!toClose) {
            waitInput();            
        }
    }

    @Override
    public void notifyChanged() {
        showField();
        model.attachObserver(this);
    }

    @Override
    public void notifyLoss() {
        showEnd("You lost.");
    }

    @Override
    public void notifyVictory() {
        showEnd("You escaped.");
    }
}
