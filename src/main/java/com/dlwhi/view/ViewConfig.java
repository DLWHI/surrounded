package com.dlwhi.view;

import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import com.diogonunes.jcolor.Ansi;

import com.dlwhi.config.ConfigValidator;
import com.dlwhi.model.Entity;

@Parameters(separators = "=")
public class ViewConfig {
    private static ViewConfig instance;

    @DynamicParameter(names = "char.", validateWith = ConfigValidator.class)
    private Map<String, String> chars = new HashMap<>();
    @DynamicParameter(names = "color.")
    private Map<String, String> colors = new HashMap<>();
    @DynamicParameter(names = "background.")
    private Map<String, String> backgrounds = new HashMap<>();

    @Parameter(names = "clearFrame", arity = 1)
    private boolean clearFrame = true;

    private ViewConfig() {
        chars.put("enemy", "X");
        chars.put("player", "O");
        chars.put("wall", "#");
        chars.put("escape", "o");
        chars.put("empty", "-");
    }

    public static ViewConfig get() {
        if (instance == null) {
            instance = new ViewConfig();
        }
        return instance;
    }

    public String getOutputFor(Entity entity) {
        String entityName = entity.toString();
        return Ansi.colorize(
            chars.get(entityName),
            ColorParser.mapText(colors.getOrDefault(entityName, "white")),
            ColorParser.mapBack(backgrounds.getOrDefault(entityName, "black"))
        );
    }

    public boolean shouldClear() {
        return clearFrame;
    }

    public boolean movesSequential() {
        return clearFrame;
    }
}
