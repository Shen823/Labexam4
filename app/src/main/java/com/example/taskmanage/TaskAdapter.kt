package com.example.taskmanage

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(var data: List<TaskInfo>) : RecyclerView.Adapter<TaskAdapter.viewHolder>() {
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.titleL)
        var description: TextView = itemView.findViewById(R.id.descriptionL)
        var priority: TextView = itemView.findViewById(R.id.priorityL)
        var dedline: TextView = itemView.findViewById(R.id.deadlineL)
        var layout: View = itemView.findViewById(R.id.viewColor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        when (data[position].priority.toLowerCase()) {
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F8C9C9"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#FFCF5A"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#56FFAA"))
        }

        holder.title.text = data[position].title
        holder.description.text = data[position].description
        holder.priority.text = data[position].priority
        holder.dedline.text = data[position].deadline
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateTaskDetails::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}