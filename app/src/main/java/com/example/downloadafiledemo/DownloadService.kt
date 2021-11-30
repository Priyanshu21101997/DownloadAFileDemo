package com.example.downloadafiledemo

import android.app.Activity
import android.app.IntentService
import android.content.ContextWrapper
import android.content.Intent
import android.os.Environment
import android.util.Log
import java.io.*
import java.net.URL

class DownloadService:IntentService("DownloadService") {
//class DownloadService:Service() {


    val TAG = "MyService"

    companion object {
        private var result = Activity.RESULT_CANCELED
        const val NOTIFICATION = "service receiver"
    }

//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//
//        val urlPath = intent?.getStringExtra("URL")
//        val fileName = intent?.getStringExtra("FILENAME")
//
//        val output = File(Environment.getExternalStorageDirectory(), fileName)
//        Log.d("BroadCastLog","Output path is $output")
//
//        if (output.exists()) {
//            Log.d("BroadCastLog","It exists")
//
//            output.delete()
//        }
//
//        var stream: InputStream? = null
//        var fos: FileOutputStream? = null
//
//        try {
//            val url = URL(urlPath)
//            Log.d("BroadCastLog","URL is $url")
//
//            stream = url.openConnection().getInputStream()
//            Log.d("BroadCastLog", "Stream is $stream")
//
//            val reader = InputStreamReader(stream)
//            fos = FileOutputStream(output.path)
//
//            var next = -1
//            while (reader.read().also { next = it } != -1) {
//                fos.write(next)
//            }
//
//            result = Activity.RESULT_OK;
//        } catch (e: Exception) {
//            Log.d("BroadCastLog","Exception occured $e")
//            e.printStackTrace();
//        } finally {
//            if (stream != null) {
//                try {
//                    stream.close();
//                } catch (e: IOException) {
//                    e.printStackTrace();
//                }
//            }
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (e: IOException) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        publishResults(output.absolutePath, result);
//        return START_REDELIVER_INTENT
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }


    override fun onHandleIntent(intent: Intent?) {


        val urlPath = intent?.getStringExtra("URL")
        val fileName = intent?.getStringExtra("FILENAME")

        val output = getPath(fileName)

        if (output.exists()) {
            output.delete()
        }

        var stream: InputStream? = null
        var fos: FileOutputStream? = null

        try {
            val url = URL(urlPath)
            stream = url.openConnection().getInputStream()

            val reader = InputStreamReader(stream)
            fos = FileOutputStream(output.path)

            var next = -1
            while (reader.read().also { next = it } != -1) {
                fos.write(next)
            }

            result = Activity.RESULT_OK;

        } catch (e: Exception) {

            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (e: IOException) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (e: IOException) {
                    e.printStackTrace();
                }
            }
        }

        publishResults(output.absolutePath, result);
    }


    private fun publishResults(outputPath: String, result: Int) {

        val intent = Intent(NOTIFICATION)

        intent.putExtra("FILEPATH", outputPath)
        intent.putExtra("RESULT", result)
        sendBroadcast(intent)
    }

    private fun getPath(fileName: String?): File {
        val contextWrapper = ContextWrapper(applicationContext)
        val downloadDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(downloadDirectory, fileName)

    }


    }