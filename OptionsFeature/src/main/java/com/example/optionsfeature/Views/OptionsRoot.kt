package com.example.optionsfeature.Views
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.core.PresenterLayer.gradiental
import com.example.optionsfeature.R
import com.example.optionsfeature.ViewModels.OptionsViewModel

@Composable
fun OptionsRoot() {
    val context = LocalContext.current as ComponentActivity

    val vm by context.viewModels<OptionsViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                    return OptionsViewModel(context) as T
                }
            }
        }
    )
    vm.context = context // due to recomposition, protection from memory leaks
    val fm = LocalFocusManager.current
    Column(
        modifier = Modifier
            .pointerInput(1) {
                detectTapGestures {
                    fm.clearFocus(true)
                }
            }
            .gradiental(
                listOf(
                    colorResource(id = R.color.blue1),
                    colorResource(id = R.color.green1),
                )
            )
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.End
    )
    {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "Настройки", fontSize = 26.sp, color = colorResource(id = R.color.yellow2))
        Divider(thickness = 2.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text("Показывать доп. информацию", color = colorResource(id = R.color.yellow1))
            Spacer(Modifier.width(10.dp))
            Switch(
                checked = vm.savedOptions.showAdditionalInfo,
                onCheckedChange = {
                    vm.updateShowAdditionalInfo(it)
                },
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text("Использовать текущее местоположение", color = colorResource(id = R.color.yellow1))
            Spacer(Modifier.width(10.dp))
            Switch(
                checked = vm.savedOptions.useCurrentLocation,
                onCheckedChange = {
                    vm.updateUseCurrentLocation(it)
                }
            )
        }
        TextField(
            value = vm.savedOptions.selectedCity,
            label = {
                Text("Выбранный город", color = colorResource(id = R.color.cyan2))
            },
            onValueChange = {
                vm.updateSelectedCity(it)
            },
            enabled = !vm.savedOptions.useCurrentLocation,
        )
        TextField(
            value = vm.savedOptions.apiKey,
            label = {
                Text("OpenWeatherMap api key", color = colorResource(id = R.color.cyan2))
            },
            onValueChange = {
                vm.updateApiKey(it)
            },
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(100.dp)
                .align(Alignment.CenterHorizontally),
            onClick = { vm.saveOptions() },
            shape = RoundedCornerShape(20.dp),
            enabled = vm.isSomethingChanged
        )
        {
            Text(text = "Сохранить настройки", fontSize = 26.sp, color = colorResource(id = R.color.yellow1))
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}