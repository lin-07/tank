package com.linqing.tank;


import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author lin-PC
 * 读取资源
 */
public class ResourceManager {

    // 完美的单例 双重检测单例
    private ResourceManager(){}

    private static volatile ResourceManager resourceManager;

    public static ResourceManager getInstance(){
        if(resourceManager == null){
            synchronized (ResourceManager.class){
                if(resourceManager == null){
                    resourceManager = new ResourceManager();
                }
            }
        }
        return resourceManager;
    }

    private static BufferedImage goodTankL,goodTankR,goodTankU,goodTankD;
    private static BufferedImage badTankL,badTankR,badTankU,badTankD;
    private static BufferedImage bulletL,bulletR,bulletU,bulletD;
    private static BufferedImage[] blasts = new BufferedImage[16];

    public BufferedImage getBufferedImage(String key){
        BufferedImage value = null;
       if(key == "goodTankL"){
           value = goodTankL;
       }
        if(key == "goodTankR"){
            value = goodTankR;
        }
        if(key == "goodTankD"){
            value = goodTankD;
        }
        if(key == "goodTankU"){
            value = goodTankU;
        }
        if(key == "badTankL"){
            value = badTankL;
        }
        if(key == "badTankR"){
            value = badTankR;
        }
        if(key == "badTankU"){
            value = badTankU;
        }
        if(key == "badTankD"){
            value = badTankD;
        }
        if(key == "bulletL"){
            value = bulletL;
        }
        if(key == "bulletR"){
            value = bulletR;
        }
        if(key == "bulletU"){
            value = bulletU;
        }
        if(key == "bulletD"){
            value = bulletD;
        }
        return value;
    }

    public BufferedImage[] getBufferedImageArray(){
        return blasts;
    }

    static {
        try {
            goodTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);
            goodTankD = ImageUtil.rotateImage(goodTankU,180);

            badTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU,-90);
            badTankR = ImageUtil.rotateImage(badTankU,90);
            badTankD = ImageUtil.rotateImage(badTankU,180);

            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);
            for (int i = 0; i < 16; i++) {
                blasts[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/e"+ (i+1) +".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
