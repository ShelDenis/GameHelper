package com.example.gamehelper

fun getScore(nd: NecessaryData): Array<Int> {
    val logs = nd.gameResult
    val logList = logs.split("|").subList(1, logs.split("|").size)
    var scores = arrayOf(0)
    repeat(nd.players.split("\n").size - 1) {
        scores += 0
    }
    for (log in logList) {
        val sLog = log.split("->")
        val num = sLog[0].toInt()
        val action = sLog[1]
        if (action == "cool")
            scores[num] += 1
    }

    return scores
}