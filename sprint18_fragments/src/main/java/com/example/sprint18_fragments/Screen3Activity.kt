package com.example.sprint18_fragments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

class Screen3Activity : AppCompatActivity(R.layout.activity_screen3) {

//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        Log.d("qqq", "Screen3 -> onNewIntent")
//    }


    private val handler = Handler(Looper.getMainLooper())
    // Описали callback для обработки нажатия на Back
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showWarningToast()
            disableCallback()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("qqq", "Screen3 -> onCreate")

        findViewById<TextView>(R.id.screen3Title).text = "Screen 3"

        findViewById<Button>(R.id.screen3ButtonToScreen1).setOnClickListener {
            Log.d("qqq", "Screen3 -> Click on 'To screen 1'")
            openScreen1()
        }

        findViewById<Button>(R.id.screen3ButtonBack).setOnClickListener {
            Log.d("qqq", "Screen3 -> Click on 'Back'")
            backToPreviousScreen()
        }

        findViewById<Button>(R.id.screen3ButtonToScreen3).setOnClickListener {
            Log.d("qqq", "Screen3 -> Click on 'To screen 3'\n$this")
            openScreen3()
        }
        findViewById<Button>(R.id.screen3ButtonToScreen1WithClear).setOnClickListener {
            Log.d("qqq", "Screen3 -> Click on 'Back to Screen 1'")
            backToScreen1()
        }
        // Добавляем созданный callback к Dispatcher
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun openScreen1() {
        this.startActivity(Intent(this, Screen1Activity::class.java))
    }
    private fun openScreen3() {
        this.startActivity(Intent(this, Screen3Activity::class.java))
    }
//private fun openScreen3() {
//    val intent = Intent(this, Screen3Activity::class.java)
//    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
//    this.startActivity(intent)
//}

    private fun backToPreviousScreen() {
        Log.d("qqq", "backToPreviousScreen")
        onBackPressedDispatcher.onBackPressed()

    }
    // Показываем Toast с предупреждением
    private fun showWarningToast() {
        Toast.makeText(this, "Нажмите ещё раз, чтобы перейти на предыдущий экран", Toast.LENGTH_SHORT).show()
    }

    private fun disableCallback() {
        // Отключаем callback
        callback.isEnabled = false

        // И через две секунды включаем его обратно
        handler.postDelayed({ callback.isEnabled = true }, 2000L)
    }

//    private fun backToScreen1() {
//        val intent = Intent(this, Screen1Activity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        this.startActivity(intent)
//    }
    private fun backToScreen1() {
        val intent = Intent(this, Screen1Activity::class.java)

        this.startActivity(intent)
    }
}
