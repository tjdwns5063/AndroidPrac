package com.example.testapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var myLevel : Int = 0
    private var myExp : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val levelUpText = findViewById<TextView>(R.id.level_text)
        val expText = findViewById<TextView>(R.id.exp_text)
        val levelUpBtn = findViewById<Button>(R.id.level_btn)
        val expUpBtn = findViewById<Button>(R.id.exp_btn)

        levelUpBtn.setOnClickListener {
            myLevel++
            levelUpText.text = myLevel.toString()
        }
        expUpBtn.setOnClickListener {
            myExp++
            expText.text = myExp.toString()
        }
    }
}