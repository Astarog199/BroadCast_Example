package com.example.broadcastexample

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

val receiver = BroadcastReceiver()

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter("Broadcast_1")
         textView = findViewById<TextView>(R.id.textView)

        registerReceiver(receiver, filter, RECEIVER_EXPORTED)

        sendBroadcast(Intent("Broadcast_2"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onResume() {
        super.onResume()
        updateCounters()
    }

    companion object {
         lateinit var textView: TextView


        fun updateCounters() {
            if (::textView.isInitialized) {
                textView.text = receiver.counter.toString()
            }
        }
    }
}