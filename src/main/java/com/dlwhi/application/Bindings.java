package com.dlwhi.application;

import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.DynamicParameter;
import com.dlwhi.config.ConfigValidator;

public class Bindings {
    private static Bindings instance = null;

    @DynamicParameter(names = "key.", validateWith = ConfigValidator.class)
    private Map<String, String> binds = new HashMap<>();

    private Bindings() {
    }

    public static Bindings get() {
        if (instance == null) {
            instance = new Bindings();
        }
        return instance;
    }

    public String getCommand(String name) {
        return binds.get(name);
    }
}
