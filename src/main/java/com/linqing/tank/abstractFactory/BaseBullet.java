package com.linqing.tank.abstractFactory;

import com.linqing.tank.GameObject;
import lombok.Data;

import java.awt.*;

@Data
public abstract class BaseBullet extends GameObject {

    public abstract void collision(BaseTank tank);


}
