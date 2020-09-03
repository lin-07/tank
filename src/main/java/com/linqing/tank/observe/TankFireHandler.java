package com.linqing.tank.observe;

import com.linqing.tank.Tank;

/**
 * @author lin-PC
 */
public class TankFireHandler implements TankFireObserver<Tank> {


    public void fire(TankFireEvent<Tank> tankFireEvent) {
        Tank tank = tankFireEvent.getSource();
        tank.fire();
    }
}
