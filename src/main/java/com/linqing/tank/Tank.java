package com.linqing.tank;

import com.linqing.tank.abstractFactory.BaseTank;

import java.awt.*;

/**
 * @author lin-PC
 */
public class Tank extends BaseTank {

    public static int width = ResourceManager.getInstance().getBufferedImage("goodTankU").getWidth();
    public static int height = ResourceManager.getInstance().getBufferedImage("goodTankU").getHeight();

    public Tank(int x, int y, Direction direction, Group group, TankFrame tf) {
        super(x,y,direction,group,tf,width,height);
    }

    /**
     * 画出坦克
     * @param g
     */
    @Override
    public void paint(Graphics g){
        if(this.getMove()){
            switch (this.getDirection()) {
                case UP:
                    this.setY(this.getY() - this.getSpeed());
                    break;
                case LEFT:
                    this.setX(this.getX() - this.getSpeed());
                    break;
                case RIGHT:
                    this.setX(this.getX() + this.getSpeed());
                    break;
                case DOWM:
                    this.setY(this.getY() + this.getSpeed());
                    break;
            }
            if(this.getRandom().nextInt(100) > 95 && this.getGroup() == Group.bad){
                this.fire();
            }
            randomBadTankDirection();
            borderDetection();
            // update rectangle
            updateRectangle();
        }

        switch (this.getDirection()) {
            case UP:
                g.drawImage(this.getGroup() == Group.good ? ResourceManager.getInstance().getBufferedImage("goodTankU") : ResourceManager.getInstance().getBufferedImage("badTankU"),this.getX(),this.getY(),null);
                break;
            case LEFT:
                g.drawImage(this.getGroup() == Group.good ? ResourceManager.getInstance().getBufferedImage("goodTankL") : ResourceManager.getInstance().getBufferedImage("badTankL"),this.getX(),this.getY(),null);
                break;
            case RIGHT:
                g.drawImage(this.getGroup() == Group.good ? ResourceManager.getInstance().getBufferedImage("goodTankR") : ResourceManager.getInstance().getBufferedImage("badTankR"),this.getX(),this.getY(),null);
                break;
            case DOWM:
                g.drawImage(this.getGroup() == Group.good ? ResourceManager.getInstance().getBufferedImage("goodTankD") : ResourceManager.getInstance().getBufferedImage("badTankD"),this.getX(),this.getY(),null);
                break;
        }
    }





    /**
     * 坦克发射子弹
     */
    @Override
    public void fire() {
        this.getTankTankFireStrategy().fire(this);
    }
}
