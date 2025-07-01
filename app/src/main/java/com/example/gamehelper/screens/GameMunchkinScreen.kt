package com.example.gamehelper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun GameMunchkinScreen(navController: NavController, md: MunchkinData){

    val state = rememberScrollState()

    Box(modifier = Modifier.verticalScroll(state)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = "Игроки",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 32.dp)
            )

            LaunchedEffect(Unit) {
                md.parsePlayers()
            }


            var lps = md.players.values.toList()

            for (i: Int in 0..lps.count() - 1) {
                if (lps[i].gameLevel >= 10) {
                    var lb = ""
                    var cnt = 1
                    for (p: MunchkinGamer in lps.sortedByDescending { it.summaryLevel }){
                        lb += cnt.toString() + ") " + p.name + "\n"
                        cnt ++
                    }
                    md.leaderboard = lb
                    navController.navigate("finish_munchkin_screen")
                }
            }

            for (i: Int in 0..lps.count() - 1) {
                Text(lps[i].name, style = MaterialTheme.typography.titleLarge)
                Text(
                    "Игровой уровень = " + lps[i].gameLevel + "\nБонус за шмотки = "
                            + lps[i].wearLevel + "\nВСЕГО = " + lps[i].summaryLevel
                )

//                Если понадобится в дальнейшем
//                if (lps[i].raceNum != 0)
//                    Text(
//                        "Раса: " + lps[i].getPlRace()
//                    )
//
//                if (lps[i].classNum != 0)
//                    Text(
//                        "Класс: " + lps[i].getPlClass()
//                    )

                Row {
                    Button(
                        onClick = {
                            lps[i].gameLevel ++
                        },
                        modifier = Modifier.width(70.dp)
                    ) {
                        Text(text = "+1 👑")
                    }

                    Button(
                        onClick = {
                            lps[i].gameLevel --
                        },
                        modifier = Modifier.width(70.dp)
                    ) {
                        Text(text = "-1 👑")
                    }

                    Button(
                        onClick = {
                            lps[i].wearLevel ++
                        },
                        modifier = Modifier.width(70.dp)
                    ) {
                        Text(text = "+1 \uD83E\uDDE5")
                    }

                    Button(
                        onClick = {
                            lps[i].wearLevel --
                        },
                        modifier = Modifier.width(70.dp)
                    ) {
                        Text(text = "-1 \uD83E\uDDE5")
                    }

                    Button(
                        onClick = {
                            lps[i].underCurse = !lps[i].underCurse
                        },
                        modifier = Modifier.width(50.dp)
                    ) {
                        if (lps[i].underCurse)
                            Text(text = "\uD83E\uDD29")
                        else
                            Text(text = "\uD83D\uDC80")
                    }
                }
//                Если понадобится в дальнейшем
//                    Button(
//                        onClick = {
//                            lps[i].changeClassNumber()
//                        },
//                        modifier = Modifier.width(75.dp)
//                    ) {
//                        Text(text = "\uD83E\uDDDD")
//                    }
//
//                    if (md.type == "Classic") {
//                        Button(
//                            onClick = {
//                                lps[i].changeRaceNumber()
//                            },
//                            modifier = Modifier.width(75.dp)
//                        ) {
//                            Text(text = "\uD83E\uDDCC")
//                        }
//                    }
                    Spacer(Modifier.height(10.dp))
                }

                Button(
                    onClick = {
                        var lb = ""
                        var cnt = 1
                        for (p: MunchkinGamer in lps.sortedByDescending { it.summaryLevel }){
                            lb += cnt.toString() + ") " + p.name + "\n"
                            cnt ++
                        }
                        md.leaderboard = lb
                        navController.navigate("finish_munchkin_screen")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Завершить игру!")
                }
            }
    }
}