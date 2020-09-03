package com.linqing.tank;

import com.linqing.tank.abstractFactory.BaseBullet;
import com.linqing.tank.facade.GameModel;
import lombok.Data;

import java.awt.*;

/**
 * @author lin-PC
 */
@Data
public class Bullet extends BaseBullet {

    private Direction direction;
    private int speed = 10;
    private Group group;
    private Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Direction direction, Group group) {
        super(x,y,
                ResourceManager.getInstance().getBufferedImage("bulletD").getWidth(),
                ResourceManager.getInstance().getBufferedImage("bulletD").getHeight());
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = ResourceManager.getInstance().getBufferedImage("bulletD").getWidth();
        rectangle.height = ResourceManager.getInstance().getBufferedImage("bulletD").getHeight();
        this.direction = direction;
        this.group = group;
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
                this.setY(this.getY() - speed);
                break;
            case LEFT:
                this.setX(this.getX() - speed);
                break;
            case RIGHT:
                this.setX(this.getX() + speed);
                break;
            case DOWM:
                this.setY(this.getY() + speed);
                break;
            default:
                break;
        }

        // update rectangle
        updateRectangle();

        // 每次画的时候判断子弹是否存活
        if(this.getX() < 0 || this.getY() < 0 || this.getX() > TankFrame.GAME_WIDTH || this.getY() > TankFrame.GAME_HEIGHT){
            this.die();
        }
        switch (direction) {
            case UP:
                g.drawImage(ResourceManager.getInstance().getBufferedImage("bulletU"),this.getX(),this.getY(),null);
                break;
            case LEFT:
                g.drawImage(ResourceManager.getInstance().getBufferedImage("bulletL"),this.getX(),this.getY(),null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.getInstance().getBufferedImage("bulletR"),this.getX(),this.getY(),null);
                break;
            case DOWM:
                g.drawImage(ResourceManager.getInstance().getBufferedImage("bulletD"),this.getX(),this.getY(),null);
                break;
        }
    }

    /**
     * 更新rectangle
     */
    private void updateRectangle(){
        this.rectangle.x = super.getX();
        this.rectangle.y = super.getY();
    }
}
