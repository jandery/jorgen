package cc.jorgen.server;

import cc.jorgen.server.api.Multiply;
import com.atexpose.AtExpose;
import com.atexpose.api.datatypes.DataType;
import com.atexpose.dispatcher.IDispatcher;
import com.atexpose.dispatcherfactories.WebServerBuilder;
import io.schinzel.basicutils.configvar.ConfigVar;

/**
 * Purpose of this file is ...
 * <p>
 * Created by Jorgen Andersson on 2018-01-08.
 */
public class WebServer {

    private static final String PORT_NUMBER = ConfigVar.create(".env").getValue("PORT");


    public static void main(String[] args) {
        AtExpose atExpose = AtExpose.create();
        //
        atExpose.getAPI().addLabel("Jorgen", "");
        //
        atExpose.getAPI()
                .addArgument("Value1", DataType.INT, "")
                .addArgument("Value2", DataType.INT, "");
        //
        atExpose.expose(Multiply.class)
                .start(getWebServer());
    }


    static IDispatcher getWebServer() {
        return WebServerBuilder.create()
                .webServerDir("websites/jorgen")
                .port(Integer.valueOf(PORT_NUMBER))
                .cacheFilesInRAM(false)
                .build();
    }

}
