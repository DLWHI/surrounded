package com.dlwhi.application;

import com.dlwhi.config.AppContext;

public class Main {
    public static void main(String[] args) {
        try (App game = AppContext.getApp(args)) {
            game.run();
        } catch (Exception e) {
            System.err.println("Dies of cringe");
            e.printStackTrace();
        }
    }
}
