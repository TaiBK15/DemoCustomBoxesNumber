package com.example.democustomnumberedittext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.text.InputFilter
import android.util.AttributeSet
import android.widget.EditText

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
        val rect = Rect()
        mLineBound = rect
        super.setFilters(arrayOf<InputFilter>(InputFilter.LengthFilter(maxChar)))
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var shapeLeft = paddingLeft.toFloat()
        val shapeTop = paddingTop.toFloat()
        val shapeWidth = getCharacterWidth() + mPaddingLeftRightChar * 2
        val shapeSpacing = (getDrawableWidth() - shapeWidth * maxChar) / (maxChar - 1)
        val text = text

        val shapePaint = Paint()
        shapePaint.style = Paint.Style.STROKE
        shapePaint.strokeWidth = mStrokeWidth
        for (i in 0 until maxChar) {
            shapePaint.color = if ( i == text.length) 0xff1ba89f.toInt() else 0xffb1b1b1.toInt()
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

        var charLeft = paddingLeft.toFloat() + mPaddingLeftRightChar
        val charBottom = paddingTop.toFloat() + lineHeight
        val charWidth = getCharacterWidth()
        val chartSpacing = shapeSpacing + mPaddingLeftRightChar * 2

        for (c in text) {
            canvas?.drawText(c + "", charLeft, charBottom, paint)
            charLeft += charWidth + chartSpacing
        }
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.setSelection(text.length)
    }

    private fun getDrawableWidth() = measuredWidth - paddingLeft - paddingRight

    private fun getCharacterWidth() = paint.measureText("1")
}