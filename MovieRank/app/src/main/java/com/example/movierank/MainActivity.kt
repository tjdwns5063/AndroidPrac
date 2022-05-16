package com.example.movierank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.movierank.network.InfoApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = InfoApi.infoApiService.getInfo("c3c3c19c98650f46d506e0f334259831", "20220515")
                Log.i("Network", "ret: ${result.movieInfos.dailyBoxOfficeList[0].movieNm}")
            } catch (e: Exception) {
                Log.i("Network", "ret: ${e.message}")
            }
        }
    }
}