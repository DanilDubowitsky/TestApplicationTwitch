package com.example.testapplication.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.testapplication.Game
import com.example.testapplication.GameClass
import com.example.testapplication.LogoClass

class DbManager(val context: Context) {
    val gameDbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = gameDbHelper.writableDatabase
    }
    fun destroyDb(){
        db?.delete(DataBaseClass.TABLE_NAME,null,null)
    }
    fun insertToDb(title:String,viewers:Int, channels:Int, image:String){
        val values = ContentValues().apply {
            put(DataBaseClass.COLUMN_NAME_TITLE, title)
            put(DataBaseClass.COLUMN_NAME_COUNT_CHANNELS, channels)
            put(DataBaseClass.COLUMN_NAME_COUNT_VIEWERS, viewers)
            put(DataBaseClass.COLUMN_NAME_IMAGE, image)
        }
        db?.insert(DataBaseClass.TABLE_NAME,null,values)
    }
    fun closeDb(){
        gameDbHelper.close()
    }
    fun readDbData() : ArrayList<Game>{
        val dataList = ArrayList<Game>()
        val cursor = db?.query(DataBaseClass.TABLE_NAME,null,null,null,null,null,null)
        var gameObj: Game = Game()
            while (cursor?.moveToNext()!!){
                var dataText = cursor?.getString(cursor.getColumnIndex(DataBaseClass.COLUMN_NAME_TITLE))
                gameObj?.game?.name = dataText.toString()
                dataText = cursor?.getInt(cursor.getColumnIndex(DataBaseClass.COLUMN_NAME_COUNT_VIEWERS)).toString()
                gameObj?.viewers = dataText.toInt()
                dataText = cursor?.getInt(cursor.getColumnIndex(DataBaseClass.COLUMN_NAME_COUNT_CHANNELS)).toString()
                gameObj?.channels = dataText.toInt()
                dataText = cursor?.getString(cursor.getColumnIndex(DataBaseClass.COLUMN_NAME_IMAGE))
                gameObj?.game?.box?.large = dataText.toString()
                dataList.add(gameObj)
                gameObj = Game()
            }

        return dataList
    }
}