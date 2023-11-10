package com.example.lesson6.presentation.ui.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.lesson6.MyApplication
import com.example.lesson6.R
import javax.inject.Inject

/** тут реализация обычного адаптера и пример реализации лист адаптера. Это копипастить не надо **/
class ExampleAdapter @Inject constructor() : ListAdapter<Int, ExampleAdapter.ExampleViewHolder>(DIFF_UTIL) {

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
                return oldItem == newItem
            }
        }
    }

    val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        return ExampleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_digit, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    /** это отдельным файлом надо делать **/
    inner class ExampleViewHolder(view: View) : ViewHolder(view) {

        private val textView = view.findViewById<TextView>(R.id.textViewDigit)
        private val imageView = view.findViewById<ImageView>(R.id.imageViewPreview)

        fun bind(item: String) {
            textView.text = item

            Glide.with(imageView)
                .load("https://fanatics.frgimages.com/FFImage/thumb.aspx?i=/productimages/_3533000/ff_3533150-d9254664c08370f8572c_full.jpg&w=340")
                .into(imageView)
        }
    }
}