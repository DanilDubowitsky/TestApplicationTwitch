package com.example.testapplication.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context:Context) : SQLiteOpenHelper(context, DataBaseClass.DATABASE_NAME, null, DataBaseClass.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DataBaseClass.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DataBaseClass.SQL_DELETE_TABLE)
        onCreate(db)
    }
}