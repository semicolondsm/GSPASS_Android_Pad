package com.semicolon.gspass_android_pad.model

data class SetMealTimeRequest(
    val id: Int,
    val breakfast: String,
    val lunch: String,
    val dinner: String
)