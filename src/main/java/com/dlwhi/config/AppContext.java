package com.dlwhi.config;

import com.beust.jcommander.JCommander;
import com.dlwhi.application.App;
import com.dlwhi.controller.GameModelController;
import com.dlwhi.model.ModelFacade;
import com.dlwhi.view.ConsoleGameView;

public class AppContext {
    private static final FieldConfigurator gameBuilder = new FieldConfigurator();
    private static final ConfigLoader cfgLoader = new ConfigLoader();

    public static App getApp(String[] args) {

        JCommander configurator = JCommander.newBuilder()
                .addObject(gameBuilder)
                .addObject(cfgLoader)
                .build();
        configurator.parse(args);
        cfgLoader.load();

        ModelFacade model = new ModelFacade(gameBuilder.getGenerator());
        GameModelController controller = new GameModelController(model);
        ConsoleGameView view = new ConsoleGameView(controller, model);

        return view;
    }
}
