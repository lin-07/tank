package com.linqing.tank.strategy;

import com.linqing.tank.*;
import com.linqing.tank.facade.GameModel;

/**
 * @author lin-PC
 */
public class MoreDirectionFireStrategy implements FireStrategy<Tank> {

    public void fire(Tank tank) {
        int bx = tank.getX() + tank.getWidth()/2 - ResourceManager.getInstance().getBufferedImage("bulletD").getWidth()/2;
        int by = tank.getY() + tank.getHeight()/2 - ResourceManager.getInstance().getBufferedImage("bulletD").getHeight()/2;
        for(Direction direction : Direction.values()){
            GameModel.getInstance().gameFactory.createBullet(bx,by,direction,tank.getGroup());
        }
        if(tank.getGroup() == Group.good){
            new Thread(new Runnable() {
                public void run() {
                    new Audio("audio/tank_fire.wav").play();
                }
            }).start();
        }
    }
}
