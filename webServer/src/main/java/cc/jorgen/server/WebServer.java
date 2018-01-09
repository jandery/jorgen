package cc.jorgen.server;

import cc.jorgen.server.api.Multiply;
import com.atexpose.AtExpose;
import com.atexpose.api.Argument;
import com.atexpose.api.datatypes.DataType;
import com.atexpose.dispatcher.IDispatcher;
import com.atexpose.dispatcherfactories.WebServerBuilder;

/**
 * Purpose of this file is ...
 * <p>
 * Created by Jorgen Andersson on 2018-01-08.
 */
public class WebServer {


    public static void main(String[] args) {
        AtExpose.create()
                .expose(Multiply.class)
                .start(WebServerBuilder.create().build());
    }

}
