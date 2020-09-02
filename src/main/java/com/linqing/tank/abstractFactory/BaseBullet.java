package com.linqing.tank.abstractFactory;

import com.linqing.tank.GameObject;
import lombok.Data;

import java.awt.*;

/**
 * @author lin-PC
 */
@Data
public abstract class BaseBullet extends GameObject {



    public BaseBullet(int x,int y,int width,int height){
        super(x,y,width,height);

    }



}
