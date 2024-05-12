package com.example.taskmanage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateTaskDetails : AppCompatActivity() {

    private lateinit var updatetitle: EditText
    private lateinit var updatedescription: EditText
    private lateinit var updatepriority: EditText
    private lateinit var updatedeadline: EditText
    private lateinit var deleteData: Button
    private lateinit var updateData: Button
    private lateinit var database: TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task_details)

        database = Room.databaseBuilder(
            applicationContext, TaskDatabase::class.java, "Task_Manage"
        ).build()

        updatetitle = findViewById(R.id.updateTitle)
        updatedescription = findViewById(R.id.updateDescription)
        updatepriority = findViewById(R.id.updatePriority)
        updatedeadline = findViewById(R.id.updateDeadline)
        deleteData = findViewById(R.id.delete)
        updateData = findViewById(R.id.update)

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = TaskDataObject.getData(pos).title
            val description = TaskDataObject.getData(pos).description
            val priority = TaskDataObject.getData(pos).priority
            val deadline = TaskDataObject.getData(pos).deadline

            updatetitle.setText(title)
            updatedescription.setText(description)
            updatepriority.setText(priority)
            updatedeadline.setText(deadline)

            deleteData.setOnClickListener {
                TaskDataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        TaskEntity(
                            pos + 1,
                            updatetitle.text.toString(),
                            updatedescription.text.toString(),
                            updatepriority.text.toString(),
                            updatedeadline.text.toString()
                        )
                    )
                    // Refresh RecyclerView after deletion
                    refreshRecyclerView()
                }
                Toast.makeText(this@UpdateTaskDetails, "Data Deleted Successfully", Toast.LENGTH_SHORT).show()
                myIntent()
            }

            updateData.setOnClickListener {
                TaskDataObject.updateData(
                    pos,
                    updatetitle.text.toString(),
                    updatedescription.text.toString(),
                    updatepriority.text.toString(),
                    updatedeadline.text.toString()
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        TaskEntity(
                            pos + 1,
                            updatetitle.text.toString(),
                            updatedescription.text.toString(),
                            updatepriority.text.toString(),
                            updatedeadline.text.toString()
                        )
                    )
                    // Refresh RecyclerView after update
                    refreshRecyclerView()
                }
                Toast.makeText(this@UpdateTaskDetails, "Data Updated Successfully", Toast.LENGTH_SHORT).show()
                myIntent()
            }

        }
    }

    fun myIntent() {
        val intent = Intent(this, ViewTasks::class.java)
        startActivity(intent)
    }

    private fun refreshRecyclerView() {
        if (isFinishing) return
        val viewTasksIntent = Intent(this, ViewTasks::class.java)
        startActivity(viewTasksIntent)
        finish()
    }
}
