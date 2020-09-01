package com.linqing.tank.strategy;

public interface FireStrategy<T> {


    /**
     * 开火
     * @param t
     */
    void fire(T t);

}
