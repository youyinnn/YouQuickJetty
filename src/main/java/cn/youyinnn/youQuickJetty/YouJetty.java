package cn.youyinnn.youQuickJetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

/**
 * @description:
 * @author: cn.youyinnn
 * @date: 2017/9/24
 */
public class YouJetty {

    private static Server server;
    private static WebAppContext webapp;

    private static YouJetty youJetty = new YouJetty();

    private YouJetty(){}

    public static YouJetty initServer(int port) {

        server = new Server(port);

        webapp = new WebAppContext();
        webapp.setContextPath("/");

        File warFile = new File("src/main/webapp");

        webapp.setWar(warFile.getAbsolutePath());

        server.setHandler(webapp);

        return youJetty;
    }

    public void startAndJoin() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAnnotationScanner(){
        Configuration.ClassList classList = Configuration.ClassList.setServerDefault(server);

        classList.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
                "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                "org.eclipse.jetty.plus.webapp.PlusConfiguration");

        classList.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");

        webapp.setExtraClasspath("target/classes");
    }
}
