package com.linqing.tank.abstractFactory;

import lombok.Data;

import java.awt.*;

@Data
public abstract class BaseBullet {

    private boolean live = true;

    /**
     * 创建
     * @param g
     */
    public abstract void paint(Graphics g);

    public abstract void collision(BaseTank tank);

    protected void die() {
        this.live = false;
    }

}
