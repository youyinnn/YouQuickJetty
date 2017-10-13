package cn.youyinnn.youQuickJetty;

import cn.youyinnn.youQuickJetty.utils.ArgsAnalysis;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @description:
 * @author: cn.cn.youyinnn
 * @date: 2017/9/24
 */
public class YouJetty {

    private static Server server;
    private static WebAppContext webapp;

    private static YouJetty youJetty = new YouJetty();

    private YouJetty(){}

    private static YouJetty initServer(int port, String contextName, String warPath){

        server = new Server(port);

        webapp = new WebAppContext();
        webapp.setContextPath("/"+contextName);
        webapp.setConfigurationDiscovered(true);
        webapp.setParentLoaderPriority(true);
        webapp.setClassLoader(Thread.currentThread().getContextClassLoader());

        if (warPath != null) {
            // 发布环境
            webapp.setWar(warPath);
        } else {
            // idea下的测试环境
            File warFile = new File("src/main/webapp");

            webapp.setWar(warFile.getAbsolutePath());
        }


        server.setHandler(webapp);

        addAnnotationScanner();

        return youJetty;
    }

    public static YouJetty initServer(String[] args){

        ArrayList<String> supportCmd = new ArrayList<>(Arrays.asList("p","cn","wpth"));

        ArgsAnalysis argsAnalysis = new ArgsAnalysis(args, supportCmd);

        return initServer(
                argsAnalysis.hasCommand("p") ? Integer.parseInt(argsAnalysis.getValue("p")) : 8080,
                argsAnalysis.hasCommand("cn") ? argsAnalysis.getValue("cn") : "",
                argsAnalysis.hasCommand("wpth") ? argsAnalysis.getValue("wpth") : null
        );
    }

    public void startAndJoin() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addAnnotationScanner(){
        Configuration.ClassList classList = Configuration.ClassList.setServerDefault(server);

        classList.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
                "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                "org.eclipse.jetty.plus.webapp.PlusConfiguration");

        classList.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");

        // 前面的路径是idea下的调试路径 后面的路径是发布版本后的class类存放的路径
        webapp.setExtraClasspath("target/classes,classes");
    }

}
