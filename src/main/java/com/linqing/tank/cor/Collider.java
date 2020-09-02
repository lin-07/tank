package com.linqing.tank.cor;

/**
 * 碰撞器
 * @param <T>
 */
public interface Collider<T> {

    /**
     * 是否继续
     * @param o1
     * @param o2
     * @return
     */
    boolean collideWith(T o1,T o2);
}
