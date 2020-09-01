package com.linqing.tank.facade;

import com.linqing.tank.Direction;
import com.linqing.tank.Group;
import com.linqing.tank.PropertyManager;
import com.linqing.tank.Tank;
import com.linqing.tank.abstractFactory.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameModel {

    private GameModel(){
        int initTankCount = Integer.parseInt((String) PropertyManager.getInstance().getKey("initTankCount"));

        for (int i = 0; i < initTankCount ; i++) {
            tanks.add(new Tank(i * 80,200,Direction.DOWM,Group.bad,this));
        }
    }

    private static GameModel gameModel = new GameModel();

    public static GameModel getInstance(){
        return gameModel;
    }


    public GameFactory gameFactory = DefaultFactory.getInstance();
    public BaseTank myTank = gameFactory.createTank(200,400, Direction.DOWM, Group.good,this);
    public List<BaseBullet> bullets = new ArrayList<BaseBullet>();
    public List<BaseTank> tanks = new ArrayList<BaseTank>();
    public List<BaseBlast> blasts = new ArrayList<BaseBlast>();

    public void paint(Graphics graphics) {
        // 画出坦克
        myTank.paint(graphics);
        // 画出子弹
        Iterator<BaseBullet> iterator1 = bullets.iterator();
        while(iterator1.hasNext()){
            BaseBullet bullet = iterator1.next();
            if(!bullet.isLive()){
                iterator1.remove();
            }
            bullet.paint(graphics);
        }
        // 画出敌方坦克
        Iterator<BaseTank> iterator = tanks.iterator();
        while(iterator.hasNext()){
            BaseTank tank = iterator.next();
            if(!tank.getLive()){
                iterator.remove();
            }
            tank.paint(graphics);
        }
        // 画出爆炸
        Iterator<BaseBlast> iterator2 = blasts.iterator();
        while(iterator2.hasNext()){
            BaseBlast blast = iterator2.next();
            if(!blast.isLive()){
                iterator2.remove();
            }else{
                blast.paint(graphics);
            }
        }
        // 碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collision(tanks.get(j));
            }
        }

        Color color = graphics.getColor();
        graphics.setColor(Color.white);
        graphics.drawString("子弹的数量：" + bullets.size(),10,60);
        graphics.drawString("敌人的数量：" + tanks.size(),10,80);
        graphics.drawString("爆炸的数量：" + blasts.size(),10,100);
        graphics.setColor(color);
    }
}
