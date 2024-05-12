package com.example.taskmanage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ViewTasks : AppCompatActivity() {

    private lateinit var database: TaskDatabase
    lateinit var AddTaskBtn: Button
    lateinit var DeleteAllBtn: Button
    lateinit var RecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)

        database = Room.databaseBuilder(
            applicationContext, TaskDatabase::class.java, "Task_Manage"
        ).build()

        AddTaskBtn = findViewById(R.id.addTasks);
        DeleteAllBtn = findViewById(R.id.deleteAll);
        RecyclerView = findViewById(R.id.recyclerView);

        AddTaskBtn.setOnClickListener {
            val intent = Intent(this, AddNewTask::class.java)
            startActivity(intent)
        }

        DeleteAllBtn.setOnClickListener {
            TaskDataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }

        setRecycler()

    }

    fun setRecycler() {
        RecyclerView.adapter = TaskAdapter(TaskDataObject.getAllData())
        RecyclerView.layoutManager = LinearLayoutManager(this)
    }
}