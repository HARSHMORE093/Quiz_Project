package com.example.quizapplication.app.models

data class Quiz(
    var id:String="",
    var title:String="",
    var question: MutableMap<String,Question> = mutableMapOf()
)