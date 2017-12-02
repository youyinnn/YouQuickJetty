import com.github.youyinnn.youquickjetty.YouJetty;
import com.github.youyinnn.youquickjetty.utils.YouProUtils;

/**
 * @author youyinnn
 */
public class ProductEnvStart {

    public static void main(String[] args) {

        YouJetty youJetty = YouJetty.initServer(args);
        YouJetty.addAnnotationScanner();

        YouProUtils.load("/conf/root.properties");
        System.out.println(YouProUtils.get("root"));

        youJetty.startAndJoin();
    }

}
