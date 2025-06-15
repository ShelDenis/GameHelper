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
import androidx.compose.material3.RadioButton
import androidx.navigation.NavController

@Composable
fun TrueSettings(navController: NavController, nd: NecessaryData){
    var erotic_state = remember { mutableStateOf(false) }
    var toilet_state = remember { mutableStateOf(false) }
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
            text = "Настройка Правды или Правды",
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
            RadioButton(
                selected = erotic_state.value,
                onClick = { erotic_state.value = !erotic_state.value }
            )
            Text(
                text = "Добавить вопросы 18+",
                style = MaterialTheme.typography.bodyLarge
            )

            RadioButton(
                selected = toilet_state.value,
                onClick = { toilet_state.value = !toilet_state.value }
            )
            Text(text = "Добавить вопросы с сортирным юмором", style = MaterialTheme.typography.bodyLarge)

            Button(
                onClick = { navController.navigate("true_or_true_screen")
                    nd.players = players.value.subSequence(1, players.value.length).toString()
                    nd.erotic = erotic_state.value
                    nd.toilet = toilet_state.value
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Начать игру!")
            }
        }
    }
}