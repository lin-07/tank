package com.linqing.tank;

import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * @author lin-PC
 */
@Data
public class Tank {

    private int x;
    private int y;
    private Direction direction;
    private int speed = 5;
    private boolean move = false;
    public static int width = ResourceManager.goodTankU.getWidth();
    public static int height = ResourceManager.goodTankU.getHeight();
    private boolean live = true;
    private TankFrame tf;
    private Random random = new Random();
    private Group group;

    public Tank(int x, int y, Direction direction, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.tf = tf;
        if(this.group == Group.bad){
            this.move = true;
        }
    }

    /**
     * 画出坦克
     * @param g
     */
    public void paint(Graphics g){
        if(this.move){
            switch (direction) {
                case UP:
                    y -= speed;
                    break;
                case LEFT:
                    x -= speed;
                    break;
                case RIGHT:
                    x += speed;
                    break;
                case DOWM:
                    y += speed;
                    break;
            }
            if(random.nextInt(100) > 95 && this.group == Group.bad){
                this.fire();
            }
            if(this.group == Group.bad && random.nextInt(100) > 92){
                setBadDirection();
            }

            borderDetection();
        }

        switch (direction) {
            case UP:
                g.drawImage(this.group == Group.good ? ResourceManager.goodTankU : ResourceManager.badTankU,x,y,null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.good ? ResourceManager.goodTankL : ResourceManager.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.good ? ResourceManager.goodTankR : ResourceManager.badTankR,x,y,null);
                break;
            case DOWM:
                g.drawImage(this.group == Group.good ? ResourceManager.goodTankD : ResourceManager.badTankD,x,y,null);
                break;
        }
    }

    /**
     * 边界检测
     */
    private void borderDetection() {
        if(this.x < 2){
            this.x = 2;
        }
        if(this.y <32){
            this.y = 32;
        }
        if(this.x > TankFrame.GAME_WIDTH  - Tank.width){
            this.x = TankFrame.GAME_WIDTH - Tank.width;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.height){
            this.y = TankFrame.GAME_HEIGHT - Tank.height;
        }
    }

    private void setBadDirection() {
        this.direction = Direction.values()[random.nextInt(4)];
    }

    /**
     * 坦克发射子弹
     */
    public void fire() {
        int bx = x + Tank.width/2 - Bullet.WIDTH/2;
        int by = y + Tank.width/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bx,by,this.direction,this.group,tf));
        if(this.group == Group.good){
            new Thread(new Runnable() {
                public void run() {
                    new Audio("audio/tank_fire.wav").play();
                }
            }).start();
        }
    }

    public void die() {
        this.live = false;
    }

}
