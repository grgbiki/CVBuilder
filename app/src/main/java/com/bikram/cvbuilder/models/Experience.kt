package com.bikram.cvbuilder.models

data class Experience(
    val employer: String,
    val address: String,
    val country: String,
    val jobTitle: String,
    val startDate: String,
    val endDate: String,
    val responsibilities: List<String>
)