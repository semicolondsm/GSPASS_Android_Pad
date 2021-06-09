package com.semicolon.gspass_android_pad.model

data class SetMealTimeRequest(
    val id: Int,
    val breakFast: String,
    val lunch: String,
    val dinner: String
)