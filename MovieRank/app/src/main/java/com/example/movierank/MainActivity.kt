package com.example.movierank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movierank.databinding.ActivityMainBinding
import com.example.movierank.network.ResultData
import com.example.movierank.overview.Adapter
import com.example.movierank.viewmodel.SharedViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        setContentView(binding.root)
        val adapter = Adapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        viewModel.getMovieData().observe(this, Observer<List<ResultData>>{
            adapter.submitList(it)
        })
    }
}