package com.cjh.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ConfigManager {
    private static  ConfigManager configManager;
    private static Properties properties;
    static{
        String path="jdbc.properties";
        properties=new Properties();
        InputStream stream = ConfigManager.class.getClassLoader().getResourceAsStream(path);
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static  synchronized ConfigManager getInstance(){
        return configManager;
    }

    public static  String  getValue(String key){
        return properties.getProperty(key);
    }
}
