package com.example.todayforecastfeature.Views

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todayforecastfeature.ViewModels.TodayForecastViewModel


@Composable
internal fun HourShowcase(vm: TodayForecastViewModel, hourInd: Int, onNearHourClicked: (Int) -> Unit) {
    val context = (LocalContext.current as ComponentActivity)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        val hourWeatherList by vm.hourWeatherListSharedFlow.collectAsState(initial = null)
        val selectedHour = remember(hourWeatherList, hourInd){
            hourWeatherList?.get(hourInd)
        }
        Spacer(modifier = Modifier.height(0.dp))
        Text("Сегодня|${selectedHour?.time}", fontSize = 26.sp)
        Divider(thickness = 2.dp)
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.DarkGray)
                .fillMaxWidth(0.8f)
                .weight(1f)
        )
        {
            item{
                Text("Температура: ${selectedHour?.temperature}")
                Text("Ощущается: ${selectedHour?.feelsLike}")
                Text("Влажность: ${selectedHour?.humidity}")
                Text("Скорость ветра: ${selectedHour?.windSpeed}")
                Text("Порывы ветра: ${selectedHour?.windGust}")
                Text("угол ветра: ${selectedHour?.windDeg}")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Button(
                modifier = Modifier.weight(2f).fillMaxHeight(),
                onClick = {
                    onNearHourClicked(hourInd - 1)
                },
                enabled = hourInd > 0,
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text("Предыдущий час")
            }
            Button(
                modifier = Modifier.weight(3f).fillMaxHeight(),
                onClick = {
                    context.onBackPressedDispatcher.onBackPressed()
                },
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text("Назад")
            }
            Button(
                modifier = Modifier.weight(2f).fillMaxHeight(),
                onClick = {
                    onNearHourClicked(hourInd + 1)
                },
                enabled = hourInd < ((hourWeatherList?.size ?: 1) - 1),
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text("Следующий час")
            }
        }
        Spacer(modifier = Modifier.height(0.dp))
    }
}