package com.linqing.tank;

import com.linqing.tank.abstractFactory.BaseBlast;
import com.linqing.tank.facade.GameModel;
import lombok.Data;

import java.awt.*;

@Data
public class Blast extends BaseBlast {

    private int x;
    private int y;
    public static int WIDTH = ResourceManager.getInstance().getBufferedImageArray()[0].getWidth();
    public static int HEIGHT = ResourceManager.getInstance().getBufferedImageArray()[0].getHeight();
    private int step = 0;

    public Blast(int x,int y){
        this.x = x;
        this.y = y;
        new Thread(new Runnable() {
            public void run() {
                new Audio("audio/explode.wav").play();
            }
        }).start();
        GameModel.getInstance().iterator.add(this);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(ResourceManager.getInstance().getBufferedImageArray()[step++],x,y,null);
        if(step >= ResourceManager.getInstance().getBufferedImageArray().length){
            this.setLive(false);
        }
    }
}
