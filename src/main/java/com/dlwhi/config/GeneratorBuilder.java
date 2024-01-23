package com.dlwhi.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import com.dlwhi.exceptions.IllegalParametersException;
import com.dlwhi.field.GameGenerator;
import com.dlwhi.field.Position;

@Parameters(separators = "=")
public class GeneratorBuilder {
    @Parameter(names = {"--enemiesCount"})
    private int enemyCount;
    @Parameter(names = {"--wallsCount"})
    private int wallCount;
    @Parameter(names = {"--size"})
    private int fieldSize;

    public GameGenerator build() {
        if (fieldSize * fieldSize * 0.3 <= enemyCount + wallCount) {
            throw new IllegalParametersException();
        }
        Position fSize = new Position(fieldSize, fieldSize);
        return new GameGenerator(fSize, wallCount, enemyCount);
    }
}
