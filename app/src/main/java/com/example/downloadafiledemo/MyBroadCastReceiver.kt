package com.example.downloadafiledemo

import android.app.Activity.RESULT_OK
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

class MyBroadCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {


        val intent = Intent(DownloadService.NOTIFICATION)

            val string = intent.getStringExtra("FILEPATH");
            val resultCode = intent.getIntExtra("RESULT",-1);
            if (resultCode == RESULT_OK) {

                Log.d("BroadCastLog","Download Completed")
            } else {

                Log.d("BroadCastLog","Download failed");
            }
//        }
    }
}