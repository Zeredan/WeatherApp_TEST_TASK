package com.example.todayforecastfeature.Views

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todayforecastfeature.ViewModels.TodayForecastViewModel

@Composable
fun TodayForecastRoot() {
    val context = LocalContext.current
    val vm by (context as ComponentActivity).viewModels<TodayForecastViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return TodayForecastViewModel(context) as T
                }
            }
        }
    )
    vm.context = context // due to recomposition, protection from memory leaks
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "TodayShowcase"
    )
    {
        composable("TodayShowcase"){
            TodayShowcase(
                vm = vm,
                onHourClicked = {
                    navController.navigate("HourShowcase/$it"){
                        popUpTo("TodayShowcase"){ inclusive = false }
                    }
                }
            )
        }
        composable("HourShowcase/{hourInd}"){
            println("qwert")
            println(it.arguments?.getString("hourInd"))
            HourShowcase(
                vm = vm,
                hourInd = it.arguments?.getString("hourInd")?.toIntOrNull() ?: 0,
                onNearHourClicked = {
                    navController.navigate("HourShowcase/$it"){
                        popUpTo("TodayShowcase"){ inclusive = false }
                    }
                }
            )
        }
    }
}