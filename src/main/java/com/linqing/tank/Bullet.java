package com.linqing.tank;

import lombok.Data;

import java.awt.*;

/**
 * @author lin-PC
 */
@Data
public class Bullet {

    private int x;
    private int y;
    private Direction direction;
    private int speed = 10;
    private boolean live = true;
    public static int WIDTH = ResourceManager.bulletD.getWidth();
    public static int HEIGHT = ResourceManager.bulletD.getHeight();
    private Group group;
    TankFrame tankFrame = null;
    public Bullet(int x, int y, Direction direction, Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;
    }


    /**
     * 画出子弹
     * @param g
     */
    public void paint(Graphics g){
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
            default:
                break;
        }
        // 每次画的时候判断子弹是否存活
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            live = false;
        }
        switch (direction) {
            case UP:
                g.drawImage(ResourceManager.bulletU,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceManager.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR,x,y,null);
                break;
            case DOWM:
                g.drawImage(ResourceManager.bulletD,x,y,null);
                break;
        }
    }

    public void collision(Tank tank) {
        if(this.group == tank.getGroup()){
            return;
        }
        Rectangle rectangle1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rectangle2 = new Rectangle(tank.getX(),tank.getY(),Tank.width,Tank.height);
        if(rectangle1.intersects(rectangle2)){
            this.die();
            tank.die();
            int bx = tank.getX() + Tank.width/2 - Blast.WIDTH/2;
            int by = tank.getY() + Tank.height/2 - Blast.HEIGHT/2;
            this.tankFrame.blasts.add(new Blast(bx,by,tankFrame));
        }
    }

    private void die() {
        this.live = false;
    }
}
