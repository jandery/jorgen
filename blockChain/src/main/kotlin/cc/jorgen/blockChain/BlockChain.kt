package cc.jorgen.blockChain

import cc.jorgen.encrypt.HashUtils

/**
 * Purpose of this file is ...
 *
 * Created by Jorgen Andersson on 2018-04-10.
 */
class BlockChain {

    /**
     * Statics
     */
    companion object {
        val documentChain = mutableListOf<Block<OfficialDocument>>()
        val transactionChain = mutableListOf<Block<Transaction>>()

        fun addDocumentBlock(docs: List<OfficialDocument>) {
            val previousHash = if (documentChain.isEmpty()) "" else documentChain.last().hash
            val nounce: Long = 0
            //
            documentChain.add(Block(
                    previousHash,
                    docs,
                    nounce))
        }

        fun addTransactionBlock(transactions: List<Transaction>) {
            val previousHash = if (transactionChain.isEmpty()) "" else transactionChain.last().hash
            val nounce: Long = 0
            //
            transactionChain.add(Block(
                    previousHash,
                    transactions,
                    nounce))
        }
    }
}