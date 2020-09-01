package com.linqing.tank;

import com.linqing.tank.abstractFactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lin-PC
 */
public class TankFrame extends Frame {

   public GameFactory gameFactory = DefaultFactory.getInstance();
   BaseTank myTank = gameFactory.createTank(200,400, Direction.DOWM,Group.good,this);
   public List<BaseBullet> bullets = new ArrayList<BaseBullet>();
   List<BaseTank> tanks = new ArrayList<BaseTank>();
   List<BaseBlast> blasts = new ArrayList<BaseBlast>();
   public static int GAME_WIDTH = 1366;
   public static int GAME_HEIGHT = 768;


    public TankFrame (){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new MyKeyAdapter());
    }

    Image offScreenImage = null;

    /**
     * 双缓存解决闪缩问题，无需理解
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
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

    class MyKeyAdapter extends KeyAdapter{

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        /**
         * 按键按下
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTank();
        }

        /**
         * 按键抬起
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
            }
            setMainTank();
        }

        /**
         * 根据方向的四个状态触发坦克是否是静止以及移动的方向
         * 方向键按下和抬起的时候调用
         */
        private void setMainTank() {
            // 1.设置坦克方向
            if(bD){
                myTank.setDirection(Direction.DOWM);
            }
            if(bR){
                myTank.setDirection(Direction.RIGHT);
            }
            if(bU){
                myTank.setDirection(Direction.UP);
            }
            if(bL){
                myTank.setDirection(Direction.LEFT);
            }
            if(bD || bL || bU || bR){
                new Thread(new Runnable() {
                    public void run() {
                        new Audio("audio/tank_move.wav").play();
                    }
                }).start();
            }
            // 2.设置坦克移动状态
            myTank.setMove(true);
            // 方向的四个状态都是false的时候坦克静止
            if(!bD && !bL && !bU && !bR){
                myTank.setMove(false);
            }
        }


    }
}
