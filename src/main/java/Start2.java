import cn.youyinnn.youQuickJetty.YouJetty;
import cn.youyinnn.youQuickJetty.YouProUtils;

/**
 * @description:
 * @author: youyinnn
 * @date: 2017/9/24
 */
public class Start2 {

    public static void main(String[] args) {

        YouJetty youJetty;

        YouProUtils.load("/conf/root.properties");
        System.out.println(YouProUtils.get("root"));

        youJetty = YouJetty.initServer(8080,"", args);

        youJetty.startAndJoin();
    }

}
