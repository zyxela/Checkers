package com.example.checkers.view.fragments

import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.checkers.R
import com.example.checkers.data.Server
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BeServer : Fragment(R.layout.be_server) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.ip_adress).text =
            getLocalIpAddress() ?: "please, turn on Wi-Fi"

        view.findViewById<Button>(R.id.createLob).setOnClickListener {
            val server = Server(8787)
            GlobalScope.launch {
                serverBehavior(server)
            }
        }
    }

    private fun getLocalIpAddress(): String? {
        try {

            val wifiManager: WifiManager = context?.getSystemService(WIFI_SERVICE) as WifiManager
            return ipToString(wifiManager.connectionInfo.ipAddress)
        } catch (ex: Exception) {
            Log.e("IP Address", ex.toString())
        }

        return null
    }

    private fun ipToString(i: Int): String {
        return (i and 0xFF).toString() + "." +
                (i shr 8 and 0xFF) + "." +
                (i shr 16 and 0xFF) + "." +
                (i shr 24 and 0xFF)

    }

    private fun serverBehavior(server: Server)= runBlocking {
        launch {
            server.startListening()
        }.join()
    }
}