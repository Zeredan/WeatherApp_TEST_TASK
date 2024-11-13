package com.example.weatherapp_testtask

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseInOutCirc
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.optionsfeature.Views.OptionsRoot
import com.example.todayforecastfeature.Views.TodayForecastRoot
import com.example.weatherapp_testtask.Composables.NavigationButton
import com.example.weatherapp_testtask.ui.theme.WeatherApp_TestTaskTheme
import com.example.weekforecastfeature.Views.WeekForecastRoot

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    fun AppRoot() {
        val snackbarHostState = remember{ SnackbarHostState() }
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxSize(),
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            //It works like ConstraintLayout, and content() is taking ALL THE SCREEN; so children can move through edge of bottomBar and be hided
            /*bottomBar = {
                Column {

                }
            }*/
        )
        {
            it
            Column(
                modifier = Modifier
                    .fillMaxSize()
            )
            {
                NavHost(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    navController = navController,
                    startDestination = "TodayForecast"
                )
                {
                    composable("TodayForecast") {
                        TodayForecastRoot()
                    }
                    composable("WeekForecast") {
                        WeekForecastRoot()
                    }
                    composable("Options") {
                        OptionsRoot()
                    }
                }
                Divider(thickness = 2.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black)
                        .height(80.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                )
                {
                    //Не буду создавать мапу строка - @Composable функция, так как всего 3 фичи, сделаю навигацию читабельно руками
                    val animatedColor by rememberInfiniteTransition(
                        label = "selectedBorderColor",
                    ).animateColor(
                        initialValue = Color.Blue,
                        targetValue = Color.Yellow,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000, easing = EaseInOutCirc),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "color"
                    )
                    // be carefull, navController.currentDestination IS NOT A STATE, thats why
                    // we dont subscribe to recomposition from it. If there's no auto-recomposing animation,
                    // after switching screen(navigating) we'll not see changes in bottom bar
                    NavigationButton(
                        onClick = {
                            navController.navigate("TodayForecast") {
                                this.popUpTo("TodayForecast") { inclusive = true }
                            }
                        },
                        isSelected = navController.currentDestination?.route == "TodayForecast",
                        selectedColors = listOf(
                            animatedColor,
                            animatedColor.copy(
                                red = 1f - animatedColor.green,
                                green = 1f - animatedColor.blue,
                                blue = 1f - animatedColor.red
                            )
                        ),
                        img = R.drawable.feat_1_icon
                    )
                    NavigationButton(
                        onClick = {
                            navController.navigate("WeekForecast") {
                                this.popUpTo("TodayForecast") { inclusive = false }
                            }
                        },
                        isSelected = navController.currentDestination?.route == "WeekForecast",
                        selectedColors = listOf(
                            animatedColor,
                            animatedColor.copy(
                                red = 1f - animatedColor.green,
                                green = 1f - animatedColor.blue,
                                blue = 1f - animatedColor.red
                            )
                        ),
                        img = R.drawable.feat_2_icon
                    )
                    NavigationButton(
                        onClick = {
                            navController.navigate("Options") {
                                this.popUpTo("TodayForecast") { inclusive = false }
                            }
                        },
                        isSelected = navController.currentDestination?.route == "Options",
                        selectedColors = listOf(
                            animatedColor,
                            animatedColor.copy(
                                red = 1f - animatedColor.green,
                                green = 1f - animatedColor.blue,
                                blue = 1f - animatedColor.red
                            )
                        ),
                        img = R.drawable.feat_3_icon
                    )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp_TestTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    AppRoot()
                }
            }
        }
    }
}