package com.linqing.tank.abstractFactory;

import com.linqing.tank.*;
import com.linqing.tank.facade.GameModel;

/**
 * @author lin-PC
 */
public class DefaultFactory extends GameFactory {

    private DefaultFactory(){}

    private static DefaultFactory defaultFactory = new DefaultFactory();

    public static DefaultFactory getInstance(){
        return defaultFactory;
    }


    @Override
    public BaseTank createTank(int x, int y, Direction direction, Group group, GameModel gameModel) {
        return new Tank(x,y,direction,group,gameModel);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Direction direction, Group group, GameModel gameModel) {
        return new Bullet(x,y,direction,group,gameModel);
    }

    @Override
    public BaseBlast createBlast(int x, int y, GameModel gameModel) {
        return new Blast(x,y,gameModel);
    }
}
