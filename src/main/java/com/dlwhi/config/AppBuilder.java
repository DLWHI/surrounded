package com.dlwhi.config;

import com.beust.jcommander.JCommander;

import com.dlwhi.application.App;
import com.dlwhi.application.FieldController;
import com.dlwhi.application.ModelFacade;
import com.dlwhi.view.ConsoleView;

public class AppBuilder {
    private static final GeneratorBuilder gameBuilder = new GeneratorBuilder();
    private static final ConfigLoader cfgLoader = new ConfigLoader();

    public static App build(String[] args) {
        App app = new App();

        JCommander configurator = JCommander.newBuilder()
                .addObject(gameBuilder)
                .addObject(cfgLoader)
                .build();
        configurator.parse(args);
        cfgLoader.load();

        ModelFacade model = new ModelFacade(gameBuilder.build());
        FieldController controller = new FieldController(model);
        ConsoleView view = new ConsoleView(controller, model);

        app.setView(view);

        return app;
    }
}
