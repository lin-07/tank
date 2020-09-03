package com.linqing.tank.observe;


public class TankFireEvent<T> {

    private T t;

    public TankFireEvent(T t){
        this.t = t;
    }

    public T getSource(){
        return t;
    }

}
