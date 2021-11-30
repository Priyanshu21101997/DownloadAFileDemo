package com.example.downloadafiledemo

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = findViewById<TextView>(R.id.status)
        val btnDownload = findViewById<Button>(R.id.btnDownload)

        btnDownload.setOnClickListener {
            Intent(this, DownloadService::class.java).also {
                it.putExtra("FILENAME", "Nature.jpg")
                it.putExtra("URL", "https://wonderfulengineering.com/wp-content/uploads/2016/01/wallpaper-download-1-1024x576.jpg")
                startService(it)
                status.text = "Service started"
            }
        }



    }


    val receiver = MyBroadCastReceiver()

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, IntentFilter(DownloadService.NOTIFICATION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }
}