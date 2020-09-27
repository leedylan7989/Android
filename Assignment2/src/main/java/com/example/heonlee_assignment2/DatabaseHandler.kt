package com.example.heonlee_assignment2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,
    DATABASE_VERSION) {

    companion object {
        //Constant variables
        private val DATABASE_NAME = "PlayerDatabase"
        private val DATABASE_VERSION = 1
        private val TABLE_PLAYERS = "PlayerTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "Name"
        private val KEY_POSITION = "Position"
        private val KEY_GOALS = "Goals"
    }

    //CREATE
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PLAYER_TABLE = ("CREATE TABLE " + TABLE_PLAYERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_NAME +
                " TEXT," + KEY_POSITION +
                " TEXT," + KEY_GOALS + " INTEGER" + ")")

        db?.execSQL(CREATE_PLAYER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Drop table if exists and load the table again
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_PLAYERS)

        onCreate(db)
    }

    //INSERT
    fun addPlayer(player: PlayerModelClass): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_NAME, player.playerName)
        contentValues.put(KEY_POSITION, player.playerPosition)
        contentValues.put(KEY_GOALS, player.playerGoals)

        val successRow = db.insert(TABLE_PLAYERS, null, contentValues)

        db.close()

        return successRow
    }

    //READ
    fun viewPlayers() : List<PlayerModelClass> {
        val playerList : ArrayList<PlayerModelClass> = ArrayList<PlayerModelClass>()
        val db =this.writableDatabase
        val selectQuery ="SELECT * FROM $TABLE_PLAYERS"
        var cursor : Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (ex : SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var playerName =""
        var playerPosition = ""
        var playerGoals = 0

        //Start the cursor
        if (cursor.moveToFirst()) {
            do {
                //Get the values using the cursor object
                playerName = cursor.getString(cursor.getColumnIndex("Name"))
                playerPosition = cursor.getString(cursor.getColumnIndex("Position"))
                playerGoals = cursor.getInt(cursor.getColumnIndex(KEY_GOALS))

                //Create a PlayerModelClass object using the retrieved values
                val player : PlayerModelClass = PlayerModelClass(playerName =  playerName,
                    playerPosition = playerPosition,
                    playerGoals = playerGoals)

                //Add to the list
                playerList.add(player)

                //Stop when the cursor reaches the end
            } while (cursor.moveToNext())
        }


        return playerList

    }
}