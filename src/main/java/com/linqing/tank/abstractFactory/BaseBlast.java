package com.linqing.tank.abstractFactory;

import lombok.Data;

import java.awt.*;

@Data
public abstract class BaseBlast {

    private boolean live = true;

    /**
     * 创建
     * @param g
     */
    public abstract void paint(Graphics g);
}
