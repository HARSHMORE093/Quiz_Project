package com.example.quizapplication.app.utils

import com.example.quizapplication.R

object iconPicker {
    val icons= arrayOf(
        R.drawable.icon1,
        R.drawable.icon3,
        R.drawable.icon4,
        R.drawable.icon5,
        R.drawable.icon6,
        R.drawable.icon7,
        R.drawable.icon9,
        R.drawable.icon10
    )

    var currentColorIndex = 0
    fun getIcon(): Int {
        currentColorIndex = (currentColorIndex + 1) % colorPicker.colors.size
        return icons[currentColorIndex]
    }
}