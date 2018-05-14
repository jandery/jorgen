package cc.jorgen.chain

import cc.jorgen.encrypt.HashUtils
import java.time.LocalDateTime

/**
 * Purpose of this file is ...
 *
 * Created by Jorgen Andersson on 2018-04-10.
 */
class BlockChain {

    fun getDocumentBlock(): Block<OfficialDocument> {

        val docs = listOf<OfficialDocument>(
                OfficialDocument("My title", "My text 1", LocalDateTime.now(), "Jörgen Andersson"),
                OfficialDocument("My title", "My text 2", LocalDateTime.now(), "Jörgen Andersson"),
                OfficialDocument("My title", "My text 3", LocalDateTime.now(), "Jörgen Andersson"))

        return Block<OfficialDocument>(
                "",
                docs,
                0,
                hashContent(
                        "",
                        docs.joinToString(prefix = "", postfix = "", separator = ""),
                        0))
    }

    fun getTransactionBlock(): Block<Transaction> {

        val transactions = listOf<Transaction>(
                Transaction("", "", 100),
                Transaction("", "", 100),
                Transaction("", "", 100),
                Transaction("", "", 100),
                Transaction("", "", 100))

        return Block<Transaction>(
                "",
                transactions,
                0,
                hashContent(
                        "",
                        transactions.joinToString(),
                        0))
    }

    private fun hashContent(previousHash: String, content: String, nounce: Int): String {
        val source = "${previousHash}${content}${nounce}"
        return HashUtils.sha1(source)
    }
}