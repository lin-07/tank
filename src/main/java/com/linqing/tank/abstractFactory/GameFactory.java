package com.linqing.tank.abstractFactory;

import com.linqing.tank.Direction;
import com.linqing.tank.Group;

public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Group group);

    public abstract BaseBullet createBullet(int x,int y,Direction direction,Group group);

    public abstract BaseBlast createBlast(int x,int y);
}
