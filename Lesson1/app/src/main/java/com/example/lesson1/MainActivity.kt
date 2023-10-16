package com.example.lesson1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = DetailActivity.createIntent(this)
        findViewById<Button>(R.id.button).setOnClickListener {
            startActivity(intent)
        }
    }
}