package cn.youyinnn.youQuickJetty.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @description:
 * @author: youyinnn
 * @date: 2017/9/26
 */
public class YouProUtils {

    private static Properties properties = new Properties();

    private YouProUtils(){}

    public static void load(String proFilePath) {
        try {
            properties.load(YouProUtils.class.getResourceAsStream(proFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

    public static void set(String key,String val) {
        properties.setProperty(key,val);
    }

}
