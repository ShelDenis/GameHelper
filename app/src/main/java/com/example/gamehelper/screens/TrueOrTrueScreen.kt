package com.example.gamehelper

import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.gamehelper.ui.theme.LightYellow
import androidx.compose.runtime.*


@Composable
fun TrueOrTrue(navController: NavController, nd: NecessaryData){
    var number by remember { mutableStateOf(0) }
    var used_list: MutableList<Int> = mutableListOf()
    val players = nd.players.split("\n")
    var who by remember { mutableStateOf(0) }
    var currentPlayer = players[who]

    var playerStats by remember { mutableStateOf("") }

    var questionsList: List<String>
    if (!nd.erotic and !nd.toilet) questionsList = nd.trueQuestions
    else if (nd.erotic and !nd.toilet) questionsList = nd.trueQuestions + nd.eroticQuestions
    else if (!nd.erotic and nd.toilet) questionsList = nd.trueQuestions + nd.toiletQuestions
    else questionsList = nd.trueQuestions + nd.toiletQuestions + nd.eroticQuestions


    val until = questionsList.size
    number = get_uniq_num(until, used_list)
    used_list.add(number)
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Игра Правда или Правда",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 32.dp)
        )

        Text(
            text = "Правда или правда, " + currentPlayer + "?",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 32.dp)
                .background(LightYellow),

            )

        Text(
            text = questionsList[number],
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 32.dp)
                .background(LightYellow),

            )

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = { number = get_uniq_num(until, used_list)
                used_list.add(number)
                text = questionsList[number]
                playerStats += "|" + (who % players.size).toString() + "->cool"
                who = (who + 1) % players.size
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Замечательно!")
        }

        Button(
            onClick = { number = get_uniq_num(until, used_list)
                used_list.add(number)
                text = questionsList[number]
                playerStats += "|" + (who % players.size).toString() + "->bad"
                who = (who + 1) % players.size
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Фигня!")
        }

        Button(
            onClick = { number = get_uniq_num(until, used_list)
                used_list.add(number)
                text = questionsList[number] },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Другой вопрос")
        }

        Button(
            onClick = {
                nd.gameResult = playerStats
                navController.navigate("true_congrat_screen")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Конец игры")
        }
    }
}