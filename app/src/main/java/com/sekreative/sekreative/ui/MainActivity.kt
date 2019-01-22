package com.sekreative.sekreative.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.sekreative.sekreative.R

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_logout.setOnClickListener {

            finish()
            return@setOnClickListener
        }
    }


}
