package com.linqing.tank.abstractFactory;

import com.linqing.tank.Direction;
import com.linqing.tank.Group;
import com.linqing.tank.TankFrame;
import com.linqing.tank.facade.GameModel;

public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Direction direction, Group group, GameModel gameModel);

    public abstract BaseBullet createBullet(int x,int y,Direction direction,Group group,GameModel gameModel);

    public abstract BaseBlast createBlast(int x,int y,GameModel gameModel);
}