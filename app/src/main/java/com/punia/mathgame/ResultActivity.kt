package com.punia.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {

    lateinit var congrats : TextView
    lateinit var score : TextView

    lateinit var buttonExit : Button
    lateinit var buttonPlayAgain: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        congrats = findViewById(R.id.congrats)
        score = findViewById(R.id.finalScore)
        buttonExit = findViewById(R.id.buttonExit)
        buttonPlayAgain = findViewById(R.id.buttonPlayAgain)

        val finalScore = intent.getIntExtra("Your Score", 0)
        score.text = "Your Score: " + finalScore
        buttonPlayAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonExit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}