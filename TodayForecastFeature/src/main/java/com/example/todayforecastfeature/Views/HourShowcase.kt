package com.example.todayforecastfeature.Views

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.PresenterLayer.gradiental
import com.example.todayforecastfeature.R
import com.example.todayforecastfeature.ViewModels.TodayForecastViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
        Text("Сегодня|${selectedHour?.time}", fontSize = 26.sp, color = colorResource(id = R.color.yellow2))
        Divider(thickness = 2.dp)
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.gray2),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .gradiental(
                    listOf(
                        colorResource(id = R.color.darkblue1),
                        colorResource(id = R.color.blue1)
                    )
                )
                .fillMaxWidth(0.8f)
                .weight(1f)
        )
        {
            item{
                Text("Температура: ${selectedHour?.temperature}", color = colorResource(id = R.color.yellow1))
                Text("Ощущается: ${selectedHour?.feelsLike}", color = colorResource(id = R.color.yellow1))
                Text("Влажность: ${selectedHour?.humidity}", color = colorResource(id = R.color.yellow1))
                Text("Скорость ветра: ${selectedHour?.windSpeed}", color = colorResource(id = R.color.yellow1))
                Text("Порывы ветра: ${selectedHour?.windGust}", color = colorResource(id = R.color.yellow1))
                Text("угол ветра: ${selectedHour?.windDeg}", color = colorResource(id = R.color.yellow1))
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
                    onNearHourClicked(hourInd - 1)
                },
                enabled = hourInd > 0,
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text("Предыдущий час", color = colorResource(id = R.color.yellow2))
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
                Text("Назад", color = colorResource(id = R.color.yellow2))
            }
            Button(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                onClick = {
                    onNearHourClicked(hourInd + 1)
                },
                enabled = hourInd < ((hourWeatherList?.size ?: 1) - 1),
                shape = RoundedCornerShape(10.dp)
            )
            {
                Text("Следующий час", color = colorResource(id = R.color.yellow2))
            }
        }
        Spacer(modifier = Modifier.height(0.dp))
    }
}