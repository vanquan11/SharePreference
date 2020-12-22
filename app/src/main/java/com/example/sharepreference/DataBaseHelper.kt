package com.example.sharepreference

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

val DATABASENAME = "MY DATABASE"
val TABLENAME = "merchandise"
val COL_ID = "id"
val COL_NAME = "name"
val COL_PRICE = "price"
val COL_PRICE_LARGEUNIT = "priceLargeUnit"
val COL_CREATED_DATE = "createdDate"
val COL_MODIFIED_DATE = "modifiedDate"
val COL_MAXQUANTITYMAX = "maxQuantity"
val COL_UNIT = "unit"

class DataBaseHelper (var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " VARCHAR(256)," + COL_PRICE + " INTEGER, " +
                COL_PRICE_LARGEUNIT + " INTEGER, " + COL_CREATED_DATE + " VARCHAR(256), " + COL_MODIFIED_DATE + " VARCHAR(256), " + COL_MAXQUANTITYMAX + " INTEGER, " + COL_UNIT + " VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(merchandise: ObResponsPost) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_ID, merchandise.id)
        contentValues.put(COL_NAME, merchandise.name)
        contentValues.put(COL_PRICE, merchandise.price)
        contentValues.put(COL_PRICE_LARGEUNIT, merchandise.priceLargeUnit)
        contentValues.put(COL_CREATED_DATE, merchandise.createdDate)
//        contentValues.put(COL_MODIFIED_DATE, merchandise.modifiedDate)
//        contentValues.put(COL_MAXQUANTITYMAX, merchandise.maxQuantity)
//        contentValues.put(COL_UNIT, merchandise.unit)

        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Log.d("vq", "Fail insert")
        }
        else {
            Log.d("vq", "Success insert")
        }
    }

    fun writeData(arrMerchandise: MutableList<Result>) {
        val database = this.writableDatabase
        for (item in arrMerchandise){
            val contentValues = ContentValues()
            contentValues.put(COL_ID, item.id)
            contentValues.put(COL_NAME, item.name)
            contentValues.put(COL_PRICE, item.price)
            contentValues.put(COL_PRICE_LARGEUNIT, item.priceLargeUnit)
            contentValues.put(COL_CREATED_DATE, item.createdDate)
            contentValues.put(COL_MODIFIED_DATE, item.modifiedDate)
            contentValues.put(COL_MAXQUANTITYMAX, item.maxQuantity)
            contentValues.put(COL_UNIT, item.unit)
            database.insert(TABLENAME, null, contentValues)
        }
    }


    fun readData(): MutableList<Result> {
        val list: MutableList<Result> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val merchandise = Result()
                merchandise.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                merchandise.name = result.getString(result.getColumnIndex(COL_NAME))
                merchandise.price = result.getString(result.getColumnIndex(COL_PRICE)).toLong()
                merchandise.priceLargeUnit = result.getString(result.getColumnIndex(COL_PRICE_LARGEUNIT)).toInt()
                merchandise.createdDate = result.getString(result.getColumnIndex(COL_CREATED_DATE))
                merchandise.modifiedDate = result.getString(result.getColumnIndex(COL_MODIFIED_DATE))
                merchandise.maxQuantity = result.getString(result.getColumnIndex(COL_MAXQUANTITYMAX)).toInt()
                merchandise.unit = result.getString(result.getColumnIndex(COL_UNIT))
                list.add(merchandise)
            }
            while (result.moveToNext())
        }
        return list
    }

    fun deleteData(id : Int){
        val database = this.writableDatabase
        val result = database.delete(TABLENAME, "$COL_ID = $id",  null)
        if (result == 0){
            Log.d("vq", "Fail delete")
        }else{
            Log.d("vq", "Success delete")
        }
    }

    fun deleteDatabase(){
        val database = this.writableDatabase
        val result = database.delete(TABLENAME, null, null)
        if (result == 0){
        }else{
        }
    }

    fun updateData(merchandise: ObResponsPost, id: Int){
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_ID, merchandise.id)
        contentValues.put(COL_NAME, merchandise.name)
        contentValues.put(COL_PRICE, merchandise.price)
        contentValues.put(COL_PRICE_LARGEUNIT, merchandise.priceLargeUnit)
        contentValues.put(COL_CREATED_DATE, merchandise.createdDate)
//        contentValues.put(COL_MODIFIED_DATE, merchandise.modifiedDate)
//        contentValues.put(COL_MAXQUANTITYMAX, merchandise.maxQuantity)
//        contentValues.put(COL_UNIT, merchandise.unit)

        val result = database.update(TABLENAME, contentValues, "$COL_ID = $id", null )
        if (result == (0)) {
            Log.d("vq", "Fail update")
        }
        else {
            Log.d("vq", "Success update")
        }
    }

}