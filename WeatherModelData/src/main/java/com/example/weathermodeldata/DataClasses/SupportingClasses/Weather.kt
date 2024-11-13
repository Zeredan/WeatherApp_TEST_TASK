package com.example.weathermodeldata.DataClasses.SupportingClasses

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)