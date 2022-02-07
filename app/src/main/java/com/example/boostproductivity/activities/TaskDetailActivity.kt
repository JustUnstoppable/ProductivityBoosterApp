package com.example.boostproductivity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.boostproductivity.R
import com.example.boostproductivity.model.taskModel
lateinit var toolbarTask:Toolbar
//lateinit var datE:TextView
lateinit var Task1Name:TextView
lateinit var Task2Name:TextView
lateinit var Task3Name:TextView
lateinit var Task4Name:TextView
lateinit var Task5Name:TextView

class TaskDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
        toolbarTask=findViewById(R.id.tbTask)
        //datE=findViewById(R.id.Date)
        Task1Name=findViewById(R.id.task1Name)
        Task2Name=findViewById(R.id.task2Name)
        Task3Name=findViewById(R.id.task3Name)
        Task4Name=findViewById(R.id.task4Name)
        Task5Name=findViewById(R.id.task5Name)
        var tasksDetailModel:taskModel?=null/**
         * First, we get the object (taskModel) from intent's Serialized
         * or Parcelable extra. The Serialize is easier to use (requires
         * no boilerplate code), but Parcelable is more efficient in terms
         * of time and memory leaks.
         */

        if(intent.hasExtra(MainActivity.EXTRA_TASK_DETAILS)){
            tasksDetailModel =intent.getParcelableExtra(MainActivity.EXTRA_TASK_DETAILS) as taskModel?
        }
        if(tasksDetailModel!=null){
            setSupportActionBar(toolbarTask)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title =tasksDetailModel.date
            toolbarTask.setNavigationOnClickListener{
                onBackPressed()
            }
            //datE.text=tasksDetailModel.date
            Task1Name.text=tasksDetailModel.task1name
            Task2Name.text=tasksDetailModel.task2name
            Task3Name.text=tasksDetailModel.task3name
            Task4Name.text=tasksDetailModel.task4name
            Task5Name.text=tasksDetailModel.task5name
        }

    }
}