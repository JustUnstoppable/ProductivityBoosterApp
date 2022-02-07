package com.example.boostproductivity.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boostproductivity.R
import com.example.boostproductivity.adapters.taskAdapter
import com.example.boostproductivity.database.DatabaseHandler
import com.example.boostproductivity.model.taskModel
import com.example.boostproductivity.utils.SwipeToDeleteCallback
import com.example.boostproductivity.utils.SwipeToEditCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton
lateinit var rvTaskList : RecyclerView
lateinit var tvNoTaskFound : TextView
//hold and ctrl then click on it to go to its source
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fButton : FloatingActionButton = findViewById(R.id.floatButton)
        rvTaskList = findViewById(R.id.rvList)
        tvNoTaskFound = findViewById(R.id.tvList)

        fButton.setOnClickListener {
            val intent = Intent(this, DaysDetails::class.java)
            startActivityForResult(intent,ADDTASKACTIVITYREQUESTCODE)
        }
        getTaskListFromLocalDB()
    }
    private fun setUpTaskRecyclerView(TaskList: ArrayList<taskModel>){
        rvTaskList.layoutManager=LinearLayoutManager(this)
        rvTaskList.setHasFixedSize(true)
        val TaskListAdapter= taskAdapter(this,TaskList)
        rvTaskList.adapter=TaskListAdapter
        TaskListAdapter.setOnClickListener(object: taskAdapter.OnClickListener {
            override fun onClick(position: Int, model: taskModel) {
                val intent = Intent(this@MainActivity,
                    TaskDetailActivity::class.java)
                intent.putExtra(EXTRA_TASK_DETAILS,model)
                startActivity(intent)
            }
              }

        )
        val editSwipeHandler=object: SwipeToEditCallback(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
              val adapter= rvTaskList.adapter as taskAdapter
                adapter.notifyEditItem(this@MainActivity,viewHolder.adapterPosition,
                    ADDTASKACTIVITYREQUESTCODE)
            }
        }
        val editItemTouchHelper=ItemTouchHelper(editSwipeHandler)
        editItemTouchHelper.attachToRecyclerView(rvTaskList)

        val deleteSwipeHandler=object: SwipeToDeleteCallback(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter= rvTaskList.adapter as taskAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                getTaskListFromLocalDB()
            }
        }
        val deleteItemTouchHelper=ItemTouchHelper(deleteSwipeHandler)
        deleteItemTouchHelper.attachToRecyclerView(rvTaskList)

    }
    private fun getTaskListFromLocalDB(){
        val dbHandler= DatabaseHandler(this)
        val getTaskList :ArrayList<taskModel> =dbHandler.getAlltaskList()
        if(getTaskList.size >  0){
            rvTaskList.visibility=View.VISIBLE
            tvNoTaskFound.visibility= View.GONE
            setUpTaskRecyclerView(getTaskList)
        }else{
            rvTaskList.visibility=View.GONE
            tvNoTaskFound.visibility= View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== ADDTASKACTIVITYREQUESTCODE){
            if(resultCode== Activity.RESULT_OK){
                getTaskListFromLocalDB()
            }else{
                Log.e("Activity","Cancelled or back pressed")
            }
        }
    }
    companion object{
        var ADDTASKACTIVITYREQUESTCODE=1
        var EXTRA_TASK_DETAILS = "extra_task_details"
    }
}

