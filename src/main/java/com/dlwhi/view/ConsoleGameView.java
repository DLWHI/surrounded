package com.dlwhi.view;

import java.io.IOException;
import java.util.Scanner;

import com.dlwhi.application.Bindings;
import com.diogonunes.jcolor.Command;
import com.diogonunes.jcolor.Ansi;

public class ConsoleGameView implements GameView {
    private final Scanner input = new Scanner(System.in).useDelimiter("\\s*");
    private final Bindings binds = Bindings.get();

    private boolean toClose = false;

    @Override
    public void show(String data) {
        System.out.println(data);
    }

    public void clear() {
        System.out.println(Ansi.colorize(Command.CLEAR_SCREEN()));
    }

    @Override
    public String waitInput() throws IOException {
        while (true) {
            String command = binds.getCommand(input.next().toUpperCase());
            if (command != null) {
                if ("exit".equals(command)) {
                    toClose = true;
                }
                return command;
            }
            System.out.println("Unknown key provided");
        }
    }

    @Override
    public boolean shouldClose() {
        return toClose;
    }


    @Override
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
    }
}
