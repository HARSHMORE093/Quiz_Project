package com.example.quizapplication.app.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapplication.R
import com.example.quizapplication.app.adapter.OptionAdapter
import com.example.quizapplication.app.models.Question
import com.example.quizapplication.app.models.Quiz
import com.example.quizapplication.databinding.ActivityQuestionBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class QuestionActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuestionBinding
    var quizzes : MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var index = 1
    var backPressedTime:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_question)
        setUpFirestore()
        setUpEventListener()
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            startActivity(Intent(this,MainActivity::class.java))
            super.onBackPressed()
        } else {
            Toast.makeText(this, "You want to quit the quiz", Toast.LENGTH_LONG).show()
        }
        backPressedTime=System.currentTimeMillis()
    }

    private fun setUpEventListener() {
        binding.btnPrevious.setOnClickListener {
            index--
            bindViews()
        }

        binding.btnNext.setOnClickListener {
            index++
            bindViews()
        }

        binding.btnSubmit.setOnClickListener {
            Log.d("FINALQUIZ", questions.toString())
            val intent = Intent(this, ResultActivity::class.java)
            val json  = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }
    }

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if (date != null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty){
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].question
                        bindViews()
                    }
                }
        }
    }

    private fun bindViews() {
        binding.btnPrevious.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE
        binding.btnNext.visibility = View.GONE

        if(index == 1){ //first question
            binding.btnNext.visibility = View.VISIBLE
        }
        else if(index == questions!!.size) { // last question
            binding.btnSubmit.visibility = View.VISIBLE
            binding.btnPrevious.visibility = View.VISIBLE
        }
        else{ // Middle
            binding.btnPrevious.visibility = View.VISIBLE
            binding.btnNext.visibility = View.VISIBLE
        }

        val question = questions!!["questions$index"]
        question?.let {
            binding.Description.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            binding.optionlist.layoutManager = LinearLayoutManager(this)
            binding.optionlist.adapter = optionAdapter
            binding.optionlist.setHasFixedSize(true)
        }
    }


}