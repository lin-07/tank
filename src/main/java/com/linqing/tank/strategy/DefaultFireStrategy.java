package com.linqing.tank.strategy;

import com.linqing.tank.Audio;
import com.linqing.tank.Bullet;
import com.linqing.tank.Group;
import com.linqing.tank.Tank;
import com.linqing.tank.abstractFactory.BaseTank;
import com.linqing.tank.facade.GameModel;

/**
 * @author lin-PC
 */
public class DefaultFireStrategy implements FireStrategy<BaseTank> {

    // public boolean isFire() {
    //     // return tank.getRandom().nextInt(100) > 95 && tank.getGroup() == Group.bad;
    // }


    public void fire(BaseTank tank) {
        int bx = tank.getX() + Tank.width/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.width/2 - Bullet.HEIGHT/2;
        GameModel.getInstance().gameFactory.createBullet(bx,by,tank.getDirection(),tank.getGroup());
        if(tank.getGroup() == Group.good){
            new Thread(new Runnable() {
                public void run() {
                    new Audio("audio/tank_fire.wav").play();
                }
            }).start();
        }
    }
}
