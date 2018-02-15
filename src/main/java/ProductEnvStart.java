import com.github.youyinnn.youquickjetty.YouJetty;

/**
 * @author youyinnn
 */
public class ProductEnvStart {

    public static void main(String[] args) {

        YouJetty youJetty = YouJetty.initServer(args);

        youJetty.startAndJoin();
    }

}
