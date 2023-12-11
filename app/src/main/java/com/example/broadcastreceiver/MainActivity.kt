package com.example.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    var br : MyReceiver?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var br = MyReceiver()
        // Xử lý action airplance mode
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        this.registerReceiver(br, filter)

        // Xử lý action connectivity manager
        var intentFilter : IntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(br , intentFilter)

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
//        unregisterReceiver(br)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }
}