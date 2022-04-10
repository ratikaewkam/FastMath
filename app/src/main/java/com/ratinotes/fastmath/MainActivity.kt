package com.ratinotes.fastmath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var btnPlay: Button
    private lateinit var scoreDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlay = findViewById(R.id.btnPlay)
        scoreDisplay = findViewById(R.id.scoreDisplay)

        var intentValues = intent.extras
        var score = intentValues?.getString("score")

        if (score != null) {
            scoreDisplay.text = score
        } else {
            scoreDisplay.text = "0"
        }

        customActionBar()
        btnPlay.setOnClickListener {
            val enterNameIntent = Intent(this, EnterNameActivity::class.java)
            startActivity(enterNameIntent)
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
}