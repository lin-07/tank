package com.linqing.tank.observe;

import com.linqing.tank.Tank;

/**
 * @author lin-PC
 */
public interface TankFireObserver<T> {

    void fire(TankFireEvent<T> tankFireEvent);
}
