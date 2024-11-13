package com.example.core.PresenterLayer

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

fun Modifier.gradiental(colors: List<Color>) : Modifier{
    return this.background(object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            return LinearGradientShader(
                Offset.Zero,
                size.center * 2f,
                colors
            )
        }
    })
}