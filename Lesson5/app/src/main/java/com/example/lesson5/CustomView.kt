package com.example.lesson5

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class  CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {



    private var lineColor = ContextCompat.getColor(context, R.color.black)
    private var something = POSITION.START
    private val newWidth = resources.getDimensionPixelSize(R.dimen.custom_view_width)
    private val newHeight = resources.getDimensionPixelSize(R.dimen.custom_view_height)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textSize = 100f
    }
    private var right = 100f

    private val simpleDateFormat = SimpleDateFormat("MM.dd", Locale.getDefault())
    private val date = simpleDateFormat.format(Date())

    private val animation = ValueAnimator.ofFloat(0f, 100f).apply {
        duration = 1000
        addUpdateListener {
            right = (it.animatedValue as Float)
            invalidate()
        }

    }

    private val gestureDetector = GestureDetector(context,
        object : GestureDetector.OnGestureListener {
            override fun onDown(p0: MotionEvent): Boolean {
                return false
            }

            override fun onShowPress(p0: MotionEvent) {

            }

            override fun onSingleTapUp(p0: MotionEvent): Boolean {
                return false
            }

            override fun onScroll(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
                return false
            }

            override fun onLongPress(p0: MotionEvent) {
                animateLine()
            }

            override fun onFling(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
                return false
            }
        })

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0)
        lineColor = typedArray.getColor(R.styleable.CustomView_lineColor, lineColor)
        val smth = typedArray.getIndex(R.styleable.CustomView_something)
        something = if (smth == 2) POSITION.END else POSITION.START
        paint.color = lineColor
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(newWidth, newHeight)
    }

    val path = Path().apply {
        val newWidth = 200f
        val newHeight = 200f
        moveTo(newWidth, newHeight)
        lineTo(newWidth + 50, newHeight + 50)
        lineTo(newWidth + 100, newHeight - 100)
        lineTo(newWidth, newHeight)
        close()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.drawRect(10f, 10f, right, 100f, paint)
        canvas.drawText(date, 10f, 160f, paint)
        canvas.drawRoundRect(10f, 10f, right, 100f, 10f, 10f, paint)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when {
            gestureDetector.onTouchEvent(event) -> true
            event.action == MotionEvent.ACTION_UP -> {
                animateLine()
                true
            }

            else -> false
        }
    }

    private fun animateLine() {
        animation.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animation.cancel()
    }

    enum class POSITION {
        START, END
    }
}