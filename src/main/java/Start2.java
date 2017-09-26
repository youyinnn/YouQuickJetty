import cn.youyinnn.youQuickJetty.YouJetty;

import java.io.IOException;
import java.util.Properties;

/**
 * @description:
 * @author: youyinnn
 * @date: 2017/9/24
 */
public class Start2 {

    public static void main(String[] args) {

        YouJetty youJetty;

        Properties properties = new Properties();

        try {
            properties.load(Start2.class.getResourceAsStream("/conf/root.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(properties.getProperty("root"));

        if (args.length > 0){
            youJetty = YouJetty.initServer(8080,args[0]);
        } else {
            youJetty = YouJetty.initServerWithDevelopment(8080);
        }

        youJetty.startAndJoin();
    }

}
