package com.linqing.tank.abstractFactory;

import com.linqing.tank.*;
import com.linqing.tank.facade.GameModel;
import lombok.Data;

import java.awt.*;

@Data
public abstract class BaseTank extends GameObject {



    private Rectangle rectangle = new Rectangle();

    public BaseTank(int x, int y,int width,int height) {
        super(x,y,width,height);

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = width;
        rectangle.height = height;
    }

    /**
     * 开火
     */
    protected abstract void fire();

    /**
     * 边界检测
     */
    protected void borderDetection() {
        if(this.getX() < 2){
            this.setX(2);
        }
        if(this.getY() <32){
            this.setY(32);
        }
        if(this.getX() > TankFrame.GAME_WIDTH  - this.getWidth()){
            this.setX(TankFrame.GAME_WIDTH - this.getWidth());
        }
        if(this.getY() > TankFrame.GAME_HEIGHT - this.getHeight()){
            this.setY(TankFrame.GAME_HEIGHT - this.getHeight());
        }
    }

    /**
     * 更新rectangle
     */
    protected void updateRectangle(){
        this.rectangle.x = super.getX();
        this.rectangle.y = super.getY();
    }


}
