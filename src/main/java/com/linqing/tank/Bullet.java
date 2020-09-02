package com.linqing.tank;

import com.linqing.tank.abstractFactory.BaseBlast;
import com.linqing.tank.abstractFactory.BaseBullet;
import com.linqing.tank.abstractFactory.BaseTank;
import com.linqing.tank.facade.GameModel;
import lombok.Data;

import java.awt.*;

/**
 * @author lin-PC
 */
@Data
public class Bullet extends BaseBullet {

    private int x;
    private int y;
    private Direction direction;
    private int speed = 10;
    public static int WIDTH = ResourceManager.getInstance().getBufferedImage("bulletD").getWidth();
    public static int HEIGHT = ResourceManager.getInstance().getBufferedImage("bulletD").getHeight();
    private Group group;
    private Rectangle rectangle = new Rectangle();
    public Bullet(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
        GameModel.getInstance().iterator.add(this);
    }


    /**
     * 画出子弹
     * @param g
     */
    @Override
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
            this.setLive(false);
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
}
