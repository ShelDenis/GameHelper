package com.example.gamehelper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FirstMunchkinScreen(navController: NavController, md: MunchkinData){
    var pl_mes = remember { mutableStateOf("") }
    var players = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Добавление игроков",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        TextField(
            value = pl_mes.value,
            onValueChange = { newText -> pl_mes.value = newText }
        )

        Button(
            onClick = { players.value += "\n" + pl_mes.value
                pl_mes.value = ""},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Добавить игрока")
        }

        Text(
            text = "Игроки: " + players.value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 32.dp),

            )
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
                onClick = {
                    md.playersStr = players.toString()
                    navController.navigate("game_munchkin_screen")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Начать игру!")
            }
        }
    }
}