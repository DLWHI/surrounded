package com.dlwhi.application;

import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import com.diogonunes.jcolor.Ansi;

import com.dlwhi.config.ConfigValidator;
import com.dlwhi.view.ColorParser;

@Parameters(separators = "=")
public class GameConfig {
    private static GameConfig instance;

    @DynamicParameter(names = "char.", validateWith = ConfigValidator.class)
    private Map<String, String> chars = new HashMap<>();
    @DynamicParameter(names = "color.")
    private Map<String, String> colors = new HashMap<>();
    @DynamicParameter(names = "background.")
    private Map<String, String> backgrounds = new HashMap<>();

    @Parameter(names = "clearFrame", arity = 1)
    private boolean clearFrame = true;

    private GameConfig() {
        chars.put("enemy", "X");
        chars.put("player", "O");
        chars.put("wall", "▧");
        chars.put("goal", "□");
        chars.put("empty", " ");
    }

    public static GameConfig get() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    public String getOutputFor(String entity) {
        return Ansi.colorize(
            chars.get(entity),
            ColorParser.mapText(colors.getOrDefault(entity, "white")),
            ColorParser.mapBack(backgrounds.getOrDefault(entity, "black"))
        );
    }

    public boolean shouldClear() {
        return clearFrame;
    }

    public boolean movesSequential() {
        return clearFrame;
    }
}
