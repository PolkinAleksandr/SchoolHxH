package com.example.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.textView).setOnClickListener {
            supportFragmentManager.commit {
                add(R.id.fragmentContainerView, FirstFragment.newInstance(), "tag")
                addToBackStack(null)
            }
        }
    }
}