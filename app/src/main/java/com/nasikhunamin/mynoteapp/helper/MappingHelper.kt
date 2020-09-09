package com.nasikhunamin.mynoteapp.helper

import android.database.Cursor
import com.nasikhunamin.mynoteapp.db.sqlite.DatabaseContract
import com.nasikhunamin.mynoteapp.entitiy.Note

object MappingHelper {
    fun mapCursorToArrayList(noteCursor: Cursor): ArrayList<Note> {
        val notesList = ArrayList<Note>()
        noteCursor?.apply { // apply digunakan untuk menyederhanakan kode yang berulang
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE))
                val description = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE))
                notesList.add(Note(id, title, description, date))
            }
        }
        return notesList
    }
}