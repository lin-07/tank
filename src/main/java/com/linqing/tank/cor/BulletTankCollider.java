package com.linqing.tank.cor;

import com.linqing.tank.Blast;
import com.linqing.tank.Bullet;
import com.linqing.tank.GameObject;
import com.linqing.tank.Tank;
import com.linqing.tank.abstractFactory.BaseTank;
import com.linqing.tank.facade.GameModel;

/**
 * @author lin-PC
 */
public class BulletTankCollider<GameObject> implements Collider<GameObject> {

    public boolean collideWith(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            return collision((Bullet) o1,(Tank)o2);
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            return collision((Bullet) o2,(Tank)o1);
        }else{
            return true;
        }
    }

    private boolean collision(Bullet bullet, BaseTank tank) {
        if(bullet.getGroup() == tank.getGroup()){
            return true;
        }
        if(bullet.getRectangle().intersects(tank.getRectangle())){
            bullet.die();
            tank.die();
            int bx = tank.getX() + Tank.width/2 - Blast.WIDTH/2;
            int by = tank.getY() + Tank.height/2 - Blast.HEIGHT/2;
            GameModel.getInstance().gameFactory.createBlast(bx,by);
            return false;
        }
        return true;
    }


}
