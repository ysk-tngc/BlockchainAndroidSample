package com.example.ysk.blockchainkotlinandroid.BlockChain

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import java.security.MessageDigest

data class Block(
        val index: Int,
        val timestamp: Long,
        val transactions: List<Transaction>,
        val proof: Int,
        val previousHash: ByteArray) {

    private val moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


    fun description(): String {
        val adapter = moshi.adapter(Block::class.java)
        return adapter.toJson(this)
    }

    fun hash(): ByteArray {
        val description = description()
        return description.toByteArray().sha256()
    }
}

fun ByteArray.sha256(): ByteArray {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    messageDigest.update(this)
    return messageDigest.digest()
}

private val HEX_CHARS = "0123456789ABCDEF".toCharArray()

fun ByteArray.hexDigest(): String {
    val result = StringBuffer()

    forEach {
        val octet = it.toInt()
        val firstIndex = (octet and 0xF0).ushr(4)
        val secondIndex = octet and 0x0F
        result.append(HEX_CHARS[firstIndex])
        result.append(HEX_CHARS[secondIndex])
    }

    return result.toString()
}


