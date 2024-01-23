package com.dlwhi.application;

import com.dlwhi.config.AppBuilder;

public class Main {
    public static void main(String[] args) {
        try (App game = AppBuilder.build(args)) {
            game.run();
        } catch (Exception e) {
            System.err.println("Dies of cringe");
            e.printStackTrace();
        }
    }
}
