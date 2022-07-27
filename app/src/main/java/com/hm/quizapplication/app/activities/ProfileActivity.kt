package com.hm.quizapplication.app.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.hm.quizapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.hm.quizapplication.databinding.ActivityProfileBinding

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