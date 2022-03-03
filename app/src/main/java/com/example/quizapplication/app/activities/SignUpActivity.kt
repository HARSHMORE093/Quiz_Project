package com.example.quizapplication.app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.quizapplication.R
import com.example.quizapplication.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity :AppCompatActivity(){
    lateinit var binding: ActivitySignupBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth= FirebaseAuth.getInstance()

        binding=DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.signup.setOnClickListener {
            signUPUser()
        }
        binding.btnlog.setOnClickListener {
            val intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun signUPUser(){
        val email=binding.etEmailsignup.text.toString()
        val password=binding.etPasswordsignup.text.toString()
        val confirmPassword=binding.etConfirmPassword.text.toString()
        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this,"Email/password cannot be empty",Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmPassword){
            Toast.makeText(this,"Password and Confirm password does not match",Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                val intent =Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this,"Error creating user",Toast.LENGTH_SHORT).show()
            }
        }

    }
}