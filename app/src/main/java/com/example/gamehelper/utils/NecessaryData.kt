package com.example.gamehelper

class NecessaryData {
    val trueQuestions: List<String>
    val eroticQuestions: List<String>
    val toiletQuestions: List<String>
    var players: String = ""
    var erotic: Boolean = false
    var toilet: Boolean = false
    var gameResult = ""


    constructor() {
        val gd = GameData()
        trueQuestions = gd.trueData.split("\n")
        eroticQuestions = gd.erotic_data.split("\n")
        toiletQuestions = gd.toilet_data.split("\n")
    }
}