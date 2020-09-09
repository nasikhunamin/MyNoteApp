package com.nasikhunamin.mynoteapp.db.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.nasikhunamin.mynoteapp.db.sqlite.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import com.nasikhunamin.mynoteapp.db.sqlite.DatabaseContract.NoteColumns.Companion._ID
import java.sql.SQLException

// tugas kelas ini adalah melakukan manipulasi data
class NoteHelper(context: Context){
    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: NoteHelper? = null
        private lateinit var database: SQLiteDatabase

        //singleton notehelper : agar objek hanya memiliki sebuah instance
        fun getInstance(context: Context) : NoteHelper =
            INSTANCE ?: synchronized(this) { // synchronized dipakai untuk menghindari duplikasi instance di semua thread
                INSTANCE
                    ?: NoteHelper(context)
            }
    }

    init {
        databaseHelper =
            DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close(){
        databaseHelper.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    fun insert(values : ContentValues?): Long{
        return database.insert(
            DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues) : Int{
        return database.update(
            DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    fun deleteByID(id: String): Int{
        return database.delete(
            DATABASE_TABLE, "$_ID = '$id'", null)
    }
}