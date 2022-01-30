package com.example.quizapplication.app.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizapplication.R
import com.example.quizapplication.app.adapter.QuizAdapter
import com.example.quizapplication.app.models.Quiz
import com.example.quizapplication.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: QuizAdapter
    private var quizList= mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle//on off funtionality of drawer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpView()
        populateDummyData()
    }

    private fun populateDummyData() {
        quizList.add(Quiz("12-10-2020","12-10-2020"))
        quizList.add(Quiz("17-10-2020","18-10-2020"))
        quizList.add(Quiz("16-10-2020","19-10-2020"))
        quizList.add(Quiz("15-10-2020","22-10-2020"))
        quizList.add(Quiz("14-10-2020","32-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
        quizList.add(Quiz("13-10-2020","42-10-2020"))
    }

    fun setUpView(){
        setUpDrawerLayout()
        setUpRecyclerView()
        setupFireStore()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupFireStore() {
        firestore= FirebaseFirestore.getInstance()
        val collectionReference=firestore.collection("quizzes")
        collectionReference.addSnapshotListener{value,error ->
            if(value == null || error!=null){
                Toast.makeText(this,"Error Fecthing data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }//addSnapshotlistener check karega ki data change tho nahi ho raha ,Agar change hoga data mai, tho update kardega
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())//json to object banayenge
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerView() {
        adapter= QuizAdapter(this,quizList)
        binding.quizRecyclerView.layoutManager=GridLayoutManager(this,2)
        binding.quizRecyclerView.adapter=adapter
    }

    fun setUpDrawerLayout(){
        setSupportActionBar(binding.appBar)
        actionBarDrawerToggle= ActionBarDrawerToggle(this,binding.mainDrawer, R.string.quiz, R.string.quiz)
        actionBarDrawerToggle.syncState()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {//3 dot menu
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)
        return true
    }
}