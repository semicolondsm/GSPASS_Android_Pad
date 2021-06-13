package com.semicolon.gspass_android_pad.model

data class SetApplyTimeRequest(
    val breakfast_period: String,
    val launch_period: String,
    val dinner_period: String,
    val time_length: Int
)