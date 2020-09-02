package com.linqing.tank.abstractFactory;

import com.linqing.tank.*;
import com.linqing.tank.facade.GameModel;
import lombok.Data;

import java.awt.*;

@Data
public abstract class BaseTank extends GameObject {



    public BaseTank(int x, int y,int width,int height) {
        super(x,y,width,height);
    }

    /**
     * 开火
     */
    protected abstract void fire();

}
