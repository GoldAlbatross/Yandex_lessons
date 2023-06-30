package com.example.sprint_20_rxjava

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import java.util.Optional

class MainActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable
            .just(
                1, 2, 3, 4, 5, 6
            )
            .doOnNext { Log.d("RxJava", "doOnNext 1 [after just]: $it") }
            .doOnError { Log.e("RxJava", "doOnError 1 [after just]", it) }
            .map { value ->
                val result = value * value
                if (result > 10) {
                    throw RuntimeException("Test doOnError!")
                } else {
                    result
                }
            }
            .doOnNext { Log.d("RxJava", "doOnNext 2 [after map]: $it") }
            .doOnError { Log.e("RxJava", "doOnError 2 [after map]", it) }
            .doOnNext { Log.d("RxJava", "doOnNext 3 [after map & doOnError]: $it") }
            .filter { value ->
                value > 10
            }
            .doOnError { Log.e("RxJava", "doOnError 3 [after filter]", it) }
            .subscribe(
                { value -> Log.d("RxJava", "New value: $value") },
                { error -> Log.e("RxJava", "onError", error) },
                { Log.d("RxJava", "onComplete!") }
            )

        val x = Optional.empty<Int>()
    }
}