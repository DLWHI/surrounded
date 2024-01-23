package com.dlwhi.interfaces;

import com.dlwhi.field.Position;

public interface IPrivateModel extends IPublicModel {
    public void movePlayer(Position direction);

    public void finish();

    public void update();

    public void updateAllEnemies();

    public void updateOneEnemy();
}
