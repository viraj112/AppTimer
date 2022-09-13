package com.neosoft.apptimer

import android.app.Application
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MyApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("BaseActivity", "MyApplication")
        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("BaseActivity", "sendBroadcast")
            val intent = Intent()
            intent.action = "TriggerTask"
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }, (1*14000).toLong())
    }
}