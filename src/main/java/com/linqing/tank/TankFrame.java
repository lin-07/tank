package com.linqing.tank;

import com.linqing.tank.facade.GameModel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author lin-PC
 */
public class TankFrame extends Frame {

   public static int GAME_WIDTH = 1366;
   public static int GAME_HEIGHT = 768;

   private GameModel gameModel = GameModel.getInstance();


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
        gameModel.paint(graphics);
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
                    gameModel.myTank.fireKey();
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
                gameModel.myTank.setDirection(Direction.DOWM);
            }
            if(bR){
                gameModel.myTank.setDirection(Direction.RIGHT);
            }
            if(bU){
                gameModel.myTank.setDirection(Direction.UP);
            }
            if(bL){
                gameModel.myTank.setDirection(Direction.LEFT);
            }
            if(bD || bL || bU || bR){
                new Thread(new Runnable() {
                    public void run() {
                        new Audio("audio/tank_move.wav").play();
                    }
                }).start();
            }
            // 2.设置坦克移动状态
            gameModel.myTank.setMove(true);
            // 方向的四个状态都是false的时候坦克静止
            if(!bD && !bL && !bU && !bR){
                gameModel.myTank.setMove(false);
            }
        }


    }
}
