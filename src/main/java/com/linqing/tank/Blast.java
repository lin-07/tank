package com.linqing.tank;

import lombok.Data;

import java.awt.*;

@Data
public class Blast {

    private int x;
    private int y;
    public static int WIDTH = ResourceManager.getInstance().getBufferedImageArray()[0].getWidth();
    public static int HEIGHT = ResourceManager.getInstance().getBufferedImageArray()[0].getHeight();
    private int step = 0;
    private TankFrame tankFrame;
    private boolean live = true;

    public Blast(int x,int y,TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Thread(new Runnable() {
            public void run() {
                new Audio("audio/explode.wav").play();
            }
        }).start();
    }

    public void paint(Graphics g){
        g.drawImage(ResourceManager.getInstance().getBufferedImageArray()[step++],x,y,null);
        if(step >= ResourceManager.getInstance().getBufferedImageArray().length){
            this.live = false;
        }
    }
}
