package com.linqing.tank.abstractFactory;

import com.linqing.tank.*;
import lombok.Data;


@Data
public abstract class BaseTank extends GameObject {



    public BaseTank(int x, int y,int width,int height) {
        super(x,y,width,height);
    }
}
