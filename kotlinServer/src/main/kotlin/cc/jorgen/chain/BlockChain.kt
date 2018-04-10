package cc.jorgen.chain

import java.util.Date

/**
 * Purpose of this file is ...
 *
 * Created by Jorgen Andersson on 2018-04-10.
 */
class BlockChain {

    fun getDocumentBlock() : Block<OfficialDocument> {

        val docs = listOf<OfficialDocument>(
                OfficialDocument("My title", "My text 1", Date(), "Jörgen Andersson"),
                OfficialDocument("My title", "My text 2", Date(), "Jörgen Andersson"),
                OfficialDocument("My title", "My text 3", Date(), "Jörgen Andersson"))

        return Block<OfficialDocument>(Date(), docs, 0, "", "")
    }

    fun getTransactionBlock() : Block<Transaction> {

        val transactions = listOf<Transaction>(
                Transaction("", "", 100),
                Transaction("", "", 100),
                Transaction("", "", 100),
                Transaction("", "", 100),
                Transaction("", "", 100))

        return Block<Transaction>(Date(), transactions, 0, "", "")
    }
}