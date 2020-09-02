package com.linqing.tank.cor;

/**
 * 碰撞器
 * @param <T>
 */
public interface Collider<T> {

    void collideWith(T o1,T o2);
}
