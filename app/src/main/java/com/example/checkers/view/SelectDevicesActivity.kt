package com.example.checkers.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.checkers.R

class SelectDevicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_devices_activity)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView3) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<Button>(R.id.server).setOnClickListener {
            navController.navigate(R.id.action_beClient_to_beServer)
        }


        findViewById<Button>(R.id.client).setOnClickListener {
            navController.navigate(R.id.action_beServer_to_beClient)
        }
    }
}