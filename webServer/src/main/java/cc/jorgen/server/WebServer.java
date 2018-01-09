package cc.jorgen.server;

import cc.jorgen.server.api.Multiply;
import com.atexpose.AtExpose;
import com.atexpose.api.datatypes.DataType;
import com.atexpose.dispatcherfactories.WebServerBuilder;

/**
 * Purpose of this file is ...
 * <p>
 * Created by Jorgen Andersson on 2018-01-08.
 */
public class WebServer {


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
                .start(WebServerBuilder.create().webServerDir("websites/jorgen").build());
    }

}
