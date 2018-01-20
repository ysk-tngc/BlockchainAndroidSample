package com.example.ysk.blockchainkotlinandroid.BlockChain


data class Transaction(
        val sender: String,
        val recipient: String,
        val amount: Int)