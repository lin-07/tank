package com.linqing.tank.decorator;

import com.linqing.tank.GameObject;

import java.awt.*;

/**
 * @author lin-PC
 * 装饰者可以装饰任意东西 东西可以被多个装饰器装饰
 */
public class GoDecorator extends GameObject {

    protected GameObject gameObject;

    public GoDecorator(GameObject gameObject){
        super(gameObject.getX(),gameObject.getY(),gameObject.getWidth(),gameObject.getHeight());
        this.gameObject = gameObject;
    }


    @Override
    public void paint(Graphics g) {
        gameObject.paint(g);
    }
}
