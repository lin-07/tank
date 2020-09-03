package com.linqing.tank.observe;

import com.linqing.tank.Tank;

/**
 * @author lin-PC
 */
public interface TankFireObserver<T> {

    /**
     * 按下ctrl键时，主站坦克 开火，开火身体
     * 开火又设计为策略 区分
     *
     */



    void handler(TankFireEvent<T> tankFireEvent);
}
