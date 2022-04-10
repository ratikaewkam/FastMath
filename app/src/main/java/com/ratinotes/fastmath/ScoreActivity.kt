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
import org.w3c.dom.Text

class ScoreActivity : AppCompatActivity() {
    private lateinit var nameIntent: TextView
    private lateinit var scoreIntent: TextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        nameIntent = findViewById(R.id.nameIntent)
        scoreIntent = findViewById(R.id.scoreIntent)
        btnBack = findViewById(R.id.btnBack)

        var intentValues = intent.extras
        var name = intentValues!!.getString("name")
        var score = intentValues!!.getString("score")

        nameIntent.text = name
        scoreIntent.text = score

        customActionBar()

        btnBack.setOnClickListener {
            var mainIntent = Intent(this,  MainActivity::class.java)
            mainIntent.putExtra("score", score)
            startActivity(mainIntent)
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