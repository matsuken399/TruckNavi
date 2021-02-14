package com.websarva.wings.android.trucknavi

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception
import java.util.ArrayList

class DBHandler (context: Context, name : String?, factory : SQLiteDatabase.CursorFactory?, version : Int) :
        SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CUSTOMERS_TABLE: String = ("CREATE TABLE $CUSTOMERS_TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_DATE TEXT," +
                "$COLUMN_COURSE TEXT," +
                "$COLUMN_NO TEXT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_Latlng DOUBLE DEFAULT 0)")
        db?.execSQL(CREATE_CUSTOMERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun getCustomers(mCtx: Context):ArrayList<Customer>{
        val qry = "Select * From $CUSTOMERS_TABLE_NAME"
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery(qry, null)
        val customers = ArrayList<Customer>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "No Records Found", Toast.LENGTH_SHORT).show() else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val customer = Customer()
                customer.customerID = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                customer.customerDate = cursor.getInt(cursor.getColumnIndex(COLUMN_DATE))
                customer.customerCourse = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE))
                customer.customerNo = cursor.getInt(cursor.getColumnIndex(COLUMN_NO))
                customer.customerName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                customer.customerLatlng = cursor.getDouble(cursor.getColumnIndex(COLUMN_Latlng))
                customers.add(customer)
                cursor.moveToNext()
            }
            Toast.makeText(mCtx, "${cursor.count}Records Found", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return customers
    }

    fun addCustomer(mCtx: Context, customer:Customer){
        val values = ContentValues()
        values.put(COLUMN_DATE,customer.customerDate)
        values.put(COLUMN_COURSE,customer.customerCourse)
        values.put(COLUMN_NO,customer.customerNo)
        values.put(COLUMN_NAME,customer.customerName)
        values.put(COLUMN_Latlng,customer.customerLatlng)
        val db = this.writableDatabase
        try{
            db.insert(CUSTOMERS_TABLE_NAME,null,values)
            Toast.makeText(mCtx,"Customer Added", Toast.LENGTH_SHORT).show()
        }catch(e : Exception){
            Toast.makeText(mCtx,e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun deleteCustomer(customerID : Int):Boolean{
        val qry = "Delete From $CUSTOMERS_TABLE_NAME where $COLUMN_ID = $customerID"
        val db = this.writableDatabase
        var result:Boolean = false
        try{
            val cursor = db.execSQL(qry)
            result = true
        } catch (e: Exception){
            Log.e(ContentValues.TAG,"Error Deleting")
        }
        db.close()
        return result
    }

    fun updateCustomer(id:String,customerDate:String,customerCourse:Int,customerNo:Int,customerName:String,customerLatlng:Double):
            Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        var result: Boolean = false
        contentValues.put(COLUMN_DATE, customerDate)
        contentValues.put(COLUMN_COURSE, customerCourse)
        contentValues.put(COLUMN_NO, customerNo)
        contentValues.put(COLUMN_NAME, customerName)
        contentValues.put(COLUMN_Latlng, customerLatlng)

        try {
            db.update(CUSTOMERS_TABLE_NAME, contentValues, "$COLUMN_ID = ?", arrayOf(id))
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error Updating")
            result = false
        }
        return result
    }


    companion object {
        private const val DATABASE_NAME = "MyData.db"
        private const val DATABASE_VERSION = 1

        const val CUSTOMERS_TABLE_NAME = "Customers"
        const val COLUMN_ID = "id"
        const val COLUMN_DATE = "date"
        const val COLUMN_COURSE = "course"
        const val COLUMN_NO = "No"
        const val COLUMN_NAME = "name"
        const val COLUMN_Latlng = "latlng"
    }
}
