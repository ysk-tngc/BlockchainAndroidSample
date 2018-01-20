package com.example.ysk.blockchainkotlinandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mineButton: Button
    private lateinit var sendButton: Button
    private lateinit var logView: TextView
    private lateinit var chainView: TextView

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
        chainView = findViewById(R.id.chain_view)
    }

    private fun onClickMineButton() {
        Log.d("MainActivity", "onClickMineButton")
    }

    private fun onClickSendButton() {
        Log.d("MainActivity", "onClickSendButton")
    }
}
