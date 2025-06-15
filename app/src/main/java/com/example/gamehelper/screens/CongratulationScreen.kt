package com.example.gamehelper

import androidx.compose.foundation.Image
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gamehelper.ui.theme.LightYellow

@Composable
fun CongratulationScreen(navController: NavController, nd: NecessaryData)
{
    val scores = getScore(nd)
    val players = nd.players.split("\n").map { it.trim() }

    val playerScorePairs = players.zip(scores)

    val sortedPlayerScorePairs = playerScorePairs.sortedByDescending { it.second }

    var winners = ""
    for ((rank, pair) in sortedPlayerScorePairs.withIndex()) {
        winners += "${rank + 1}) ${pair.first} - ${pair.second} очков\n"
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Конец игры",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        Text(
            text = winners,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 32.dp)
                .background(LightYellow),

            )

        Image(
            painter = painterResource(id = R.drawable.hlopushka),
            contentDescription = "Ura",
            modifier = Modifier
                .height(250.dp)
                .width(250.dp)

        )

        Button(
            onClick = { navController.navigate("initial_screen") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Вернуться в меню")
        }
    }
}