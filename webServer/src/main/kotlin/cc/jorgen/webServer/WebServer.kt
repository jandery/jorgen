package cc.jorgen.webServer

import spark.ModelAndView
import spark.Spark
import spark.Redirect.Status
import spark.Request
import spark.template.freemarker.FreeMarkerEngine



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

        Spark.before("/*") { request: Request, _ ->
            println("THIS IS BEFORE EVERYTHING")
            println("  SESSION IS_NEW: " + request.session().isNew)
            println("  SESSION TADA: " + request.session().attribute<String>("TADA"))
        }

        Spark.after("/*") { request: Request, _ ->
            if (request.session().attribute<String>("TADA").isEmpty()) {
                println("SETTING SESSION TADA")
                request.session().attribute("TADA", "TODO")
            }
        }

        Spark.get(
                "jojje",
                { _, _ ->
                    ModelAndView(mapOf("variable" to "Jörgens variable"), "index.ftl")
                },
                FreeMarkerEngine())


        // API server
        Spark.get("/hello") { _, _ -> "Hello World" }

        // Shortcuts
        Spark.redirect.get("/mid99", "https://boiling-torch-802.firebaseapp.com/", Status.TEMPORARY_REDIRECT);

        // Init/Start server
        Spark.init()
    }

}