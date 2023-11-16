package com.example.lesson8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import java.io.File
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.textView)
        DatabaseHelper.createDatabase(this)
        val productEntity = DatabaseHelper.getProductDao()

        lifecycleScope.launch {
            productEntity?.getProducts()?.collect {
                textView.text = it.map { it.name }.joinToString()
            }
        }

        lifecycleScope.launch {
            delay(1000)
            productEntity?.addProduct(ProductEntity("123", "name1"))
            delay(1000)
            productEntity?.addProduct(ProductEntity("1234", "name2"))
            delay(1000)
            productEntity?.addProduct(ProductEntity("12345", "name3"))
            delay(1000)
            productEntity?.addProduct(ProductEntity("123", "name1"))
        }

//        val file = File("")
//        file.writeText(fileContents)
        ExampleBottomSheetFragment().show(supportFragmentManager, "tag")
    }
}