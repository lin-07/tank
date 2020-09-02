package com.linqing.tank.cor;

import com.linqing.tank.GameObject;
import com.linqing.tank.Tank;

/**
 * @author lin-PC
 */
public class TankTankCollider implements Collider<GameObject> {


    public boolean collideWith(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            if(((Tank) o1).getRectangle().intersects(((Tank) o2).getRectangle())){
                collision((Tank) o1,(Tank)o2);
            }
        }
        return true;
    }

    private void collision(Tank o1, Tank o2){
        o1.setX(o1.getOldX());
        o1.setY(o1.getOldY());
        o2.setX(o2.getOldX());
        o2.setY(o2.getOldY());
    }
}
