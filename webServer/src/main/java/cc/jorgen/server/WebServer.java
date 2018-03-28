package cc.jorgen.server;

import cc.jorgen.socket.ChatSocket;
import io.schinzel.basicutils.configvar.ConfigVar;
import spark.Spark;

/**
 * Purpose of this file is ...
 * <p>
 * Created by Jorgen Andersson on 2018-01-08.
 */
public class WebServer {

    public static void main(String[] args) {
        int port = Integer.parseInt(ConfigVar.create(".env").getValue("PORT"));
        Spark.port(port);
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
