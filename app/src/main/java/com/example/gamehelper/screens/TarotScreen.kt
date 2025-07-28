package com.example.gamehelper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun TarotScreen(navController: NavController){

    val state = rememberScrollState()

    val nCards = remember { mutableStateOf(1) }
    val cardNums = remember { mutableStateOf(emptyList<Int>()) }
    val n = 78
    val taro = TarotData()

    Box(modifier = Modifier.verticalScroll(state)) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Сколько карт нужно для гадания?",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 32.dp)
            )

            val marks = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                marks.forEach { m ->
                    Text(
                        text = m.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Slider(
                value = nCards.value.toFloat(),
                onValueChange = { newValue ->
                    val roundedValue = newValue.roundToInt()
                    if (roundedValue in 1..12) {
                        nCards.value = roundedValue
                    }
                },
                valueRange = 1f..12f,
                steps = 11
            )

            val turnList = remember { mutableListOf<Boolean>() }

            Button(
                onClick = {
                    val uniqueCardNums = mutableSetOf<Int>()
                    while (uniqueCardNums.size < nCards.value) {
                        uniqueCardNums.add(Random.nextInt(0, n))
                    }
                    cardNums.value = uniqueCardNums.toList()
                    
                    val updatedTurnList = List(nCards.value) { Random.nextBoolean() }
                    turnList.clear()
                    turnList.addAll(updatedTurnList)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Получить расклад")
            }



            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(cardNums.value.size) { index ->
                    var address = taro.imageDict[cardNums.value[index]]
                    AsyncImage(
                        model = address,
                        contentDescription = null,
                        modifier = Modifier.size(300.dp)
                            .rotate(if (!turnList[index]) 180f else 0f)
                    )
                }
            }

            var text_data = ""
            for (i: Int in 0..cardNums.value.size - 1) {
                if (turnList[i])
                    text_data += taro.nameDict[cardNums.value[i]] + " --- " + taro.straightVals[cardNums.value[i]] + "\n\n"
                else
                    text_data += taro.nameDict[cardNums.value[i]] + " --- " + taro.turnedVals[cardNums.value[i]] + "\n\n"
            }

            Text(text_data)


            Button(
                onClick = {
                    navController.navigate("initial_screen")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Вернуться в меню")
            }
        }


    }


}