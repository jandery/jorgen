package cc.jorgen

import cc.jorgen.webServer.WebServer

/**
 * Purpose of this file is ...
 *
 * Created by Jorgen Andersson on 2018-06-26.
 */
fun main(args: Array<String>) {

    val port = 5656
    val filesLocation = "/www"

    WebServer(port = port, filesLocation = filesLocation)

    println("************************************************")
    println("*****  Spark server started on port $port  ******")
    println("************************************************")
}