package com.example.todayforecastfeature.Views

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
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todayforecastfeature.ViewModels.TodayForecastViewModel


@Composable
internal fun TodayShowcase(vm: TodayForecastViewModel, onHourClicked: ((Int) -> Unit)?) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        val currentWeather by vm.currentWeatherSharedFlow.collectAsState(initial = null)
        val hourWeatherList by vm.hourWeatherListSharedFlow.collectAsState(initial = null)

        Spacer(modifier = Modifier.height(0.dp))
        Text("Сегодня", fontSize = 26.sp)
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
                Text("Температура: ${currentWeather?.temperature}")
                Text("Ощущается как: ${currentWeather?.feelsLike}")
            }
        }
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
            itemsIndexed(hourWeatherList ?: emptyList()){ ind, it ->
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .run{
                            onHourClicked?.let{this.clickable{ it(ind) }} ?: this
                        }
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(it.time)
                    Divider(modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight())
                    Text("Темп: ${it.temperature}")
                    Text("Ощущ: ${it.feelsLike}")
                }
            }
        }
        Spacer(modifier = Modifier.height(0.dp))
    }
}