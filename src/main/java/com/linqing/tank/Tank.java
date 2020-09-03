package com.linqing.tank;

import com.linqing.tank.abstractFactory.BaseTank;
import com.linqing.tank.facade.GameModel;
import com.linqing.tank.observe.*;
import com.linqing.tank.strategy.FireStrategy;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
    private Rectangle rectangle = new Rectangle();
    private FireStrategy<Tank> tankTankFireStrategy;
    private TankFireObserverChain tankFireObserverChain = new TankFireObserverChain();


    public Tank(int x, int y,Group group) {
        super(x,y,
                ResourceManager.getInstance().getBufferedImage("goodTankU").getWidth(),
                ResourceManager.getInstance().getBufferedImage("goodTankU").getHeight());
        this.group = group;

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = ResourceManager.getInstance().getBufferedImage("goodTankU").getWidth();
        rectangle.height = ResourceManager.getInstance().getBufferedImage("goodTankU").getHeight();

        if(group == Group.bad){
            // 随机设置敌方坦克位置
            setX(random.nextInt(TankFrame.GAME_WIDTH  - this.getWidth() -2) + 2);
            setY(random.nextInt(TankFrame.GAME_HEIGHT - this.getHeight() -32) + 32);
        }

        // 敌方坦克初始化方向随机设置
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

    public void fireKey(){
        TankFireEvent<Tank> tankFireEvent = new TankFireEvent<Tank>(this);
        tankFireObserverChain.handler(tankFireEvent);
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
            // 敌方坦克随机发射子弹
            if(this.getRandom().nextInt(100) > 95 && this.getGroup() == Group.bad){
                this.fire();
            }

            // 敌方坦克随机变化方向
            if(this.getGroup() == Group.bad && this.getRandom().nextInt(100) > 92){
                this.setDirection(Direction.values()[this.getRandom().nextInt(4)]);
            }
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
    public void fire() {
        this.getTankTankFireStrategy().fire(this);
    }

    /**
     * 边界检测
     */
    private void borderDetection() {
        if(this.getX() < 2){
            this.setX(2);
        }
        if(this.getY() <32){
            this.setY(32);
        }
        if(this.getX() > TankFrame.GAME_WIDTH  - this.getWidth()){
            this.setX(TankFrame.GAME_WIDTH - this.getWidth());
        }
        if(this.getY() > TankFrame.GAME_HEIGHT - this.getHeight()){
            this.setY(TankFrame.GAME_HEIGHT - this.getHeight());
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
