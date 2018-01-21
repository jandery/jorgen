/**
 * Purpose of this class ...
 *
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-01-21.
 */


//Main.kt
import spark.Spark.*

fun main(args: Array<String>) {
    // File server
    spark.kotlin.staticFiles.location("/site")
    // Rest server
    get("/hello") { req, res -> "Hello World" }
    //
    path("/users") {
        get("") {req, res -> "All users"}
        get("/:id") {req, res -> "User with id: " + req.params("id")}
    }
}