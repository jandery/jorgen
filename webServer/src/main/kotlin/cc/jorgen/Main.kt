package cc.jorgen

import cc.jorgen.webServer.WebServer
import io.schinzel.basicutils.configvar.ConfigVar
import io.schinzel.basicutils.configvar.IConfigVar

/**
 * Purpose of this file is to start a WebServer
 *
 * Created by Jorgen Andersson on 2018-06-26.
 */
fun main(args: Array<String>) {

    val config : IConfigVar = ConfigVar.create(".env")
    val port = Integer.parseInt(config.getValue("PORT"))
    val filesLocation = "/www"

    WebServer(port = port, filesLocation = filesLocation)

    println("************************************************")
    println("*****  Spark server started on port $port  ******")
    println("************************************************")
}