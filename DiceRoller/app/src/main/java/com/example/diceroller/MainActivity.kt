package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var diceImage1 : ImageView
    lateinit var diceImage2 : ImageView
    var firstDiceResource = 0
    var secondDiceResource = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceImage1 = findViewById(R.id.dice_image1)
        diceImage2 = findViewById(R.id.dice_image2)

        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        val countButton: Button = findViewById(R.id.count_button)
        countButton.setOnClickListener {
            countUp()
        }
    }
    private fun rollDice() {
        //val resultImg: ImageView = findViewById(R.id.dice_image)
        firstDiceResource = getRandomDiceImage()
        secondDiceResource = getRandomDiceImage()
        diceImage1.setImageResource(firstDiceResource)
        diceImage2.setImageResource(secondDiceResource)
        Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
    }

    private fun getRandomDiceImage() : Int {
        val randomInt = (1..6).random()
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        return (drawableResource)
    }

    private fun plusDice(diceResource: Int) = when (diceResource) {
        R.drawable.dice_1 -> R.drawable.dice_2
        R.drawable.dice_2 -> R.drawable.dice_3
        R.drawable.dice_3 -> R.drawable.dice_4
        R.drawable.dice_4 -> R.drawable.dice_5
        R.drawable.dice_5 -> R.drawable.dice_6
        R.drawable.dice_6 -> R.drawable.dice_6
        else -> R.drawable.empty_dice
    }

    private fun countUp() {
        val fisrtDiceNum = plusDice(firstDiceResource)
        val secondDiceNum = plusDice(secondDiceResource)

        diceImage1.setImageResource(fisrtDiceNum)
        diceImage2.setImageResource(secondDiceNum)
        firstDiceResource = fisrtDiceNum
        secondDiceResource = secondDiceNum
        Toast.makeText(this, "count clicked", Toast.LENGTH_SHORT).show()
    }
}