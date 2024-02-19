package com.dlwhi.config;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.dlwhi.exceptions.IllegalParametersException;
import com.dlwhi.model.FieldGenerator;
import com.dlwhi.model.Position;

@Parameters(separators = "=")
public class FieldConfigurator {
    @Parameter(names = {"--enemiesCount"})
    private int enemyCount;
    @Parameter(names = {"--wallsCount"})
    private int wallCount;
    @Parameter(names = {"--size"})
    private int fieldSize;

    public FieldGenerator getGenerator() {
        if (fieldSize * fieldSize * 0.3 <= enemyCount + wallCount) {
            throw new IllegalParametersException();
        }

        return new FieldGenerator(
            new Position(fieldSize, fieldSize),
            wallCount,
            enemyCount
        );
    }
}
