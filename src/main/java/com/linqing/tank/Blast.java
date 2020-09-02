package com.linqing.tank;

import com.linqing.tank.abstractFactory.BaseBlast;
import com.linqing.tank.facade.GameModel;
import lombok.Data;

import java.awt.*;

@Data
public class Blast extends BaseBlast {

    private int step = 0;

    public Blast(int x,int y){
        super(x,y,
                ResourceManager.getInstance().getBufferedImageArray()[0].getWidth(),
                ResourceManager.getInstance().getBufferedImageArray()[0].getHeight());
        new Thread(new Runnable() {
            public void run() {
                new Audio("audio/explode.wav").play();
            }
        }).start();
        GameModel.getInstance().iterator.add(this);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(ResourceManager.getInstance().getBufferedImageArray()[step++],this.getX(),this.getY(),null);
        if(step >= ResourceManager.getInstance().getBufferedImageArray().length){
            this.setLive(false);
        }
    }
}
