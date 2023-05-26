package com.example.sprint18_fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class Screen1Activity : AppCompatActivity(R.layout.activity_screen1) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("qqq", "Screen1 -> onCreate")

        findViewById<TextView>(R.id.screen1Title).text = "Screen 1"

        findViewById<Button>(R.id.screen1ButtonToScreen2).setOnClickListener {
            Log.d("qqq", "Screen1 -> Click on 'To screen 2'")
            openScreen2()
        }
    }

    private fun openScreen2() {
        val intent = Intent(this, Screen2Activity::class.java)

        this.startActivity(intent)
    }

}