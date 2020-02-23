package com.example.cusomedittext

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.EditText
import android.text.InputFilter



class CustomNumberEdittext : EditText {

    private var mLineBound: Rect? = null
    private var mPaddingLeftRightChar = 0f
    private var mStrokeWidth = 0f
    private var maxChar = 0

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
        if (context == null) return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNumberEdittext)
        maxChar = typedArray.getInteger(R.styleable.CustomNumberEdittext_maxChar, 1)
        mPaddingLeftRightChar = typedArray.getDimension(R.styleable.CustomNumberEdittext_paddingLeftRightChar, 5f)
        mStrokeWidth = typedArray.getDimension(R.styleable.CustomNumberEdittext_strokeWidthChar, 3f)
        mLineBound = Rect()
        super.setFilters(arrayOf<InputFilter>(InputFilter.LengthFilter(maxChar)))
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        var charLeft = paddingLeft.toFloat() + mPaddingLeftRightChar
        val charBottom = paddingTop.toFloat() + lineHeight
        val charWidth = getCharacterWidth()
        val chartSpacing = (getDrawableWidth() - charWidth * maxChar - paddingLeft - paddingRight) / (maxChar - 1)
        val text = text

        var shapeLeft = paddingLeft.toFloat()
        val shapeTop = paddingTop.toFloat()
        val shapeWidth = charWidth + mPaddingLeftRightChar * 2
        val shapeSpacing = chartSpacing - mPaddingLeftRightChar * 2

        val shapePaint = Paint()
        shapePaint.color = 0xffb1b1b1.toInt()
        shapePaint.style = Paint.Style.STROKE
        shapePaint.strokeWidth = mStrokeWidth
        for (i in 0 until maxChar + 1) {
            canvas?.drawRoundRect(
                RectF(
                    shapeLeft,
                    shapeTop + mStrokeWidth,
                    shapeLeft + shapeWidth,
                    shapeTop + measuredHeight - mStrokeWidth
                ),
                10f,
                10f,
                shapePaint
            )
            shapeLeft += shapeWidth + shapeSpacing
        }

        for (c in text) {
            canvas?.drawText(c + "", charLeft, charBottom, paint)
            charLeft += charWidth + chartSpacing
        }
    }

    private fun getDrawableWidth() = measuredWidth - paddingLeft - paddingRight

    private fun getCharacterWidth() = paint.measureText("1")
}