package com.linqing.tank.facade;

import com.linqing.tank.*;
import com.linqing.tank.abstractFactory.*;
import com.linqing.tank.cor.BulletTankCollider;
import com.linqing.tank.cor.Collider;
import com.linqing.tank.cor.ColliderChain;
import com.linqing.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameModel {

    private static GameModel gameModel = new GameModel();
    public static GameModel getInstance(){
        return gameModel;
    }

    static {
        gameModel.init();
    }
    private GameModel(){
    }
    public List<GameObject> gameObjects = new ArrayList<GameObject>();
    private ColliderChain colliderChain = new ColliderChain();


    public GameFactory gameFactory = null;
    public Tank myTank = null;

    private void init(){
        // 初始化游戏物品工厂
        String objectFactoryStr = (String) PropertyManager.getInstance().getKey("objectFactory");
        try {
            gameFactory = (GameFactory) Class.forName(objectFactoryStr).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 初始化敌方坦克
        int initTankCount = Integer.parseInt((String) PropertyManager.getInstance().getKey("initTankCount"));
        for (int i = 0; i < initTankCount ; i++) {
            gameFactory.createTank(0,0,Group.bad);
        }
        myTank = (Tank) gameFactory.createTank(200,400, Group.good);
    }
    /**
     * 一个集合遍历过程中不允许直接新增或者移除集合中的元素  需要使用迭代器操作
     */
    public ListIterator<GameObject> iterator = null;


    public void paint(Graphics graphics) {
        // 画出坦克
        myTank.paint(graphics);

        // 画出敌方坦克
        iterator = gameObjects.listIterator();
        while(iterator.hasNext()){
            GameObject gameObjects = iterator.next();
            if(!gameObjects.isLive()){
                iterator.remove();
            }else{
                gameObjects.paint(graphics);
            }
        }

        // 碰撞检测
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i+1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                colliderChain.collideWith(o1, o2);
            }
        }
        //
        // Color color = graphics.getColor();
        // graphics.setColor(Color.white);
        // graphics.drawString("子弹的数量：" + bullets.size(),10,60);
        // graphics.drawString("敌人的数量：" + tanks.size(),10,80);
        // graphics.drawString("爆炸的数量：" + blasts.size(),10,100);
        // graphics.setColor(color);
    }
}
