package com.neosoft.apptimer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

open class BaseActivity :AppCompatActivity(){

    var broadcastReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("BaseActivity", "onReceive:actionName ${intent?.action}")
            if(intent != null){
                val getAction = intent.action
                if(getAction == "TriggerTask"){
                    val builder = AlertDialog.Builder(this@BaseActivity,
                        com.google.android.material.R.style.AlertDialog_AppCompat)
                        .create()

                    val view = layoutInflater.inflate(R.layout.customview_layout,null)
                    val  button = view.findViewById<Button>(R.id.dialogDismiss_button)
                    builder.setView(view)
                    button.setOnClickListener {
                        builder.dismiss()
                    }
                    builder.setCanceledOnTouchOutside(false)
                    builder.show()
                }


            }
        }
    }

    override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, IntentFilter(
            "TriggerTask"
        ))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }
}