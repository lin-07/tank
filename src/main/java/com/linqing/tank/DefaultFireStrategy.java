package com.linqing.tank;

/**
 * @author lin-PC
 */
public class DefaultFireStrategy implements FireStrategy<Tank> {

    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.width/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.width/2 - Bullet.HEIGHT/2;
        tank.getTf().bullets.add(new Bullet(bx,by,tank.getDirection(),tank.getGroup(),tank.getTf()));
        if(tank.getGroup() == Group.good){
            new Thread(new Runnable() {
                public void run() {
                    new Audio("audio/tank_fire.wav").play();
                }
            }).start();
        }
    }
}
