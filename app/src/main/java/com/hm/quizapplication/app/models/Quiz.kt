package com.hm.quizapplication.app.models

data class Quiz(
    var id:String="",
    var title:String="",
    var name:String="",
    var question: MutableMap<String,Question> = mutableMapOf()
)