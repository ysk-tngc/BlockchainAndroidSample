package com.example.ysk.blockchainkotlinandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.experimental.async

class MainActivity : AppCompatActivity() {

    private lateinit var mineButton: Button
    private lateinit var sendButton: Button
    private lateinit var logView: TextView
    private lateinit var chainView: TextView

    private val myID = "me"
    private val recipientID = "someone"
    private val server = BlockChainServer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mineButton = findViewById(R.id.mine_button)
        mineButton.setOnClickListener {
            onClickMineButton()
        }

        sendButton = findViewById(R.id.send_button)
        sendButton.setOnClickListener {
            onClickSendButton()
        }

        logView = findViewById(R.id.log_view)
        logView.setText("")
        chainView = findViewById(R.id.chain_view)

        updateChain()
    }

    private fun onClickMineButton() {
        Log.d("MainActivity", "onClickMineButton")

        val startTime = System.currentTimeMillis()
        val text = "Mining..."
        logView.text = String.format("%s\n%s", text, logView.text)

        async {
            server.mine(recipientID, { block ->
                val newLog = String.format("New Block Forged (%d s)", System.currentTimeMillis() - startTime)
                logView.text = String.format("%s\n%s", newLog, logView.text)
                Log.d("MainActivity", text + block.description())
                updateChain()
            })
        }
    }

    private fun onClickSendButton() {
        Log.d("MainActivity", "onClickSendButton")

        val index = server.send(myID, recipientID, 5)
        val text = "Transaction will be added to Block: " + index
        logView.text = String.format("%s\n%s", text, logView.text)
        Log.d("MainActivity", text)
        updateChain()
    }

    private fun updateChain() {
        val chain = server.chain()
        var text = "chain:\n"
        for (block in chain) {
            text += block.description() + "\n"
        }
        chainView.text = text
        print(text)
    }
}
