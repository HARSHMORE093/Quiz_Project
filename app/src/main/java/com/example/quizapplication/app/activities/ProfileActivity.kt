package com.example.quizapplication.app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.quizapplication.R
import com.example.quizapplication.databinding.ActivityProfileBinding
import com.example.quizapplication.databinding.ActivityResultBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(
            this,R.layout.activity_profile
        )
        firebaseAuth= FirebaseAuth.getInstance()

        binding.useremail.text= firebaseAuth.currentUser?.email
        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}