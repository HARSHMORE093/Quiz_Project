package com.example.quizapplication.app.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.quizapplication.R
import com.example.quizapplication.app.models.Quiz
import com.example.quizapplication.databinding.ActivityResultBinding
import com.google.gson.Gson
import com.google.gson.annotations.Expose

class ResultActivity : AppCompatActivity() {
    private lateinit var quiz: Quiz
    lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_result)
        setUpView()
        actiExit()
    }

    private fun actiExit() {
        binding.btnexit.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finishAffinity()
        }
    }

    override fun onBackPressed() {
        startActivity(
            Intent(this, MainActivity::class.java))
            finishAffinity()
        super.onBackPressed()
    }
    private fun setUpView() {
        val quizdata=intent.getStringExtra("QUIZ")
        quiz= Gson().fromJson(quizdata,Quiz::class.java)//Deserilize Kiya
        calculateScore()
        setAnswerrView()
    }

    private fun setAnswerrView() {
        val builder=StringBuilder(" ")
        for (entry in quiz.question.entries){
            val questions=entry.value
            builder.append("<font color'#18206F'><b>Question:${questions.description}" +
                    "</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${questions.answer}</font><br/><br/>")

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            binding.txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }


    @SuppressLint("SetTextI18n", "ResourceType")
    private fun calculateScore() {
        val progress=binding.circularBar
        var score=0
        for (entry in quiz.question.entries){
            val questions=entry.value
            if(questions.answer==questions.userAnswer){
                score += 10
                progress.progress = score
                progress.max=100
            }

        }
        binding.txtScore.text="Your Score: $score/100"
        binding.btnshare.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Your Score in Quiz is: $score/100")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }

    }
}