package com.linqing.tank.observe;

import com.linqing.tank.Group;
import com.linqing.tank.Tank;

/**
 * @author lin-PC
 */
public class TankFireDefaultHandler implements TankFireObserver<Tank> {


    public boolean handler(TankFireEvent<Tank> tankFireEvent) {
        Tank tank = tankFireEvent.getSource();
        tank.fire();
        if(tank.getGroup() == Group.good){
            return true;
        }else{
            return false;
        }
    }
}
