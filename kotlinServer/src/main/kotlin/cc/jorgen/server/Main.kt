package cc.jorgen.server

/**
 * Purpose of this class is cc.jorgen.server.main method for starting Spark WebServer
 * For more info about Spark(Java), please see http://sparkjava.com/documentation#views-and-templates
 *
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-01-21.
 */

import spark.Spark.*
import com.google.gson.Gson
import cc.jorgen.temp.User

/**
 * Application cc.jorgen.server.main
 */
fun main(args: Array<String>) {
    //
    // File server
    spark.kotlin.staticFiles.location("/site")
    //
    // Rest server
    path("/api") {
        // Object for making object to JSON for response
        val gson = Gson()
        //
        //
        get("/hello") { req, res -> "Hello World" }
        //
        // Path for users
        path("/users") {
            // Get all user with get request [http://localhost:4567]/api/users
            get("") { req, res -> "All users" }
            // Get a user with get request [http://localhost:4567]/api/users/[id for user]
            get("/:id") { req, res ->
                gson.toJson(User("Jörgen", "Andersson", req.params("id")))
            }
        }
    }
}