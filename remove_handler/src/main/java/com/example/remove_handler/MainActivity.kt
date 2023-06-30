package com.example.remove_handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private const val TAG = "qqq"

class MainActivity : AppCompatActivity() {

    private var textwatcher: TextWatcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<EditText>(R.id.text)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val job = CoroutineScope(Dispatchers.Main)

        textwatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != "+") {
                    Log.d(TAG, "onTextChanged: $s, textwatcher: $textwatcher")
                    job.launch {
                        delay(7000L)
                        textView.setText("+")
                    }
                }

            }
            override fun afterTextChanged(s: Editable?) {}

        }
        textView.addTextChangedListener(textwatcher)


        btn1.setOnClickListener {
            textView.removeTextChangedListener(textwatcher)
            Log.d(TAG, "finish: ${job.isActive}")
        }
        btn2.setOnClickListener {
            textwatcher = null
            Log.d(TAG, "onTextChanged: $textwatcher")
        }

    }
}