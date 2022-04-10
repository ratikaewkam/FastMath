package com.ratinotes.fastmath

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.*
import com.squareup.picasso.Picasso
import kotlin.random.Random

class PlayActivity : AppCompatActivity() {
    private lateinit var timeDisplay: TextView
    private lateinit var namePlayer: TextView
    private lateinit var num1: TextView
    private lateinit var num2: TextView
    private lateinit var num3: TextView
    private lateinit var editNumber: EditText
    private lateinit var btnSend: Button
    private lateinit var scorePlayer: TextView
    private var name: String? = null
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        namePlayer = findViewById(R.id.namePlayer)
        timeDisplay = findViewById(R.id.timeDisplay)
        num1 = findViewById(R.id.num1_display)
        num2 = findViewById(R.id.num2_display)
        num3 = findViewById(R.id.num3_display)
        editNumber = findViewById(R.id.editNumber)
        btnSend = findViewById(R.id.btnSend)
        scorePlayer = findViewById(R.id.scorePlayer)

        var intentValues = intent.extras
        name = intentValues!!.getString("name")
        namePlayer.text = name

        customActionBar()
        displayTimer()
        randomAndDisplayNumber()
        btnSend.setOnClickListener {
            processApp()
        }
    }

    private fun customActionBar() {
        var inflater = this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var actionBarView = inflater.inflate(R.layout.bar_customview, null)
        val actionBarImage = actionBarView.findViewById<ImageView>(R.id.barImage)
        val actionTextView = actionBarView.findViewById<TextView>(R.id.barTextView)
        actionTextView.text = "Fast Math"
        Picasso.get().load(R.drawable.math).placeholder(R.drawable.math).into(actionBarImage)

        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.customView = actionBarView
    }

    private fun displayTimer() {
        object: CountDownTimer(121000, 1000) {
            override fun onTick(time: Long) {
                changeColorByTime(time)
            }

            override fun onFinish() {
                var scoreIntent = Intent(this@PlayActivity, ScoreActivity::class.java)
                scoreIntent.putExtra("name", name)
                scoreIntent.putExtra("score", score.toString())
                startActivity(scoreIntent)
                finish()
            }
        }.start()
    }

    private fun randomAndDisplayNumber(){
        var randomNum1: Int = Random.nextInt(10, 99)
        var randomNum2: Int = Random.nextInt(10, 99)
        var randomNum3: Int = Random.nextInt(10, 99)

        num1.text = randomNum1.toString()
        num2.text = randomNum2.toString()
        num3.text = randomNum3.toString()
    }

    private fun processApp() {
        var edtNumber = editNumber.text.trim().toString()
        if (!TextUtils.isEmpty(edtNumber)) {
            var number1 = num1.text.toString().toInt()
            var number2 = num2.text.toString().toInt()
            var number3 = num3.text.toString().toInt()
            var n = edtNumber.toInt()
            var resultCheck = number1 + number2 + number3
            var timer = timeDisplay.text.toString().toInt()
            if (timer != 0 && n == resultCheck) {
                score++
                scorePlayer.text = score.toString()
                editNumber.text = null
                randomAndDisplayNumber()
            } else {
                editNumber.text = null
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Value mustn't empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun changeColorByTime(time: Long) {
        var cTime = (time.toInt()) / 1000
        timeDisplay.text = cTime.toString()
        if ((cTime >= 80) && (cTime <= 120)) {
            timeDisplay.setTextColor(Color.parseColor("#50C878"))
        } else if ((cTime >= 50) && (cTime <= 79)) {
            timeDisplay.setTextColor(Color.parseColor("#FFD000"))
        } else if ((cTime >= 20) && (cTime <= 49)) {
            timeDisplay.setTextColor(Color.parseColor("#EF7215"))
        } else if ((cTime >= 0) && (cTime <= 19)) {
            timeDisplay.setTextColor(Color.parseColor("#D30000"))
        }
    }
}