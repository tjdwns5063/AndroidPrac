package com.example.movierank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movierank.databinding.ActivityMainBinding
import com.example.movierank.network.InfoApi
import com.example.movierank.overview.Adapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = Adapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = InfoApi.infoApiService.getInfo("c3c3c19c98650f46d506e0f334259831", "20220515").movieInfos.dailyBoxOfficeList
                adapter.submitList(result)
                Log.i("Network", "ret: ${result[0].movieNm}")
            } catch (e: Exception) {
                Log.i("Network", "ret: ${e.message}")
            }
        }
    }
}