package com.punia.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class AddActivity : AppCompatActivity() {
    lateinit var scoreVal : TextView
    lateinit var lifeVal : TextView
    lateinit var timeVal : TextView

    lateinit var ques : TextView
    lateinit var ans : EditText

    lateinit var buttonOk : Button
    lateinit var buttonNext : Button

    lateinit var timer : CountDownTimer
    private val startTimerInMillis : Long = 20000
    var timeLeftInMillis : Long = startTimerInMillis

    var correctAns = 0
    var userScore = 0
    var userLife = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.title = "Addition"

        scoreVal = findViewById(R.id.scoreVal)
        lifeVal = findViewById(R.id.lifeVal)
        timeVal = findViewById(R.id.timeVal)
        ques = findViewById(R.id.ques)
        ans = findViewById(R.id.ans)
        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameQues()

        buttonOk.setOnClickListener {
            val input = ans.text.toString()

            if(input == "")
            {
                Toast.makeText(applicationContext, "Please enter a number or press NEXT", Toast.LENGTH_LONG).show()
            }
            else
            {
                pauseTimer()
                val userAns = input.toInt()
                if(userAns == correctAns)
                {
                    userScore += 10
                    ques.text = "Correct Ans!!"
                    scoreVal.text = userScore.toString()
                }
                else
                {
                    userLife--;
                    ques.text = "Wrong Ans!!"
                    lifeVal.text = userLife.toString()
                }
            }
        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            gameQues()
            ans.setText("")

            if(userLife <=0)
            {
                Toast.makeText(applicationContext, "GAME OVER!!", Toast.LENGTH_LONG).show()
                val intent = Intent(this@AddActivity, ResultActivity :: class.java)
                intent.putExtra("Your Score", userScore)
                startActivity(intent)
                finish()
            }
            else
            {
                gameQues()
            }
        }
    }

    fun gameQues()
    {
        var num1 = Random.nextInt(0, 100)
        var num2 = Random.nextInt(0, 100)
        ques.text = "$num1 + $num2"

        correctAns = num1 + num2
        startTimer()
    }

    fun startTimer()
    {
        timer = object : CountDownTimer(timeLeftInMillis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                userLife--
                lifeVal.text = userLife.toString()
                ques.text = "Time Over!"
            }

        }.start()
    }

    fun updateText()
    {
        val remainingTime : Int = (timeLeftInMillis / 1000).toInt()
        timeVal.text = String.format(Locale.getDefault(), "%2d", remainingTime)
    }
    fun pauseTimer()
    {
        timer.cancel()
    }
    fun resetTimer()
    {
        timeLeftInMillis = startTimerInMillis
        updateText()
    }
}