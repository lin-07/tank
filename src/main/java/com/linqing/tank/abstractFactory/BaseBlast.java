package com.linqing.tank.abstractFactory;

import com.linqing.tank.GameObject;
import lombok.Data;


@Data
public abstract class BaseBlast extends GameObject {

    public BaseBlast(int x,int y,int width,int height){
        super(x,y,width,height);
    }

}
