package com.example.heonlee_assignment2

class PlayerModelClass{

    var playerId : Int = 0
    var playerName : String = ""
    var playerPosition : String = ""
    var playerGoals : Int = 0

    constructor(playerName : String, playerPosition : String, playerGoals : Int){

        this.playerName = playerName
        this.playerPosition = playerPosition
        this.playerGoals = playerGoals
    }

    constructor(playerId : Int, playerName: String, playerPosition: String, playerGoals: Int){
        this.playerId = playerId
        this.playerName = playerName
        this.playerPosition = playerPosition
        this.playerGoals = playerGoals
    }
}