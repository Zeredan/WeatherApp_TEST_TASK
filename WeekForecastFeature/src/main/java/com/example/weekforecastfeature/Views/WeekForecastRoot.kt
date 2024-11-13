package com.example.weekforecastfeature.Views

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weekforecastfeature.ViewModels.WeekForecastViewModel

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
    NavHost(
        navController = navController,
        startDestination = "DaysShowcase"
    )
    {
        composable("DaysShowcase"){
            DaysShowcase(
                vm = vm,
                onDayClicked = {
                    navController.navigate("DayShowcase/$it"){
                        popUpTo("DaysShowcase"){ inclusive = false }
                    }
                }
            )
        }
        composable("DayShowcase/{dayInd}"){
            DayShowcase(
                vm = vm,
                dayInd = it.arguments?.getString("dayInd")?.toIntOrNull() ?: 0,
                onNearDayClicked = {
                    navController.navigate("DayShowcase/$it"){
                        popUpTo("DaysShowcase"){ inclusive = false }
                    }
                }
            )
        }
    }
}