package com.example.quizapplication.app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapplication.R
import com.example.quizapplication.app.models.Question

class OptionAdapter(val context:Context,val question: Question):RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {
    private var options:List<String> = listOf(question.option1,question.option2,question.option3,question.option4)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return OptionViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text=options[position]
        holder.itemView.setOnClickListener{
            question.userAnswer=options[position]//question ke ander jo useranswer, tha vo equal hai jo option select karenge
            notifyDataSetChanged()
        }
        if(question.userAnswer==options[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selector)
        }else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }
    inner class OptionViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var optionView: TextView =itemView.findViewById(R.id.quizOption)
    }
}