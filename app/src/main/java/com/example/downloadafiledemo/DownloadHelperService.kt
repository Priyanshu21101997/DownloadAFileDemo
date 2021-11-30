package com.example.downloadafiledemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class DownloadHelperService:Service() {

    val TAG = "TAGS"
    init{
        Log.d(TAG,"Service is running")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val dataString = intent?.getStringExtra("EXTRA_DATA");
//        if(dataString!=null){
//            Log.d(TAG,dataString)
//        }

        // Start new thread if you have complex calculations to do


        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Service is Destroyed")
    }


}