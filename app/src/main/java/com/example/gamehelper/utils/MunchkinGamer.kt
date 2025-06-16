package com.example.gamehelper

class MunchkinGamer {
    val name: String
    var gameLevel: Int
    var wearLevel: Int
    var summaryLevel: Int
    var mClass: String
    var underCurse: Boolean


    constructor(name: String, gameLevel: Int, wearLevel: Int, mClass: String = "No class") {
        this.name = name
        this.gameLevel = gameLevel
        this.wearLevel = wearLevel
        this.summaryLevel = gameLevel + wearLevel
        this.mClass = mClass
        this.underCurse = false
    }

    fun changeGameLevel(n: Int) {
        this.gameLevel += n
        updateSumLevel()
    }

    fun changeWearLevel(n: Int) {
        this.wearLevel += n
        updateSumLevel()
    }

    fun changeClass(newClass: String){
        this.mClass = newClass
        updateSumLevel()
    }

    fun updateSumLevel() {
        this.summaryLevel = gameLevel + wearLevel
        if (mClass == "Воин")
            this.summaryLevel += 1
    }
}