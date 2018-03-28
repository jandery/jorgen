package cc.jorgen.server;

import cc.jorgen.config.ConfigVariable;
import cc.jorgen.socket.ChatSocket;
import spark.Spark;



/**
 * Purpose of this file is ...
 * <p>
 * Created by Jorgen Andersson on 2018-01-08.
 */
public class WebServer {

    public static void main(String[] args) {
        Spark.port(Integer.parseInt(ConfigVariable.getValue(ConfigVariable.PORT)));
        Spark.staticFiles.location("/site");
        Spark.webSocket("/chat", ChatSocket.class);

        Spark.path("/api", () -> {
            Spark.get("/hello", (request, response) -> "Hello to you too");
        });

        Spark.init();

        System.out.println("***************************************************");
        System.out.println("**             Running cc.jorgen                 **");
        System.out.println("***************************************************");
    }
}
