package com.linqing.tank;

import lombok.Data;

import java.awt.*;

@Data
public abstract class GameObject {

    private boolean live = true;

    /**
     * 创建
     * @param g
     */
    public abstract void paint(Graphics g);

    public void die() {
        this.live = false;
    }


}
