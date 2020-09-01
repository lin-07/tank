package com.linqing.tank.strategy;

import com.linqing.tank.*;
import com.linqing.tank.abstractFactory.BaseTank;

/**
 * @author lin-PC
 */
public class MoreDirectionFireStrategy implements FireStrategy<BaseTank> {

    public void fire(BaseTank tank) {
        int bx = tank.getX() + Tank.width/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.width/2 - Bullet.HEIGHT/2;
        for(Direction direction : Direction.values()){
            tank.getGameModel().gameFactory.createBullet(bx,by,direction,tank.getGroup(),tank.getGameModel());
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
