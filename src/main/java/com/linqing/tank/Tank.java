package com.linqing.tank;

import com.linqing.tank.abstractFactory.BaseTank;
import com.linqing.tank.facade.GameModel;
import com.linqing.tank.strategy.FireStrategy;
import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * @author lin-PC
 */
@Data
public class Tank extends BaseTank {

    private int oldX;
    private int oldY;
    private Direction direction;
    private int speed = 5;
    private Boolean move = false;
    private Group group;
    private Random random = new Random();
    private FireStrategy<Tank> tankTankFireStrategy;


    public Tank(int x, int y, Group group) {
        super(x,y,group,
                ResourceManager.getInstance().getBufferedImage("goodTankU").getWidth(),
                ResourceManager.getInstance().getBufferedImage("goodTankU").getHeight());
        this.group = group;
        this.direction = group == Group.good ? Direction.UP : Direction.values()[random.nextInt(4)];
        this.move = group == Group.good ? false : true;
        String fireClassName = null;
        if(this.group == Group.good){
            fireClassName = (String) PropertyManager.getInstance().getKey("goodFS");
        }else{
            fireClassName = (String) PropertyManager.getInstance().getKey("badFS");
        }
        try {
            this.tankTankFireStrategy = (FireStrategy<Tank>)Class.forName(fireClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(group == Group.bad){
            // 敌方坦克初始化的时候加入的游戏物品集合，注意什么时候用集合的加入方法，什么时候用迭代器的加入方法，这里是刚开始初始化
            GameModel.getInstance().gameObjects.add(this);
        }
    }

    /**
     * 画出坦克
     * @param g
     */
    @Override
    public void paint(Graphics g){
        if(this.getMove()){
            // 每次移动前设置oldX oldY的值
            this.setOldX(this.getX());
            this.setOldY(this.getY());
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
     * 设置坦克方向
     */
    private void randomBadTankDirection() {
        if(this.getGroup() == Group.bad && this.getRandom().nextInt(100) > 92){
            this.setDirection(Direction.values()[this.getRandom().nextInt(4)]);
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
