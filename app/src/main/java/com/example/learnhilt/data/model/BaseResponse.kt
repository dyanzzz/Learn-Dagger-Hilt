package com.example.learnhilt.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("code") val code: Int?,
    @field:SerializedName("data") val data: T?,
    @field:SerializedName("error") val error: String? = null,
    @field:SerializedName("error_description") val errorDescription: String? = null
)