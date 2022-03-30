package com.example.empty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.empty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.clickBtn.setOnClickListener {
            ++count
            binding.ret.text = count.toString()
        }
    }
}