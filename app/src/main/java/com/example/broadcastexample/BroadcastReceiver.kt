package com.example.broadcastexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BroadcastReceiver : BroadcastReceiver() {
    var counter = 0
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onReceive(context: Context, intent: Intent) {

        scope.launch {
            if (intent.action == "Broadcast_1") {
                counter++

                withContext(Dispatchers.Main) {
                    MainActivity.updateCounters()
                }
                delay(3000)
                context.sendBroadcast(
                    Intent("Broadcast_2"),
                    "com.example.broadcastexample2.CALL_IN_THE_MIST"
                )
            }
        }

    }
}
