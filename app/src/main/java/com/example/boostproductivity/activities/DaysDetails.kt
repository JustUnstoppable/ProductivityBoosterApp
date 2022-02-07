package com.example.boostproductivity.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.boostproductivity.R
import com.example.boostproductivity.database.DatabaseHandler
import com.example.boostproductivity.model.taskModel
import java.text.SimpleDateFormat
import java.util.*


lateinit var tbDetail:Toolbar
lateinit var dateDetail:EditText
lateinit var btnSave : Button
lateinit var task1Title:EditText
lateinit var task1Description:EditText
lateinit var task1Time:EditText
lateinit var checkbox1: CheckBox
lateinit var task2Title:EditText
lateinit var task2Description:EditText
lateinit var task2Time:EditText
lateinit var checkbox2: CheckBox
lateinit var task3Title:EditText
lateinit var task3Description:EditText
lateinit var task3Time:EditText
lateinit var checkbox3: CheckBox
lateinit var task4Title:EditText
lateinit var task4Description:EditText
lateinit var task4Time:EditText
lateinit var checkbox4: CheckBox
lateinit var task5Title:EditText
lateinit var task5Description:EditText
lateinit var task5Time:EditText
lateinit var checkbox5: CheckBox

class DaysDetails : AppCompatActivity(), View.OnClickListener {
    private var cal=Calendar.getInstance()
    private var mTaskDetails : taskModel? = null
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_days_details)
        dateDetail=findViewById(R.id.dateDetails)
        dateDetail.setOnClickListener(this)
        tbDetail = findViewById(R.id.tbDetails)
        setSupportActionBar(tbDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnSave = findViewById(R.id.btnSave)
        btnSave.setOnClickListener(this)
        task1Title=findViewById(R.id.Task1title)
        task1Time=findViewById(R.id.Task1time)
        task1Description=findViewById(R.id.Task1description)
        checkbox1=findViewById(R.id.checkBox)
        task2Title=findViewById(R.id.Task2title)
        task2Time=findViewById(R.id.Task2time)
        task2Description=findViewById(R.id.Task2description)
        checkbox2=findViewById(R.id.checkBox2)
        task3Title=findViewById(R.id.Task3title)
        task3Time=findViewById(R.id.Task3time)
        task3Description=findViewById(R.id.Task3description)
        checkbox3=findViewById(R.id.checkBox3)
        task4Title=findViewById(R.id.Task4title)
        task4Time=findViewById(R.id.Task4time)
        task4Description=findViewById(R.id.Task4description)
        checkbox4=findViewById(R.id.checkBox4)
        task5Title=findViewById(R.id.Task5title)
        task5Time=findViewById(R.id.Task5time)
        task5Description=findViewById(R.id.Task5description)
        checkbox5=findViewById(R.id.checkBox5)
        /*val checkBox1=mTaskDetails!!.checkBox1
        val checkBox2=mTaskDetails!!.checkBox2
        val checkBox3=mTaskDetails!!.checkBox3
        val checkBox4=mTaskDetails!!.checkBox4
        val checkBox5=mTaskDetails!!.checkBox5*/
        tbDetail.setNavigationOnClickListener {
            onBackPressed()
        }
        if(intent.hasExtra(MainActivity.EXTRA_TASK_DETAILS)){
            mTaskDetails=intent.getParcelableExtra(MainActivity.EXTRA_TASK_DETAILS) as taskModel?
        }
        dateSetListener=DatePickerDialog.OnDateSetListener{view ,year,month, dayOfMonth->
            cal.set(Calendar.YEAR,year)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateInView()
        }
        updateInView()
        if(mTaskDetails != null){
            supportActionBar?.title="Edit today's task"
            dateDetail.setText(mTaskDetails!!.date)
            task1Title.setText(mTaskDetails!!.task1name)
            task1Description.setText(mTaskDetails!!.task1description)
            task1Time.setText(mTaskDetails!!.task1time)
            if(mTaskDetails?.checkBox1==false)    checkbox1.setChecked(true)
            task2Title.setText(mTaskDetails!!.task2name)
            task2Description.setText(mTaskDetails!!.task2description)
            task2Time.setText(mTaskDetails!!.task2time)
            if(mTaskDetails?.checkBox2 == false) checkbox2.setChecked(true)
            task3Title.setText(mTaskDetails!!.task3name)
            task3Description.setText(mTaskDetails!!.task3description)
            task3Time.setText(mTaskDetails!!.task3time)
            if(mTaskDetails?.checkBox3 == false) checkbox3.setChecked(true)
            task4Title.setText(mTaskDetails!!.task4name)
            task4Description.setText(mTaskDetails!!.task4description)
            task4Time.setText(mTaskDetails!!.task4time)
            //if(checkBox4) checkbox4.setChecked(true)
            task5Title.setText(mTaskDetails!!.task5name)
            task5Description.setText(mTaskDetails!!.task5description)
            task5Time.setText(mTaskDetails!!.task5time)
           // checkbox5.setChecked(mTaskDetails!!.checkBox5)
            btnSave.text="UPDATE"
        }

    }

  

    private fun updateInView(){
        val myFormat="dd.MM.yyyy"
        val sdf=SimpleDateFormat(myFormat,Locale.getDefault())
        dateDetail.setText(sdf.format(cal.time))
    }
    override fun onClick(v: View?){
        when(v?.id){
            R.id.dateDetails->{
                DatePickerDialog(
                    this,dateSetListener,
                    cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()

            }
            R.id.btnSave->when{
                task1Title.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task1 title", Toast.LENGTH_SHORT)
                        .show()
                }
                task1Description.text.isNullOrEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter the task1 description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                task1Time.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task1 time", Toast.LENGTH_SHORT)
                        .show()
                }
                task2Title.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task2 title", Toast.LENGTH_SHORT)
                        .show()
                }
                task2Description.text.isNullOrEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter the task2 description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                task2Time.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task2 time", Toast.LENGTH_SHORT)
                        .show()
                }
                task3Title.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task3 title", Toast.LENGTH_SHORT)
                        .show()
                }
                task3Description.text.isNullOrEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter the task3 description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                task3Time.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task3 time", Toast.LENGTH_SHORT)
                        .show()
                }
                task4Title.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task4 title", Toast.LENGTH_SHORT)
                        .show()
                }
                task4Description.text.isNullOrEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter the task4 description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                task4Time.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task4 time", Toast.LENGTH_SHORT)
                        .show()
                }
                task5Title.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task5 title", Toast.LENGTH_SHORT)
                        .show()
                }
                task5Description.text.isNullOrEmpty() -> {
                    Toast.makeText(
                        this,
                        "Please enter the task5 description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                task5Time.text.isNullOrEmpty() -> {
                    Toast.makeText(this, "Please enter the task5 time", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    val dbHandler = DatabaseHandler(this)
                    val taskmodel = taskModel(
                        //id = if (mHappyPlaceDetails == null) 0 else mHappyPlaceDetails!!.id,
                        id = if(mTaskDetails==null) 0 else mTaskDetails!!.id,
                        date = dateDetail.text.toString(),
                        checkBox1 = checkbox1.isChecked(),
                        task1name = task1Title.text.toString(),
                        task1description = task1Description.text.toString(),
                        task1time = task1Time.text.toString(),
                        checkBox2 =checkbox2.isChecked(),
                        task2name = task2Title.text.toString(),
                        task2description = task2Description.text.toString(),
                        task2time = task2Time.text.toString(),
                        checkBox3 =checkbox3.isChecked(),
                        task3name = task3Title.text.toString(),
                        task3description = task3Description.text.toString(),
                        task3time = task3Time.text.toString(),
                        checkBox4 =checkbox4.isChecked(),
                        task4name = task4Title.text.toString(),
                        task4description = task4Description.text.toString(),
                        task4time = task4Time.text.toString(),
                        checkBox5 =checkbox5.isChecked(),
                        task5name = task5Title.text.toString(),
                        task5description = task5Description.text.toString(),
                        task5time = task5Time.text.toString()
                    )
                  val Taskresult=
                      if(mTaskDetails==null) {
                         dbHandler.addProductivity(taskmodel) }
                     else{
                      dbHandler.updateProductivity(taskmodel).toLong()
                     }
                  if(Taskresult > 0){
                      setResult(Activity.RESULT_OK)
                      finish()
                  }
                }
            }
        }
    }
}