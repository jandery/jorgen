package cc.jorgen.blockChain

import cc.jorgen.encrypt.HashUtils
import java.time.LocalDateTime

/**
 * Purpose of this file is holding a Block in a Chain
 *
 * Created by Jorgen Andersson on 2018-04-06.
 */
data class Block<T>(
        val previousHash: String,
        val data: List<T>,
        val nonce: Long,
        val hash: String) {

    /**
     * Secondary constructor with Generate SHA1 for current Block
     * @param previousHash
     * @param data
     * @param nonce
     */
    constructor(previousHash: String, data: List<T>, nonce: Long)
            : this(previousHash, data, nonce, HashUtils.SHA1("${previousHash}${data}${nonce}"))
}



data class OfficialDocument(val title : String, val text : String, val created: LocalDateTime, val createdBy : String)

data class Transaction(val from : String, val to : String, val amount : Int)
