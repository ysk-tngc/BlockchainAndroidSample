package com.example.ysk.blockchainkotlinandroid.BlockChain

final class BlockChain {

    private var currentTransactions: MutableList<Transaction> = mutableListOf()
    var chain: MutableList<Block> = mutableListOf()

    init {

    }

    fun createBlock(proof: Int, previousHash: ByteArray? = null): Block {
        val prevHash = previousHash ?: chain.last().hash()
        val timestamp = System.currentTimeMillis()
        val block = Block(chain.count() + 1,
                System.currentTimeMillis(),
                currentTransactions, proof, prevHash)

        currentTransactions = mutableListOf()
        chain.add(block)
        return block
    }

    fun createTransaction(sender: String, recipient: String, amount: Int): Int {
        val transaction = Transaction(sender, recipient, amount)
        currentTransactions.add(transaction)

        return chain.lastIndex + 1
    }
}

fun proofOfWork(lastProof: Int): Int {
    var proof = 0

    while (!validProof(lastProof, proof)) {
        proof += 1
    }
    return proof
}

fun validProof(lastProof: Int, proof: Int): Boolean {
    val guess = (lastProof.toString() + proof.toString()).toByteArray()
    val guessHash = guess.sha256().hexDigest()
    return guessHash.startsWith("0000")

}