package com.linqing.tank;


/**
 * @author lin-PC
 */
public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();


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
