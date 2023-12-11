package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        // catch Airplance mode
        val isAirPlanceMode: Boolean = intent.getBooleanExtra("state", false)
        if (isAirPlanceMode) {
            Toast.makeText(context, "Chế độ máy bay đang bật", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Chế độ máy bay đang tắt", Toast.LENGTH_LONG).show()
        }

        //catch network state
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.action)){
            if (isNetworkAvailable(context)){
                Toast.makeText(context, "Internet connected", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context, "Internet Disconnected", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isNetworkAvailable( context : Context) : Boolean{
        var connectivityManager : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager == null){
            return false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            var network : Network? = connectivityManager.activeNetwork
            if (network == null){
                return false
            }

            var capabilities : NetworkCapabilities? = connectivityManager.getNetworkCapabilities(network)
            return capabilities != null  && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
        else {
            var networkInfo : NetworkInfo? = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}