package com.example.weekforecastfeature.Views

import android.os.Build
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.PresenterLayer.gradiental
import com.example.weekforecastfeature.R
import com.example.weekforecastfeature.ViewModels.WeekForecastViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun DaysShowcase(vm: WeekForecastViewModel, onDayClicked: ((Int) -> Unit)?) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        val dailyWeather by vm.dayWeatherListSharedFlow.collectAsState(initial = null)

        Spacer(modifier = Modifier.height(0.dp))
        Text("На Неделю", fontSize = 26.sp, color = colorResource(id = R.color.yellow2))
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
                        colorResource(id = R.color.purple1)
                    )
                )
                .fillMaxWidth(0.8f)
                .weight(1f)
        )
        {
            itemsIndexed(dailyWeather ?: emptyList()) { ind, it ->
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .run {
                            onDayClicked?.let { this.clickable { it(ind) } } ?: this
                        }
                        .gradiental(
                            listOf(
                                colorResource(id = R.color.gray3),
                                colorResource(id = R.color.gray2)
                            )
                        )
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(it.time, color = colorResource(id = R.color.yellow2))
                    Divider(
                        modifier = Modifier
                            .width(2.dp)
                            .fillMaxHeight()
                    )
                    Text(
                        "Темп днем: ${it.temperature.day}",
                        color = colorResource(id = R.color.yellow1)
                    )
                    Text(
                        "Темп ночью: ${it.temperature.night}",
                        color = colorResource(id = R.color.yellow1)
                    )
                    Text("Осадки: ${it.rain}", color = colorResource(id = R.color.yellow1))
                }
            }
        }
        Spacer(modifier = Modifier.height(0.dp))
    }
}