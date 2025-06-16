package com.example.gamehelper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun GameMunchkinScreen(navController: NavController, md: MunchkinData){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Игроки",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        for (pl: MunchkinGamer in md.players.values)
        {
            Text(pl.name)
            Text("Игровой уровень - " + pl.gameLevel + " Бонус за шмотки - "
                    + pl.wearLevel + "ВСЕГО - " + pl.summaryLevel)

            Row {
                Button(
                    onClick = {
                        md.players[pl.name]?.let { it.gameLevel++ }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "+1 уровень")
                }

                Button(
                    onClick = {
                        md.players[pl.name]?.let { it.gameLevel-- }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "-1 уровень")
                }

                Button(
                    onClick = {
                        md.players[pl.name]?.let { it.wearLevel++ }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "+1 уровень")
                }

                Button(
                    onClick = {
                        md.players[pl.name]?.let { it.wearLevel-- }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "-1 уровень")
                }

                Button(
                    onClick = {
                        md.players[pl.name]?.underCurse = !pl.underCurse
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (pl.underCurse)
                        Text(text = "Снять проклятие")
                    else
                        Text(text = "Наложить проклятие")
                }
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        Column {
            Button(
                onClick = { navController.navigate("finish_munchkin_screen")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Завершить игру!")
            }
        }
    }
}