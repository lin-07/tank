package com.linqing.tank.facade;

import com.linqing.tank.*;
import com.linqing.tank.abstractFactory.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameModel {

    private GameModel(){
        int initTankCount = Integer.parseInt((String) PropertyManager.getInstance().getKey("initTankCount"));

        for (int i = 0; i < initTankCount ; i++) {
            gameObjects.add(new Tank(i * 80,200,Direction.DOWM,Group.bad,this));
        }
    }
    public List<GameObject> gameObjects = Collections.synchronizedList(new ArrayList<GameObject>());
    private static GameModel gameModel = new GameModel();

    public static GameModel getInstance(){
        return gameModel;
    }


    public GameFactory gameFactory = DefaultFactory.getInstance();
    public BaseTank myTank = gameFactory.createTank(200,400, Direction.DOWM, Group.good,this);
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
            }
            gameObjects.paint(graphics);
        }

        // 碰撞检测
        // for (int i = 0; i < gameObjects.size(); i++) {
        //     for (int j = 0; j < gameObjects.size(); j++) {
        //         GameObject o1 = gameObjects.get(i);
        //         GameObject o2 = gameObjects.get(j);
        //         if(o1 instanceof Tank && o2 instanceof Bullet){
        //             ((Bullet) o2).collision((Tank) o1);
        //         }
        //         if(o1 instanceof Bullet && o2 instanceof Tank){
        //             ((Bullet) o1).collision((Tank) o2);
        //         }
        //     }
        // }
        //
        // Color color = graphics.getColor();
        // graphics.setColor(Color.white);
        // graphics.drawString("子弹的数量：" + bullets.size(),10,60);
        // graphics.drawString("敌人的数量：" + tanks.size(),10,80);
        // graphics.drawString("爆炸的数量：" + blasts.size(),10,100);
        // graphics.setColor(color);
    }
}