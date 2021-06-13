package com.semicolon.gspass_android_pad.model

import com.google.gson.annotations.SerializedName

data class PostSchoolResponse(
    @SerializedName("random_code") val randomCode: String,
    @SerializedName("school_name") val schoolName: String
)