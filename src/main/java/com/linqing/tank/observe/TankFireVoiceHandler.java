package com.linqing.tank.observe;

import com.linqing.tank.Audio;
import com.linqing.tank.Group;
import com.linqing.tank.Tank;

/**
 * @author lin-PC
 */
public class TankFireVoiceHandler implements TankFireObserver<Tank> {

    public boolean handler(TankFireEvent<Tank> tankFireEvent) {
        new Thread(new Runnable() {
            public void run() {
                new Audio("audio/tank_fire.wav").play();
            }
        }).start();
        return true;
    }
}
