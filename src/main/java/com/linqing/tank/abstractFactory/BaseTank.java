package com.linqing.tank.abstractFactory;

import com.linqing.tank.*;
import com.linqing.tank.strategy.FireStrategy;
import lombok.Data;

import java.awt.*;
import java.util.Random;

@Data
public abstract class BaseTank {

    private int x;
    private int y;
    private Direction direction;
    private int speed = 5;
    private Boolean move = false;
    public static int width;
    public static int height;
    private Boolean live = true;
    private TankFrame tf;
    private Random random = new Random();
    private Group group;
    private Rectangle rectangle = new Rectangle();
    private FireStrategy<Tank> tankTankFireStrategy;


    public BaseTank(int x, int y, Direction direction, Group group, TankFrame tf,int width,int height) {
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
    }

    /**
     * 创建
     * @param g
     */
    public abstract void paint(Graphics g);

    /**
     * 开火
     */
    public abstract void fire();

    /**
     * 死亡
     */
    public void die(){
        this.live = false;
    }

    /**
     * 边界检测
     */
    protected void borderDetection() {
        if(this.getX() < 2){
            this.setX(2);
        }
        if(this.getY() <32){
            this.setY(32);
        }
        if(this.getX() > TankFrame.GAME_WIDTH  - Tank.width){
            this.setX(TankFrame.GAME_WIDTH - Tank.width);
        }
        if(this.getY() > TankFrame.GAME_HEIGHT - Tank.height){
            this.setY(TankFrame.GAME_HEIGHT - Tank.height);
        }
    }

    /**
     * 更新rectangle
     */
    protected void updateRectangle(){
        this.rectangle.x = x;
        this.rectangle.y = y;
    }

    /**
     * 设置坦克方向
     */
    protected void randomBadTankDirection() {
        if(this.getGroup() == Group.bad && this.getRandom().nextInt(100) > 92){
            this.setDirection(Direction.values()[this.getRandom().nextInt(4)]);
        }
    }

}
