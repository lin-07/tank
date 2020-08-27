package com.linqing.tank;

/**
 * @author lin-PC
 */
public class MoreDirectionFireStrategy implements FireStrategy<Tank> {

    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.width/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.width/2 - Bullet.HEIGHT/2;
        for(Direction direction : Direction.values()){
            new Bullet(bx,by,direction,tank.getGroup(),tank.getTf());
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
