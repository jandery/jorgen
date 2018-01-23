package cc.jorgen.temp

/**
 * Purpose of this file is ...
 *
 * Created by Jorgen Andersson on 2018-01-23.
 */

class User constructor(firstName: String, lastName: String, id: String) {
    val fullName : String = firstName + " " + lastName
    var userId : String = id
}