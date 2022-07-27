package com.hm.quizapplication.app.utils

object colorPicker {
    var colors = arrayOf(
        "#FFE4E1", "#FFC0CB",
        "#FF8C00", "#FF4500", "#EEE8AA", "#ADD8E6", "#00FFFF", "#1E90FF"
    )
    var currentColorIndex = 0
    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}