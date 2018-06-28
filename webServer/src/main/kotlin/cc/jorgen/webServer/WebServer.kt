package cc.jorgen.webServer

import spark.Spark
import spark.Redirect.Status

/**
 * Purpose of this file is ...
 *
 * Created by Jorgen Andersson on 2018-06-26.
 */
class WebServer(port: Int, filesLocation: String) {

    init {

        // Settings
        Spark.port(port)

        // File Server
        Spark.staticFiles.location(filesLocation)

        // API server
        Spark.get("/hello") { _, _ -> "Hello World" }

        // Shortcuts
        Spark.redirect.get("/mid99", "https://boiling-torch-802.firebaseapp.com/", Status.TEMPORARY_REDIRECT);

        // Init/Start server
        Spark.init()
    }

}