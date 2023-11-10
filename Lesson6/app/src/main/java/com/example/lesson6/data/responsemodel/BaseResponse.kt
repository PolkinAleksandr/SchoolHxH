package com.example.lesson6.data.responsemodel

import com.google.gson.annotations.SerializedName

class BaseResponse<T>(
    @SerializedName("data") val data: T,
)