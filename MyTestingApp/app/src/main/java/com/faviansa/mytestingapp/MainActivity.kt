package com.faviansa.mytestingapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnSetValue: Button
    private lateinit var tvText: TextView
    private var names = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tv_text)

        btnSetValue = findViewById(R.id.btn_set_value)
        btnSetValue.setOnClickListener(this)

        names.add("Hibou Gaming")
        names.add("Bebou")
        names.add("Abou")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + v.paddingLeft,
                systemBars.top + v.paddingTop,
                systemBars.right + v.paddingRight,
                systemBars.bottom + v.paddingBottom
            )
            insets
        }
    }

    override fun onClick(view: View) {
        if(view.id == R.id.btn_set_value) {
            // tvText.text = "19"
            Log.d("MainActivity", names.toString())
            val name = StringBuilder()
            for(i in 0..2) {
                name.append(names[i]).append("\n")
            }
            tvText.text = name.toString()
        }
    }
}