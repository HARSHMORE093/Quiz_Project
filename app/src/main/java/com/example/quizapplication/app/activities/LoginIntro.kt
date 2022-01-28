package com.example.quizapplication.app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.quizapplication.R
import com.example.quizapplication.databinding.ActivityLoginIntroBinding
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    lateinit var binding: ActivityLoginIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth= FirebaseAuth.getInstance()
        binding=DataBindingUtil.setContentView(this, R.layout.activity_login_intro)
        binding.btnGetStarted.setOnClickListener {
            redirect("LOGIN")
        }
        if(auth.currentUser != null){//baar login nahi karna.
            Toast.makeText(this,"User is already logged in!",Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

    }
    private fun redirect(name:String){
        val intent = when (name) {
            "LOGIN" -> Intent(this, LoginActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("no path exists")
        }
        startActivity(intent)
        finish()
    }
}