package com.ratinotes.fastmath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.*
import com.squareup.picasso.Picasso

class EnterNameActivity : AppCompatActivity() {
    private lateinit var editName: EditText
    private lateinit var btnSet: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_name)

        editName = findViewById(R.id.editName)
        btnSet = findViewById(R.id.btnSet)

        customActionBar()
        btnSet.setOnClickListener {
            transferData()
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
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.customView = actionBarView
    }

    private fun transferData() {
        var name = editName.text.trim().toString()

        if (!TextUtils.isEmpty(name)) {
            var playIntent = Intent(this, PlayActivity::class.java)
            playIntent.putExtra("name", name)
            startActivity(playIntent)
            finish()
        } else {
            Toast.makeText(this, "Value mustn't empty", Toast.LENGTH_SHORT).show()
        }
    }
}