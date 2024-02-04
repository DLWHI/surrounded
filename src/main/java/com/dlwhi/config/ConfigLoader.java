package com.dlwhi.config;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.dlwhi.view.Bindings;
import com.dlwhi.view.ViewConfig;

@Parameters(separators = "=")
public class ConfigLoader {
    private final ViewConfig targetConfig = ViewConfig.get();
    private final Bindings bindsConfig = Bindings.get();

    @Parameter(names = "--profile")
    private String profile = "dev";

    public void load() {
        JCommander configurator = JCommander.newBuilder()
                .addObject(targetConfig)
                .addObject(bindsConfig)
                .build();

        String configFile = "/config/application-" + profile + ".properties";
        try {
            configurator.parse(FileLoader.loadAsLines(configFile, "#"));
        } catch (NullPointerException e) {
            System.err.println("No config for profile " + profile);
            System.exit(-1);
        }
    }

}
