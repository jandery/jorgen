/**
 * Purpose of this class is main method for starting Spark WebServer
 * For more info about Spark(Java), please see http://sparkjava.com/documentation#views-and-templates
 *
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-01-21.
 */


//Main.kt
import spark.Spark.*

/**
 * Application main
 */
fun main(args: Array<String>) {
    //
    // File server
    spark.kotlin.staticFiles.location("/site")
    //
    // Rest server
    path("/api") {
        get("/hello") { req, res -> "Hello World" }
        //
        path("/users") {
            get("") {req, res -> "All users"}
            get("/:id") {req, res -> "User with id: " + req.params("id")}
        }
    }
}