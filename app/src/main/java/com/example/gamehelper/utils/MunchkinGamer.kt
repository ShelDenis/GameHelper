package com.example.gamehelper

import androidx.compose.runtime.*

class MunchkinGamer(val initialName: String, val md: MunchkinData) {
    val classicClasses = listOf("No", "Клирик", "Волшебник", "Вор", "Воин")
    val classicRaces = listOf("No", "Дварф", "Эльф", "Хафлинг")
    val russianClasses = listOf("No", "Олигарх", "Спортсмен", "Хакер", "Казак")

    private val _name = mutableStateOf(initialName)
    val name by _name

    private val _gameLevel = mutableStateOf(1)
    var gameLevel by _gameLevel

    private val _wearLevel = mutableStateOf(0)
    var wearLevel by _wearLevel

    private val _summaryLevel = derivedStateOf { _gameLevel.value + _wearLevel.value }
    val summaryLevel by _summaryLevel

    private val _mClass = mutableStateOf("No")
    var mClass by _mClass

    private val _race = mutableStateOf("No")
    var race by _race

    private val _underCurse = mutableStateOf(false)
    var underCurse by _underCurse

    var raceNum = 0
    var classNum = 0

    init {
        this._gameLevel.value = 1
        this._wearLevel.value = 0
        this._mClass.value = "No"
        this._race.value = "No"
        this._underCurse.value = false
    }

    fun changeGameLevel(n: Int) {
        gameLevel += n
    }

    fun changeWearLevel(n: Int) {
        wearLevel += n
    }

    fun changeClass(newClass: String) {
        mClass = newClass
    }

    fun changeRace(newRace: String) {
        race = newRace
    }

    fun changeRaceNumber(): Int {
        raceNum = (raceNum + 1) % 3
        return raceNum
    }

    fun changeClassNumber(): Int {
        classNum = (classNum + 1) % 4
        return classNum
    }

    fun getPlRace(): String {
        var r = ""
        if (md.type == "Classic" && raceNum != 0){
            r = classicRaces[raceNum]
        }
        else r = "No"
        changeRaceNumber()
        return r
    }

    fun getPlClass(): String {
        var c = ""
        if (md.type == "Classic" && classNum != 0){
            c = classicClasses[classNum]
        }
        else if (md.type == "Russian" && classNum != 0) {
            c = russianClasses[classNum]
        }
        else c = "No"
        changeClassNumber()
        return c
    }
}