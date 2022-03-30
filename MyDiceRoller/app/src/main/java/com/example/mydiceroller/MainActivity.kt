package com.example.mydiceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.example.mydiceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rollBtn.setOnClickListener{ rollDice() }
        binding.countBtn.setOnClickListener { countUp() }
    }
    private fun rollDice() {
        val diceNum = (1..6).random()
        binding.result.text = diceNum.toString()
        Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
    }
    private fun countUp() {
        val currRes = binding.result.text
        if (!currRes.isDigitsOnly())
            binding.result.text = "1"
        else {
            val currNum = currRes.toString().toInt()
            if (currNum < 6)
                binding.result.text = (currNum + 1).toString()
        }
    }
}