package com.example.taskmanage

import androidx.room.*

@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: TaskEntity)

    @Update
    suspend fun updateTask(entity: TaskEntity)

    @Delete
    suspend fun deleteTask(entity: TaskEntity)

    @Query("Delete from Task_Manage")
    suspend fun deleteAll()

    @Query("Select * from Task_Manage")
    suspend fun getTasks():List<TaskInfo>

}