package com.linqing.tank.abstractFactory;

import com.linqing.tank.GameObject;
import lombok.Data;

import java.awt.*;

/**
 * @author lin-PC
 */
@Data
public abstract class BaseBullet extends GameObject {

    private Rectangle rectangle = new Rectangle();

    public BaseBullet(int x,int y,int width,int height){
        super(x,y,width,height);
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = width;
        rectangle.height = height;
    }

    /**
     * 更新rectangle
     */
    protected void updateRectangle(){
        this.rectangle.x = super.getX();
        this.rectangle.y = super.getY();
    }

}
