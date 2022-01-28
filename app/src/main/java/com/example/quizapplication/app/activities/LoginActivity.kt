package com.example.quizapplication.app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.quizapplication.R
import com.example.quizapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_login)
        firebaseAuth= FirebaseAuth.getInstance()
        binding.btnlogin.setOnClickListener {
            login()
        }
        binding.btnSignUp.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun login(){
        val email=binding.etEmailAddress.text.toString()
        val password=binding.etPassword.text.toString()
        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this,"Email/password cannot be empty",Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"Sucess",Toast.LENGTH_SHORT).show()
                val intent =Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Authentication Failed",Toast.LENGTH_SHORT).show()
            }
        }

    }
}