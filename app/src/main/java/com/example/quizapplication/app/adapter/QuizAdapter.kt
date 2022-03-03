package com.example.quizapplication.app.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapplication.R
import com.example.quizapplication.app.activities.QuestionActivity
import com.example.quizapplication.app.models.Quiz
import com.example.quizapplication.app.utils.colorPicker
import com.example.quizapplication.app.utils.iconPicker

class QuizAdapter(val context:Context,val quizzes:List<Quiz>):RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.quiz_items,parent,false)//layout inflater quiz_items ki xml file ko view me convert kar dega.
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text=quizzes[position].title
        holder.nameView.text=quizzes[position].name
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(colorPicker.getColor()))
        holder.iconView.setImageResource(iconPicker.getIcon())
        holder.itemView.setOnClickListener{//itemview par click karenge tho tino par click hoga : iconView,cardContanier,textViewTitle
            val intent=Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizzes[position].title)
            context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int {
        return quizzes.size
    }
    inner class QuizViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var textViewTitle:TextView=itemView.findViewById(R.id.quizTitle)
        var iconView:ImageView=itemView.findViewById(R.id.quizIcon)
        var nameView:TextView=itemView.findViewById(R.id.quizName)
        var cardContainer:CardView=itemView.findViewById(R.id.cardContainer)
    }
}