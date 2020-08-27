package com.linqing.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author lin-PC
 */
public class PropertyManager {

    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object getKey(String key){
        return properties.get(key);
    }



}
