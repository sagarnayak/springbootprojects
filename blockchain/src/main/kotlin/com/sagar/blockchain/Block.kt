package com.sagar.blockchain

data class Block(
    var transactions: ArrayList<String> = ArrayList(),
    var blockHash: Int = 0,
    var previousBlockHash: Int = 0
) {
    companion object {
        fun generateBlock(transactions: ArrayList<String>, previousBlockHash: Int): Block {
            val calculatedBlockHash =
                arrayOf(arrayOf(transactions).contentHashCode(), previousBlockHash).contentHashCode()
            return Block(transactions, calculatedBlockHash, previousBlockHash)
        }
    }

    override fun toString(): String {
        return """
            <<< Block >>>
            hash : $blockHash
            previous hash : $previousBlockHash
            transactions
            $transactions
        """.trimIndent()
    }
}