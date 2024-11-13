package com.example.weekforecastfeature.Views

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.PresenterLayer.gradiental
import com.example.weekforecastfeature.R
import com.example.weekforecastfeature.ViewModels.WeekForecastViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun WeekForecastRoot() {
    val context = LocalContext.current
    val vm by (context as ComponentActivity).viewModels<WeekForecastViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return WeekForecastViewModel(context) as T
                }
            }
        }
    )
    vm.context = context // due to recomposition, protection from memory leaks
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .gradiental(
                listOf(
                    colorResource(id = R.color.blue1),
                    colorResource(id = R.color.purple1)
                )
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if (vm.loadingResult == null)
        {
            Text("Данные загружаются", fontSize = 34.sp, color = colorResource(id = R.color.red1))
        }
        else {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = "DaysShowcase"
            )
            {
                composable("DaysShowcase") {
                    DaysShowcase(
                        vm = vm,
                        onDayClicked = {
                            navController.navigate("DayShowcase/$it") {
                                popUpTo("DaysShowcase") { inclusive = false }
                            }
                        }
                    )
                }
                composable("DayShowcase/{dayInd}") {
                    DayShowcase(
                        vm = vm,
                        dayInd = it.arguments?.getString("dayInd")?.toIntOrNull() ?: 0,
                        onNearDayClicked = {
                            navController.navigate("DayShowcase/$it") {
                                popUpTo("DaysShowcase") { inclusive = false }
                            }
                        }
                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(60.dp),
                onClick = {
                    vm.loadData()
                },
                shape = RoundedCornerShape(15.dp)
            )
            {
                Text(text = "Обновить", color = colorResource(id = R.color.yellow2))
            }
        }
    }
}