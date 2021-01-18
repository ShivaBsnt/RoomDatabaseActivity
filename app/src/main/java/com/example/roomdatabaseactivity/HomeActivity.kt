package com.example.roomdatabaseactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseactivity.adapter.StudentAdapter
import com.example.roomdatabaseactivity.model.Student
import com.example.roomdatabaseactivity.parcel.StudentData

private lateinit var recyclerView: RecyclerView
private  var  lstStudent = ArrayList<Student>()


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        recyclerView = findViewById(R.id.recyclerView)
        if(StudentData.studentList.size == 0)
        {
            StudentData.studentData()
        }
        lstStudent = StudentData.studentList
        val adapter = StudentAdapter(lstStudent,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}