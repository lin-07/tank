package com.linqing.tank.decorator;

import com.linqing.tank.GameObject;

import java.awt.*;

/**
 * @author lin-PC
 */
public class TailDecorator extends GoDecorator {

    public TailDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Color color = g.getColor();
        g.setColor(Color.BLUE);
        g.drawLine(gameObject.getX(),gameObject.getY(),gameObject.getX() + gameObject.getWidth(),gameObject.getY() + gameObject.getHeight());
        g.setColor(color);
        super.setX(gameObject.getX());
        super.setY(gameObject.getY());
        super.setLive(gameObject.isLive());
    }
}
