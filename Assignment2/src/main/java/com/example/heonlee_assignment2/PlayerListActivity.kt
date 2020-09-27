package com.example.heonlee_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_playerlist.*

class PlayerListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playerlist)

        val databaseHandler : DatabaseHandler = DatabaseHandler(this)
        val players : List<PlayerModelClass> = databaseHandler.viewPlayers()

        val playerArrayName = Array<String>(players.size){"null"}
        val playerArrayPosition = Array<String>(players.size){"null"}
        val playerArrayGoals = Array<String>(players.size){"0"}
        var index = 0

        for(p in players){
            playerArrayName[index] = p.playerName
            playerArrayPosition[index] = p.playerPosition
            playerArrayGoals[index] = p.playerGoals.toString()
            index++
        }

        val listAdapter = PlayerListAdapter(this, playerArrayName, playerArrayPosition,
            playerArrayGoals)

        listView.adapter = listAdapter
    }
}
