package com.linqing.tank.decorator;

import com.linqing.tank.GameObject;

import java.awt.*;

/**
 * @author lin-PC
 */
public class RectDecorator extends GoDecorator {

    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.yellow);
        g.drawRect(gameObject.getX(),gameObject.getY(),gameObject.getWidth(),gameObject.getHeight());
        g.setColor(color);
        super.setX(gameObject.getX());
        super.setY(gameObject.getY());
        super.setLive(gameObject.isLive());
    }
}
