package com.example.taskmanage

object TaskDataObject {

    var listdata = mutableListOf<TaskInfo>()

    fun setData(title: String, description:String, priority: String, deadline:String) {
        listdata.add(TaskInfo(title, description, priority, deadline))
    }

    fun getAllData(): List<TaskInfo> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): TaskInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,title: String, description:String, priority: String, deadline:String)
    {
        listdata[pos].title=title
        listdata[pos].description=description
        listdata[pos].priority=priority
        listdata[pos].deadline=deadline
    }
}