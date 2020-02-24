package com.example.democustomnumberedittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var position = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Log.d(
//            "tain",
//            "onCreate getWidthEdt width = " + edt?.width + " paramWidth = " + edtParams.width
//        )
        btn?.setOnClickListener {
            getWidthEdt()
        }

        edt?.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                edt?.setSelection(edt?.text?.length ?: 0, edt?.text?.length ?: 0)
                edt?.requestFocus()
            }
            return@setOnTouchListener false
        }

        edt?.requestFocus()
    }

    fun getWidthEdt() {
//        val edtParams = edt?.layoutParams as LinearLayout.LayoutParams
//        edt?.max
////        Log.d(
////            "tain",
////            "getWidthEdt width = " + edt?.width + " paramWidth = " + edtParams.width
////        )
    }
}
