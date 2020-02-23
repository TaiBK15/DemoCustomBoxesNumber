package com.example.cusomedittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import android.text.InputFilter
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var position = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtParams = edt?.layoutParams as LinearLayout.LayoutParams
//        Log.d(
//            "tain",
//            "onCreate getWidthEdt width = " + edt?.width + " paramWidth = " + edtParams.width
//        )
        btn?.setOnClickListener {
            getWidthEdt()
        }
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
