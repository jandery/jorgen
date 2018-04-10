package cc.jorgen.chain

import java.util.Date

/**
 * Purpose of this file is ...
 *
 * Created by Jorgen Andersson on 2018-04-06.
 */
data class Block<T : Any>(
        val date: Date,
        val data: List<T>,
        val nonce: Long,
        val previousHash: String,
        val hash: String)

data class OfficialDocument(val title : String, val text : String, val created: Date, val createdBy : String)

data class Transaction(val from : String, val to : String, val amount : Int)
