package com.linqing.tank.decorator;

import com.linqing.tank.GameObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lin-PC
 */
public class DecoratorChain extends GoDecorator {

    private List<GoDecorator> decorators = new LinkedList<GoDecorator>();

    public DecoratorChain(GameObject gameObject) {
        super(gameObject);
        decorators.add(new RectDecorator(gameObject));
        decorators.add(new TailDecorator(gameObject));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(GoDecorator goDecorator : decorators){
            goDecorator.paint(g);
            super.setLive(goDecorator.isLive());
        }
    }
}
