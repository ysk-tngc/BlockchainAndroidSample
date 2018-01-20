package com.example.ysk.blockchainkotlinandroid

import com.example.ysk.blockchainkotlinandroid.BlockChain.Block
import com.example.ysk.blockchainkotlinandroid.BlockChain.BlockChain
import com.example.ysk.blockchainkotlinandroid.BlockChain.proofOfWork
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class BlockChainServer {

    val blockChain = BlockChain()

    fun chain(): List<Block> {
        return blockChain.chain
    }

    fun send(sender: String, recipient: String, amount: Int): Int {
        chain().forEach { }
        return blockChain.createTransaction(sender, recipient, amount)
    }

    suspend fun mine(recipient: String, completion: (Block) -> Unit) {
        val resultBlock = mineAsync(recipient).await()
        async(UI) {
            completion(resultBlock)
        }
    }

    private fun mineAsync(recipient: String) = async(CommonPool) {
        val lastProof = chain().last().proof
        val proof = proofOfWork(lastProof)

        blockChain.createTransaction("0", recipient, 1)

        return@async blockChain.createBlock(proof)
    }

}