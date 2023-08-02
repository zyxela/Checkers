package com.example.checkers.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.checkers.R


class MenuActivity : AppCompatActivity() {

    private lateinit var play: Button
    private lateinit var playOnline: Button
    private lateinit var playWithAI: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        play = findViewById(R.id.play)
        playOnline = findViewById(R.id.online)
        playWithAI = findViewById(R.id.playAi)

        play.setOnClickListener {
            startActivity(Intent(this, PlaygroundActivity::class.java))
        }

        playOnline.setOnClickListener {
            startActivity(Intent(this, SelectDevicesActivity::class.java))
        }
    }
}