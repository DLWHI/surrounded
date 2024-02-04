package com.dlwhi.model;

public enum Entity {
    EMPTY("empty"),
    WALL("wall"),
    PLAYER("player"),
    ENEMY("enemy"),
    ESCAPE("escape");

    private final String name;

    private Entity(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
