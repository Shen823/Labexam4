package com.example.taskmanage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task_Manage")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var description:String,
    var priority:String,
    var deadline:String
)