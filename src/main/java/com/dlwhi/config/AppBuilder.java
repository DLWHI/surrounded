package com.dlwhi.config;

import com.beust.jcommander.JCommander;

import com.dlwhi.application.AppPresenter;
import com.dlwhi.model.ModelFacade;
import com.dlwhi.view.ConsoleGameView;

public class AppBuilder {
    private static final GameBuilder gameBuilder = new GameBuilder();
    private static final ConfigLoader cfgLoader = new ConfigLoader();

    public static AppPresenter build(String[] args) {
        
        JCommander configurator = JCommander.newBuilder()
        .addObject(gameBuilder)
        .addObject(cfgLoader)
        .build();
        configurator.parse(args);
        cfgLoader.load();
        
        ModelFacade model = new ModelFacade(gameBuilder);
        ConsoleGameView view = new ConsoleGameView();
        
        AppPresenter app = new AppPresenter(view, model);

        return app;
    }
}
