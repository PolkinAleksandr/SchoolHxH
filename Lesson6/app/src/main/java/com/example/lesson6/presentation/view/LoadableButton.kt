package com.example.lesson6.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.lesson6.R
import com.example.lesson6.databinding.ViewLoadableButtonBinding

class LoadableButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private var binding: ViewLoadableButtonBinding? = null

    init {
        binding = ViewLoadableButtonBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.view_loadable_button, this, true)
        )
    }

    fun setStateLoading() = binding?.run {
        buttonLoadable.text = ""
        progressBar.isVisible = true
    }

    fun setStateData() = binding?.run {
        buttonLoadable.text = "text"
        progressBar.isVisible = false
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        binding = null
    }
}