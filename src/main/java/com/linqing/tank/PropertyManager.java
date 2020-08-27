package com.linqing.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author lin-PC
 */
public class PropertyManager {

    // 正常单例就这么写就行
    private PropertyManager(){}

    private static PropertyManager propertyManager = new PropertyManager();

    public static PropertyManager getInstance(){
        return propertyManager;
    }


    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Object getKey(String key){
        return properties.get(key);
    }



}
