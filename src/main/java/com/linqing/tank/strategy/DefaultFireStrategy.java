package com.linqing.tank.strategy;

import com.linqing.tank.*;
import com.linqing.tank.abstractFactory.BaseBullet;
import com.linqing.tank.abstractFactory.BaseTank;
import com.linqing.tank.decorator.RectDecorator;
import com.linqing.tank.decorator.TailDecorator;
import com.linqing.tank.facade.GameModel;

/**
 * @author lin-PC
 */
public class DefaultFireStrategy implements FireStrategy<Tank> {

    // public boolean isFire() {
    //     // return tank.getRandom().nextInt(100) > 95 && tank.getGroup() == Group.bad;
    // }


    public void fire(Tank tank) {
        int bx = tank.getX() + tank.getWidth()/2 - ResourceManager.getInstance().getBufferedImage("bulletD").getWidth()/2;
        int by = tank.getY() + tank.getHeight()/2 - ResourceManager.getInstance().getBufferedImage("bulletD").getHeight()/2;
        // GameModel.getInstance().gameFactory.createBullet(bx,by,tank.getDirection(),tank.getGroup());
        BaseBullet bullet = GameModel.getInstance().gameFactory.createBullet(bx, by, tank.getDirection(), tank.getGroup());
        // GameModel.getInstance().iterator.add(new TailDecorator(new RectDecorator(bullet)));

    }
}
