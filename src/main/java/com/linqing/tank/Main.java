package com.linqing.tank;


import java.util.concurrent.TimeUnit;

/**
 * @author lin-PC
 */
public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyManager.getInstance().getKey("initTankCount"));

        for (int i = 0; i < initTankCount ; i++) {
            tankFrame.tanks.add(new Tank(i * 80,200,Direction.DOWM,Group.bad,tankFrame));
        }
        // new Thread(new Runnable() {
        //     public void run() {
        //         new Audio("audio/war1.wav").loop();
        //     }
        // }).start();

        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankFrame.repaint();
        }
    }

}
