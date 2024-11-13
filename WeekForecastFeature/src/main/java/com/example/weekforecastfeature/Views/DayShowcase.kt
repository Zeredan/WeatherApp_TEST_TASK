package com.example.weekforecastfeature.Views

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
import com.example.weekforecastfeature.ViewModels.WeekForecastViewModel


@Composable
internal fun DayShowcase(vm: WeekForecastViewModel, dayInd: Int, onNearDayClicked: (Int) -> Unit) {
    val context = (LocalContext.current as ComponentActivity)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        val dayWeatherList by vm.dayWeatherListSharedFlow.collectAsState(initial = null)
        val selectedDay = remember(dayWeatherList, dayInd){
            dayWeatherList?.get(dayInd)
        }
        Spacer(modifier = Modifier.height(0.dp))
        Text("${selectedDay?.time}", fontSize = 26.sp)
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
                Text("Минимальная температура: ${selectedDay?.temperature?.min}")
                Text("Максимальная температура: ${selectedDay?.temperature?.max}")
                Text("Температура утром: ${selectedDay?.temperature?.morning}")
                Text("Температура днем: ${selectedDay?.temperature?.day}")
                Text("Температура вечером: ${selectedDay?.temperature?.evening}")
                Text("Температура ночью: ${selectedDay?.temperature?.night}")
                Text("Скорость ветра: ${selectedDay?.windSpeed}")
                Text("Порывы ветра: ${selectedDay?.windGust}")
                Text("угол ветра: ${selectedDay?.windDeg}")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        )
        {
            Button(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                onClick = {
                    onNearDayClicked(dayInd - 1)
                },
                enabled = dayInd > 0,
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text("Предыдущий день")
            }
            Button(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                onClick = {
                    context.onBackPressedDispatcher.onBackPressed()
                },
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text("Назад")
            }
            Button(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                onClick = {
                    onNearDayClicked(dayInd + 1)
                },
                enabled = dayInd < ((dayWeatherList?.size ?: 1) - 1),
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text("Следующий день")
            }
        }
        Spacer(modifier = Modifier.height(0.dp))
    }
}