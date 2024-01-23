package com.dlwhi.application;

import java.io.Closeable;
import java.io.IOException;
import com.dlwhi.view.ConsoleView;

public final class App implements Runnable, Closeable {
    private ConsoleView view;

    public void setView(ConsoleView view) {
        this.view = view;
    }

    @Override
    public void run() {
        view.show();
        try {
            while (!view.shouldClose()) {
                view.waitInput();
            }
        } catch (IOException e) {
            System.err.println("Dies of cringe");
            System.err.println("Reason:");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        view.close();
    }
}