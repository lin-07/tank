package com.linqing.tank;

import com.linqing.tank.facade.GameModel;
import lombok.Data;

import java.awt.*;

@Data
public abstract class GameObject {

    private boolean live = true;

    public GameObject(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * 创建
     * @param g
     */
    public abstract void paint(Graphics g);

    public void die() {
        this.live = false;
    }


}
