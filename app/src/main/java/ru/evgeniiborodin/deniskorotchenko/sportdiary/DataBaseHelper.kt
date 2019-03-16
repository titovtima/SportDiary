package ru.evgeniiborodin.deniskorotchenko.sportdiary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper internal constructor(context:Context):SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {

    override fun onCreate(db:SQLiteDatabase) {
        db.execSQL("CREATE TABLE local_exercises (" +
            "  exerciseID int NOT NULL," +
            "  exerciseName text NOT NULL," +
            "  number int NOT NULL," +
            "  date int NOT NULL" +
            ")"
        )
    }

    override fun onUpgrade(db:SQLiteDatabase, oldVersion:Int, newVersion:Int) {
        db.execSQL("DROP TABLE `localproductlist`")
        onCreate(db)
    }

    companion object {
        var columnNames = arrayOf("exerciseID", "exerciseName", "number", "date")
        private val DATABASE_NAME = "block.db"
        val TABLE_NAME = "local_exercises"
        private val DATABASE_VERSION = 1
    }
}