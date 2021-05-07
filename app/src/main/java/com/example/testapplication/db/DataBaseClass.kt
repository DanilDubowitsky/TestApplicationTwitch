package com.example.testapplication.db

import android.provider.BaseColumns

object DataBaseClass:BaseColumns {
    const val TABLE_NAME = "games"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_COUNT_VIEWERS = "viewers"
    const val COLUMN_NAME_COUNT_CHANNELS = "channels"
    const val COLUMN_NAME_IMAGE = "image"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "games_data_base.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_TITLE TEXT, $COLUMN_NAME_COUNT_CHANNELS INTEGER, " +
            "$COLUMN_NAME_COUNT_VIEWERS INTEGER, $COLUMN_NAME_IMAGE TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    const val SQL_DELETE_FROM_TABLE = "DELETE FROM $TABLE_NAME"

}