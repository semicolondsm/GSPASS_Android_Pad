package com.semicolon.gspass_android_pad.model

import com.google.gson.annotations.SerializedName

data class SetApplyTimeRequest(
    @SerializedName("breakfast_period")val breakfastPeriod: String,
    @SerializedName("lunch_period")val launchPeriod: String,
    @SerializedName("dinner_period")val dinnerPeriod: String,
    @SerializedName("time_length")val timeLength: Int
)