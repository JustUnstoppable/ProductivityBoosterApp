package com.example.boostproductivity.database
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getBlobOrNull
import androidx.core.database.getIntOrNull
import com.example.boostproductivity.model.taskModel


class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1    // this DB version
        private val DATABASE_NAME = "BoostProductivityDatabase"
        private val TABLE_PRODUCTIVITY = "BoostProductivityTable"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_TASK1 = "task1name"
        private const val COLUMN_DESCRIPTION1 = "task1description"
        private const val COLUMN_TIME1 = "task1time"
        private const val COLUMN_CHECKBOX1="checkbox1"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_TASK2 = "task2name"
        private const val COLUMN_DESCRIPTION2 = "task2description"
        private const val COLUMN_TIME2 = "task2time"
        private const val COLUMN_CHECKBOX2="checkbox2"
        private const val COLUMN_TASK3 = "task3name"
        private const val COLUMN_DESCRIPTION3 = "task3description"
        private const val COLUMN_TIME3 = "task3time"
        private const val COLUMN_CHECKBOX3="checkbox3"
        private const val COLUMN_TASK4 = "task4name"
        private const val COLUMN_DESCRIPTION4 = "task4description"
        private const val COLUMN_TIME4 = "task4time"
        private const val COLUMN_CHECKBOX4="checkbox4"
        private const val COLUMN_TASK5 = "task5name"
        private const val COLUMN_DESCRIPTION5 = "task5description"
        private const val COLUMN_TIME5 = "task5time"
        private const val COLUMN_CHECKBOX5="checkbox5"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        // a String value containing SQL instruction

            val CREATE_PRODUCTIVITY_TABLE = ("CREATE TABLE $TABLE_PRODUCTIVITY(" +
                    "$COLUMN_ID INTEGER PRIMARY KEY," +
                    "$COLUMN_DATE TEXT," +
                    "$COLUMN_CHECKBOX1 BOOL," +
                    "$COLUMN_TASK1 TEXT," +
                    "$COLUMN_DESCRIPTION1 TEXT," +
                    "$COLUMN_TIME1 TEXT," +
                    "$COLUMN_CHECKBOX2 BOOL," +
                    "$COLUMN_TASK2 TEXT," +
                    "$COLUMN_DESCRIPTION2 TEXT," +
                    "$COLUMN_TIME2 TEXT," +
                    "$COLUMN_CHECKBOX3 BOOL," +
                    "$COLUMN_TASK3 TEXT," +
                    "$COLUMN_DESCRIPTION3 TEXT," +
                    "$COLUMN_TIME3 TEXT," +
                    "$COLUMN_CHECKBOX4 BOOL," +
                    "$COLUMN_TASK4 TEXT," +
                    "$COLUMN_DESCRIPTION4 TEXT," +
                    "$COLUMN_TIME4 TEXT," +
                    "$COLUMN_CHECKBOX5 BOOL," +
                    "$COLUMN_TASK5 TEXT," +
                    "$COLUMN_DESCRIPTION5 TEXT," +
                    "$COLUMN_TIME5 TEXT)")
            db?.execSQL(CREATE_PRODUCTIVITY_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTIVITY")
        onCreate(db)
    }
    fun addProductivity(task: taskModel) : Long{

        val values = ContentValues()
        values.put(COLUMN_DATE, task.date)
        values.put(COLUMN_CHECKBOX1,task.checkBox1)
        values.put(COLUMN_TASK1, task.task1name)
        values.put(COLUMN_DESCRIPTION1, task.task1description)
        values.put(COLUMN_TIME1, task.task1time)
        values.put(COLUMN_CHECKBOX2,task.checkBox2)
        values.put(COLUMN_TASK2, task.task2name)
        values.put(COLUMN_DESCRIPTION2, task.task2description)
        values.put(COLUMN_TIME2, task.task2time)
        values.put(COLUMN_CHECKBOX3,task.checkBox3)
        values.put(COLUMN_TASK3, task.task3name)
        values.put(COLUMN_DESCRIPTION3, task.task3description)
        values.put(COLUMN_TIME3, task.task3time)
        values.put(COLUMN_CHECKBOX4,task.checkBox4)
        values.put(COLUMN_TASK4, task.task4name)
        values.put(COLUMN_DESCRIPTION4, task.task4description)
        values.put(COLUMN_TIME4, task.task4time)
        values.put(COLUMN_CHECKBOX5,task.checkBox5)
        values.put(COLUMN_TASK5, task.task5name)
        values.put(COLUMN_DESCRIPTION5, task.task5description)
        values.put(COLUMN_TIME5, task.task5time)
        val db = this.writableDatabase
        //Inserting a row
        val result = db.insert(TABLE_PRODUCTIVITY,null, values)
        //closing db
        db.close()

        return result
    }
    fun updateProductivity(task: taskModel) : Int{

        val values = ContentValues()
        values.put(COLUMN_DATE, task.date)
        values.put(COLUMN_CHECKBOX1,task.checkBox1)
        values.put(COLUMN_TASK1, task.task1name)
        values.put(COLUMN_DESCRIPTION1, task.task1description)
        values.put(COLUMN_TIME1, task.task1time)
        values.put(COLUMN_CHECKBOX2,task.checkBox2)
        values.put(COLUMN_TASK2, task.task2name)
        values.put(COLUMN_DESCRIPTION2, task.task2description)
        values.put(COLUMN_TIME2, task.task2time)
        values.put(COLUMN_CHECKBOX3,task.checkBox3)
        values.put(COLUMN_TASK3, task.task3name)
        values.put(COLUMN_DESCRIPTION3, task.task3description)
        values.put(COLUMN_TIME3, task.task3time)
        values.put(COLUMN_CHECKBOX4,task.checkBox4)
        values.put(COLUMN_TASK4, task.task4name)
        values.put(COLUMN_DESCRIPTION4, task.task4description)
        values.put(COLUMN_TIME4, task.task4time)
        values.put(COLUMN_CHECKBOX5,task.checkBox5)
        values.put(COLUMN_TASK5, task.task5name)
        values.put(COLUMN_DESCRIPTION5, task.task5description)
        values.put(COLUMN_TIME5, task.task5time)
        val db = this.writableDatabase
        //Inserting a row
        val success=db.update(TABLE_PRODUCTIVITY,values, COLUMN_ID +"=" + task.id, null)

        db.close()

        return success
    }
    fun deleteTask(task : taskModel) : Int{
        val db=this.writableDatabase
        val success =db.delete(TABLE_PRODUCTIVITY, COLUMN_ID+ "=" + task.id, null)
        db.close()
        return success
    }
    fun getAlltaskList() : ArrayList<taskModel> {
        val taskList=ArrayList<taskModel>()
        val selectQuery="SELECT * FROM $TABLE_PRODUCTIVITY"
        val db=this.readableDatabase
        try{
          val cursor :Cursor =db.rawQuery(selectQuery,null)
          if(cursor.moveToFirst()){
              do {
                 val task=taskModel(
                     cursor.getInt((cursor.getColumnIndex(COLUMN_ID))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_DATE))),
                     cursor.isNull((cursor.getColumnIndex(COLUMN_CHECKBOX1))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TASK1))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_DESCRIPTION1))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TIME1))),
                     cursor.isNull((cursor.getColumnIndex(COLUMN_CHECKBOX2))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TASK2))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_DESCRIPTION2))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TIME2))),
                     cursor.isNull((cursor.getColumnIndex(COLUMN_CHECKBOX3))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TASK3))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_DESCRIPTION3))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TIME3))),
                     cursor.isNull((cursor.getColumnIndex(COLUMN_CHECKBOX4))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TASK4))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_DESCRIPTION4))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TIME4))),
                     cursor.isNull((cursor.getColumnIndex(COLUMN_CHECKBOX5))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TASK5))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_DESCRIPTION5))),
                     cursor.getString((cursor.getColumnIndex(COLUMN_TIME5)))
                 )
                  taskList.add(task)
              }while(cursor.moveToNext())
          }
            cursor.close()
        }catch(e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        return taskList
    }

}