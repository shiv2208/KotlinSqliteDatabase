package com.example.deeksha.kotlinsqlitedatabase.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kotlinsqlitedatabase.datamodel.UserModel

class KtSqliteDatabase(context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLENAME ( $ID INTEGER PRIMARY KEY AUTOINCREMENT, $USERNAME TEXT,$USERADDRESS TEXT,$PHONE LONG )"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLENAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    companion object {
        private val DATABASENAME = "sqlitedb"
        private val DATABASEVERSION = 1
        private val TABLENAME = "userinfo"
        private val ID = "Id"
        private val USERNAME = "user_name"
        private val USERADDRESS = "useraddress"
        private val PHONE = "mobile"
    }

    fun insertUserData(userModel: UserModel): Boolean {
        val database: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(USERNAME, userModel.name)
        values.put(USERADDRESS, userModel.address)
        values.put(PHONE, userModel.phone)
        val sucess = database.insertOrThrow(TABLENAME, null, values)
        database.close()
        return (Integer.parseInt("$sucess") != -1)
    }

    fun getAllUser(): ArrayList<UserModel> {
        val userList = ArrayList<UserModel>()

        val database: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor
        val query = "SELECT * FROM $TABLENAME"
        cursor = database.rawQuery(query, null)
        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                var userModel = UserModel(
                    name = cursor.getString(cursor.getColumnIndex(USERNAME)),
                    address = cursor.getString(cursor.getColumnIndex(USERADDRESS)),
                    phone = cursor.getLong(cursor.getColumnIndex(PHONE))
                )
                userList.add(userModel)

            }
        }
        return userList
    }

    fun updateData(userModel: UserModel): Boolean {

        val database: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(USERNAME, userModel.name)
        values.put(USERADDRESS, userModel.address)
        values.put(PHONE, userModel.phone)
        val sucess = database.update(TABLENAME, values, ID + "=" + userModel.userID, null)
        database.close()
        return (Integer.parseInt("$sucess") != -1)
    }

    fun getSingleUserData(userNam: Int): UserModel {

        lateinit var userModel: UserModel
        val database: SQLiteDatabase = this.writableDatabase
        var cursor: Cursor = database.rawQuery("Select * from $TABLENAME where $ID=" + userNam, null)

        if (cursor != null && cursor.count > 0) {
            cursor.moveToNext()
            userModel = UserModel(
                name = cursor.getString(cursor.getColumnIndex(USERNAME)),
                address = cursor.getString(cursor.getColumnIndex(USERADDRESS)),
                phone = cursor.getLong(cursor.getColumnIndex(PHONE))
            )
        }
        return userModel

    }
}