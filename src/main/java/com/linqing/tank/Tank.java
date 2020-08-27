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
    public static int width = ResourceManager.getInstance().getBufferedImage("goodTankU").getWidth();
    public static int height = ResourceManager.getInstance().getBufferedImage("goodTankU").getHeight();
    private boolean live = true;
    private TankFrame tf;
    private Random random = new Random();
    private Group group;
    private Rectangle rectangle = new Rectangle();
    private FireStrategy<Tank> tankTankFireStrategy;

    public Tank(int x, int y, Direction direction, Group group, TankFrame tf, FireStrategy<Tank> tankTankFireStrategy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.tf = tf;
        if(this.group == Group.bad){
            this.move = true;
        }
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = width;
        rectangle.height = height;
        this.tankTankFireStrategy = tankTankFireStrategy;
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
            // update rectangle
            rectangle.x = x;
            rectangle.y = y;
        }

        switch (direction) {
            case UP:
                g.drawImage(this.group == Group.good ? ResourceManager.getInstance().getBufferedImage("goodTankU") : ResourceManager.getInstance().getBufferedImage("badTankU"),x,y,null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.good ? ResourceManager.getInstance().getBufferedImage("goodTankL") : ResourceManager.getInstance().getBufferedImage("badTankL"),x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.good ? ResourceManager.getInstance().getBufferedImage("goodTankR") : ResourceManager.getInstance().getBufferedImage("badTankR"),x,y,null);
                break;
            case DOWM:
                g.drawImage(this.group == Group.good ? ResourceManager.getInstance().getBufferedImage("goodTankD") : ResourceManager.getInstance().getBufferedImage("badTankD"),x,y,null);
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
        tankTankFireStrategy.fire(this);
    }

    public void die() {
        this.live = false;
    }

}
