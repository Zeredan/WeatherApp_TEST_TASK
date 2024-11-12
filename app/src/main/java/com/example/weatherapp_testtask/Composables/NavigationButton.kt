package com.example.weatherapp_testtask.Composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp_testtask.R

@Composable
fun NavigationButton(
    onClick: () -> Unit,
    isSelected: Boolean = false,
    selectedColors: List<Color>, //dont need @Stable bcs changes
    unSelectedColors: List<Color> = listOf(Color.White, Color.LightGray), //dont need @Stable bcs changes
    @DrawableRes img: Int
)
{
    Image(
        modifier = Modifier
            .padding(10.dp)
            .border(
                width = if (isSelected) 3.dp else 2.dp,
                brush = object : ShaderBrush(){
                    override fun createShader(size: Size): Shader {
                        return LinearGradientShader(
                            Offset.Zero,
                            size.center * 2f,
                            if (isSelected) selectedColors else unSelectedColors
                        )
                    }
                },
                shape = RoundedCornerShape(10.dp)
            )
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .run {
                if (!isSelected)
                    this.clickable(onClick = onClick)
                else
                    this
            },
        painter = painterResource(img),
        contentDescription = null
    )
}