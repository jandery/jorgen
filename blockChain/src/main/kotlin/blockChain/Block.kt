package blockChain

import java.time.LocalDateTime

/**
 * Purpose of this file is ...
 *
 * Created by Jorgen Andersson on 2018-04-06.
 */
data class Block<T : Any>(
        val previousHash: String,
        val data: List<T>,
        val nonce: Long,
        val hash: String)

data class OfficialDocument(val title : String, val text : String, val created: LocalDateTime, val createdBy : String)

data class Transaction(val from : String, val to : String, val amount : Int)
