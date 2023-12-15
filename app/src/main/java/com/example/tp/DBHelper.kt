package com.example.tp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PFE.db"
    }
    private var etudiantDBHelper: DBHelper? = null

    fun getEtudiantDBHelper(context: Context): DBHelper {
        return etudiantDBHelper ?: synchronized(this) {
            val instance = DBHelper(context)
            etudiantDBHelper = instance
            instance
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(EtudiantConnect.EtudiantDBC.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(EtudiantConnect.EtudiantDBC.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}
