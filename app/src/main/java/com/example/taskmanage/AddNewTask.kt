package com.example.taskmanage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddNewTask : AppCompatActivity() {

    lateinit var title: EditText
    lateinit var description: EditText
    lateinit var priority: EditText
    lateinit var deadline: EditText
    lateinit var saveData: Button
    private lateinit var database: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_task)

        title = findViewById(R.id.addTitle);
        description = findViewById(R.id.addDescription);
        priority = findViewById(R.id.addPriority);
        deadline = findViewById(R.id.addDeadline);
        saveData = findViewById(R.id.savedata);

        database = Room.databaseBuilder(
            applicationContext, TaskDatabase::class.java, "Task_Manage"
        ).build()

        saveData.setOnClickListener {
            if (title.text.toString().trim { it <= ' ' }.isNotEmpty()
                && description.text.toString().trim { it <= ' ' }.isNotEmpty()
                && priority.text.toString().trim { it <= ' ' }.isNotEmpty()
                && deadline.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                var create_title = title.getText().toString()
                var create_description = description.getText().toString()
                var create_priority = priority.getText().toString()
                var create_deadline = deadline.getText().toString()
                TaskDataObject.setData(create_title, create_description, create_priority, create_deadline)
                GlobalScope.launch {
                    database.dao().insertTask(TaskEntity(0, create_title, create_description, create_priority, create_deadline))

                }

                val intent = Intent(this, ViewTasks::class.java)
                startActivity(intent)
            }
        }
    }
}
