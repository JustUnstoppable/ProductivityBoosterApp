package com.example.boostproductivity.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boostproductivity.R
import com.example.boostproductivity.activities.DaysDetails
import com.example.boostproductivity.activities.MainActivity
import com.example.boostproductivity.database.DatabaseHandler
import com.example.boostproductivity.model.taskModel
open class taskAdapter (
    private val context : Context,
    private val list: ArrayList<taskModel>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onClickListener:OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.itemtasklist,
                parent,
                false))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if(holder is TaskViewHolder) {
            holder.RVDate.text = model.date
            holder.TVtask1title.text = model.task1name
            holder.TVtask2title.text = model.task2name
            holder.TVtask3title.text = model.task3name
            holder.TVtask4title.text = model.task4name
            holder.TVtask5title.text = model.task5name
            holder.itemView.setOnClickListener{
                if(onClickListener != null){
                    onClickListener!!.onClick(position, model)
                }
            }
        }
    }
    fun removeAt(position: Int){
        val dbHandler=DatabaseHandler(context)
        val isDeleted=dbHandler.deleteTask(list[position])
        if(isDeleted >0){
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }
    fun notifyEditItem(activity: Activity, position: Int, requestCode:Int){
        val intent=Intent(context,DaysDetails::class.java)
        intent.putExtra(MainActivity.EXTRA_TASK_DETAILS,list[position])
        activity.startActivityForResult(intent,requestCode)
        notifyItemChanged(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var RVDate: TextView = itemView.findViewById(R.id.rvDate)
        var TVtask1title: TextView = itemView.findViewById(R.id.tvtask1title)
        var TVtask2title: TextView = itemView.findViewById(R.id.tvtask2title)
        var TVtask3title: TextView = itemView.findViewById(R.id.tvtask3title)
        var TVtask4title: TextView = itemView.findViewById(R.id.tvtask4title)
        var TVtask5title: TextView = itemView.findViewById(R.id.tvtask5title)
    }
    interface OnClickListener {
        fun onClick(position: Int,model: taskModel)

    }
    fun setOnClickListener(onClickListener : OnClickListener) {
        this.onClickListener = onClickListener
    }

    private class MyViewHolder(view:View) : RecyclerView.ViewHolder(view)
}


