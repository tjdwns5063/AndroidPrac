package com.example.coroutineprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coroutineFunc()
        GlobalScope.launch {
            for (i in 1..10) {
                Log.i("coroutine", i.toString())
                delay(100)
            }
        }
    }
}

fun coroutineFunc() = runBlocking {
    launch {
        for (i in 11..20) {
            Log.i("coroutine", i.toString())
            delay(100)
        }
    }
}
