package com.stenzotech.ochrekeyboard

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private val gold = Color.rgb(180, 154, 112)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 40, 28, 28)
            setBackgroundColor(Color.rgb(23, 23, 23))
        }
        root.addView(TextView(this).apply {
            text = "Ochre Heart Keyboard"
            textSize = 27f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        })
        root.addView(TextView(this).apply {
            text = "\n♥  Champagne Ochre  #B49A70\n"
            textSize = 22f
            setTextColor(gold)
            gravity = Gravity.CENTER
        })
        root.addView(TextView(this).apply {
            text = "Enable the keyboard, then choose it as your default input method."
            textSize = 15f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        })
        root.addView(actionButton("1. Enable keyboard") {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        })
        root.addView(actionButton("2. Choose Ochre Heart Keyboard") {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showInputMethodPicker()
        })
        root.addView(TextView(this).apply {
            text = "\nPrivacy: this starter app has no internet permission and does not collect typed text. The coloured heart is currently a styled keyboard key; Android text fields may render text hearts using their own font."
            textSize = 12f
            setTextColor(Color.GRAY)
        })
        setContentView(root)
    }
    private fun actionButton(label: String, action: () -> Unit) = Button(this).apply {
        text = label
        setTextColor(Color.WHITE)
        setBackgroundColor(0xFF66543A.toInt())
        setOnClickListener { action() }
        layoutParams = LinearLayout.LayoutParams(-1, -2).apply { topMargin = 18 }
    }
}
