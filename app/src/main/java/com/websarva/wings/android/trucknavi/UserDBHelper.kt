package com.websarva.wings.android.trucknavi

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class UsersDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user: UserModel): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_DATE, user.date)
        values.put(DBContract.UserEntry.COLUMN_COURSE, user.course)
        values.put(DBContract.UserEntry.COLUMN_No, user.No)
        values.put(DBContract.UserEntry.COLUMN_NAME, user.name)
        values.put(DBContract.UserEntry.COLUMN_LATLNG, user.latlng)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME, null, values)

        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteUser(No: String): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase
        // Define 'where' part of query.
        val selection = DBContract.UserEntry.COLUMN_No + " LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(No)
        // Issue SQL statement.
        db.delete(DBContract.UserEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }


//    fun readUser(No: String): ArrayList<UserModel> {
//        val users = ArrayList<UserModel>()
//        val db = writableDatabase
//        var cursor: Cursor? = null
//        try {
//            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME + " WHERE " + DBContract.UserEntry.COLUMN_No + "='" + No + "'", null)
//        } catch (e: SQLiteException) {
//            // if table not yet present, create it
//            db.execSQL(SQL_CREATE_ENTRIES)
//            return ArrayList()
//        }
//
//        var date: String
//        var course: String
//        var name: String
//        var age: String
//        if (cursor!!.moveToFirst()) {
//            while (cursor.isAfterLast == false) {
//                date = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DATE))
//                course = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_COURSE))
//                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
//                age = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_LATLNG))
//
//                users.add(UserModel(date, course,No, name, age))
//                cursor.moveToNext()
//            }
//        }
//        return users
//    }

    fun readAllUsers(): ArrayList<UserModel> {
        val users = ArrayList<UserModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var date: String
        var course: String
        var No: String
        var name: String
        var LatLng: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                date = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DATE))
                course = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_COURSE))
                No = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_No))
                name = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME))
                LatLng = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_LATLNG))

                users.add(UserModel(date, course ,No, name, LatLng))
                cursor.moveToNext()
            }
        }
        return users
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                    DBContract.UserEntry.COLUMN_DATE + " TEXT," +
                    DBContract.UserEntry.COLUMN_COURSE + " TEXT," +
                    DBContract.UserEntry.COLUMN_No + " TEXT PRIMARY KEY," +
                    DBContract.UserEntry.COLUMN_NAME + " TEXT," +
                    DBContract.UserEntry.COLUMN_LATLNG + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME
    }
}
