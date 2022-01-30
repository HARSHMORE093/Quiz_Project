package com.example.quizapplication.app.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapplication.R
import com.example.quizapplication.app.adapter.OptionAdapter
import com.example.quizapplication.app.models.Question
import com.example.quizapplication.databinding.ActivityQuestionBinding

class QuestionActivity:AppCompatActivity() {
    lateinit var binding:ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_question)
        bindView()
    }

    private fun bindView() {
        val question=Question(
            "Which is the only bird that can fly backward?",
            "jfsuf",
            "vksv",
            "H,lbermbks",
            "lermgjnerge",
            "lermgjnerge"
        )
        binding.Description.text=question.description
        val optionAdapter=OptionAdapter(this,question)
        binding.optionList.layoutManager=LinearLayoutManager(this)//optionList Recyclerview hai jo ki linear hai
        binding.optionList.adapter=optionAdapter
        binding.optionList.setHasFixedSize(true)
    }
}
