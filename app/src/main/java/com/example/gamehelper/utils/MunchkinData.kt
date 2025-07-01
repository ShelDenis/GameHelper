package com.example.gamehelper

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap

class MunchkinData {
    @Stable
    var players: SnapshotStateMap<String, MunchkinGamer> =
        mutableStateMapOf()

    var playersStr: String by mutableStateOf("")

    var type: String by mutableStateOf("Classic")

    var leaderboard = ""

    val classicClasses = listOf("No", "Клирик", "Волшебник", "Вор", "Воин")
    val classicRaces = listOf("No", "Дварф", "Эльф", "Хафлинг")
    val russianClasses = listOf("No", "Олигарх", "Спортсмен", "Хакер", "Казак")

    fun parsePlayers() {
        var pl_list = playersStr.split("\n").toList()
        players.clear()
        for (i in 1 until pl_list.size) {
            if (i == pl_list.size - 1) {
                val mg = MunchkinGamer(pl_list[i].split(")")[0], this)
                players[mg.name] = mg
            }
            else {
                val mg = MunchkinGamer(pl_list[i], this)
                players[mg.name] = mg
            }

        }
    }

    fun getRace(p: MunchkinGamer): String
    {
        var r = ""
        if (type == "Classic" && p.raceNum != 0){
            r = classicRaces[p.raceNum]
        }
        else r = "No"
        p.changeRaceNumber()
        return r
    }

    fun getClass(p: MunchkinGamer): String
    {
        var c = ""
        if (type == "Classic" && p.classNum != 0){
            c = classicClasses[p.classNum]
        }
        else if (type == "Russian" && p.classNum != 0) {
            c = russianClasses[p.classNum]
        }
        else c = "No"
        p.changeClassNumber()
        return c
    }
}