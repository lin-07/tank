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
    public static int WIDTH = ResourceManager.getInstance().getBufferedImage("bulletD").getWidth();
    public static int HEIGHT = ResourceManager.getInstance().getBufferedImage("bulletD").getHeight();
    private Group group;
    TankFrame tankFrame = null;
    private Rectangle rectangle = new Rectangle();
    public Bullet(int x, int y, Direction direction, Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
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

        // update rectangle
        rectangle.x = x;
        rectangle.y = y;

        // 每次画的时候判断子弹是否存活
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            live = false;
        }
        switch (direction) {
            case UP:
                g.drawImage(ResourceManager.getInstance().getBufferedImage("bulletU"),x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceManager.getInstance().getBufferedImage("bulletL"),x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.getInstance().getBufferedImage("bulletR"),x,y,null);
                break;
            case DOWM:
                g.drawImage(ResourceManager.getInstance().getBufferedImage("bulletD"),x,y,null);
                break;
        }
    }

    public void collision(Tank tank) {
        if(this.group == tank.getGroup()){
            return;
        }
        if(rectangle.intersects(tank.getRectangle())){
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
