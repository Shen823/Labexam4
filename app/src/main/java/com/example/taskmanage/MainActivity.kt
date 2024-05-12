package com.example.taskmanage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 2000
    private lateinit var database: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(
            applicationContext, TaskDatabase::class.java, "Task_Manage"
        ).build()
        GlobalScope.launch {
            TaskDataObject.listdata = database.dao().getTasks() as MutableList<TaskInfo>
        }


        Handler().postDelayed({
            // Start your main activity after the splash screen delay
            val mainIntent = Intent(this, ViewTasks::class.java)
            startActivity(mainIntent)
            finish() // close the splash screen activity
        }, SPLASH_DISPLAY_LENGTH.toLong())

    }
}