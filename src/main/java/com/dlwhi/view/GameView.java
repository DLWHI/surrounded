package com.dlwhi.view;

import java.io.Closeable;
import java.io.IOException;

public interface GameView extends Closeable {
    void show(String data);

    void clear();

    boolean shouldClose();

    boolean requestConfirm();

    String waitInput() throws IOException;
}
