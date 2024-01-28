package com.dlwhi.application;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;

import com.dlwhi.field.Position;
import com.dlwhi.model.Entity;
import com.dlwhi.model.GameModel;
import com.dlwhi.model.GameStatus;
import com.dlwhi.view.GameView;

public final class AppPresenter implements Runnable, Closeable {
    private GameView view;
    private GameModel model;

    private final HashMap<String, Position> directionsMapping = new HashMap<>();
    private final HashMap<GameStatus, String> statusMessageMapping = new HashMap<>();

    private final GameConfig cfg = GameConfig.get();

    public AppPresenter(GameView view, GameModel model) {
        this.view = view;
        this.model = model;

        directionsMapping.put("move_left", Position.DIRECTION_LEFT);
        directionsMapping.put("move_up", Position.DIRECTION_UP);
        directionsMapping.put("move_right", Position.DIRECTION_RIGHT);
        directionsMapping.put("move_down", Position.DIRECTION_DOWN);

        statusMessageMapping.put(GameStatus.PLAYER_WON, "You escaped!");
        statusMessageMapping.put(GameStatus.ENEMY_WON, "They cathced you.");
    }

    @Override
    public void run() {
        try {
            while (!view.shouldClose()) {
                printField(model.getField());
                handleInput();
                checkStatus();
            }
        } catch (IOException e) {
            System.err.println("Dies of cringe");
            System.err.println("Reason:");
            System.err.println(e.getMessage());
        }
    }

    private void printField(Entity[][] field) {
        if (cfg.shouldClear()) {
            view.clear();
        }
        StringBuilder printRow = new StringBuilder();
        for (Entity[] fieldRow : field) {
            for (Entity entity : fieldRow) {
                switch (entity) {
                    case PLAYER:
                    printRow.append(cfg.getOutputFor("player"));
                    break;
                    case ENEMY:
                    printRow.append(cfg.getOutputFor("enemy"));
                        break;
                    case WALL:
                        printRow.append(cfg.getOutputFor("wall"));
                        break;
                    case ESCAPE:
                        printRow.append(cfg.getOutputFor("escape"));
                        break;
                    case EMPTY:
                        printRow.append(cfg.getOutputFor("empty"));
                }
            }
            view.show(printRow.toString());
            printRow.delete(0, printRow.length());
        }
    }

    private void handleInput() throws IOException {
        Position dir = directionsMapping.get(view.waitInput());
        if (dir != null) {
            model.movePlayer(dir);
        }
    }

    private void checkStatus() throws IOException {
        GameStatus status = model.status();
        switch (status) {
            case ONGOING:
                break;
            case PLAYER_WON:
            case ENEMY_WON:
                view.show(statusMessageMapping.get(status) + " Retry?");
                if (view.requestConfirm()) {
                    model.restart();
                } else {
                    view.close();
                }
        }
    }

    @Override
    public void close() throws IOException {
        view.close();
    }
}