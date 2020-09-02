package com.linqing.tank.abstractFactory;

import com.linqing.tank.*;
import com.linqing.tank.facade.GameModel;

/**
 * @author lin-PC
 */
public class DefaultFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Group group) {
        return new Tank(x,y,group);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction direction, Group group) {
        return new Bullet(x,y,direction,group);
    }

    @Override
    public BaseBlast createBlast(int x, int y) {
        return new Blast(x,y);
    }
}
