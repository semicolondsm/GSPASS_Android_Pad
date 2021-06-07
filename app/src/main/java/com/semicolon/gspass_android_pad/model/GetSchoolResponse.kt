package com.semicolon.gspass_android_pad.model

import com.google.gson.annotations.SerializedName

data class GetSchoolResponse(
    @SerializedName("sc_code")val scCode: String,
    @SerializedName("school_code")val schoolCode: String,
    val location: String,
    val name: String
)