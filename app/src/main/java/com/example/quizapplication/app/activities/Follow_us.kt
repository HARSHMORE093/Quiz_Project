package com.example.quizapplication.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.quizapplication.R
import com.example.quizapplication.databinding.ActivityFollowUsBinding
import com.example.quizapplication.databinding.ActivityProfileBinding
import android.content.Intent
import android.net.Uri


class Follow_us : AppCompatActivity() {
    lateinit var binding: ActivityFollowUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_follow_us)
        binding.fb.setOnClickListener{
            val YourPageURL = "https://www.facebook.com/harsh.more.56211"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(YourPageURL))
            startActivity(browserIntent)
        }
    }
}