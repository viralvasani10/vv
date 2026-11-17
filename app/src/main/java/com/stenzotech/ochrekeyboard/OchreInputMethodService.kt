package com.stenzotech.ochrekeyboard

import android.graphics.Color
import android.graphics.Typeface
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class OchreInputMethodService : InputMethodService() {
    override fun onCreateInputView(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(5, 8, 5, 8)
            setBackgroundColor(Color.rgb(23, 23, 23))
        }
        val hearts = LinearLayout(this).apply { gravity = Gravity.CENTER }
        // Custom champagne-olive key artwork; taps insert the closest standard
        // Unicode heart supported in Instagram comments and other text fields.
        addHeartKey(hearts, Color.rgb(180,154,112))
        addHeartKey(hearts, Color.rgb(135,145,106))
        addKey(hearts, "🤎")
        addKey(hearts, "🩶")
        addKey(hearts, "💛")
        root.addView(hearts)
        listOf("qwertyuiop", "asdfghjkl", "zxcvbnm").forEach { chars ->
            val row = LinearLayout(this).apply { gravity = Gravity.CENTER }
            chars.forEach { addKey(row, it.toString()) }
            root.addView(row)
        }
        val bottom = LinearLayout(this).apply { gravity = Gravity.CENTER }
        addKey(bottom, "?123")
        addKey(bottom, "☺")
        addKey(bottom, "Space")
        addKey(bottom, "⌫")
        addKey(bottom, "↵")
        root.addView(bottom)
        return root
    }

    private fun addHeartKey(row: LinearLayout, tint: Int) {
        val key = TextView(this).apply {
            text = "♥"
            textSize = 27f
            gravity = Gravity.CENTER
            setTextColor(tint)
            typeface = Typeface.DEFAULT_BOLD
            setBackgroundColor(0xFF303030.toInt())
            setPadding(4, 13, 4, 13)
            contentDescription = "Champagne olive heart"
            setOnClickListener {
                currentInputConnection?.commitText("🤎", 1)
            }
        }
        row.addView(key, LinearLayout.LayoutParams(0, -2, 1f).apply {
            setMargins(2, 3, 2, 3)
        })
    }

    private fun addKey(row: LinearLayout, label: String) {
        val key = TextView(this).apply {
            text = label
            textSize = if (label == "Space" || label == "?123") 14f else 23f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
            setBackgroundColor(0xFF303030.toInt())
            setPadding(4, 13, 4, 13)
            setOnClickListener { handleKey(label) }
        }
        row.addView(key, LinearLayout.LayoutParams(0, -2, 1f).apply {
            setMargins(2, 3, 2, 3)
        })
    }

    private fun handleKey(label: String) {
        val ic = currentInputConnection ?: return
        when (label) {
            "⌫" -> ic.deleteSurroundingText(1, 0)
            "↵" -> ic.commitText("\n", 1)
            "Space" -> ic.commitText(" ", 1)
            "?123" -> ic.commitText("123 ", 1)
            "☺" -> ic.commitText("☺", 1)
            else -> ic.commitText(label, 1)
        }
    }
}
