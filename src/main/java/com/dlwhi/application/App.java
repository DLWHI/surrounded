package com.dlwhi.application;

import java.io.Closeable;

public interface App extends Closeable {
    void run();
}
