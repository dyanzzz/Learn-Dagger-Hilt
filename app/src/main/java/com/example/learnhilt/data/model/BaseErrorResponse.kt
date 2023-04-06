package com.example.learnhilt.data.model

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @field:SerializedName("error") val error: String? = null,
    @field:SerializedName("errors") val errors: ArrayList<String>? = null,
    @field:SerializedName("error_description") val errorDescription: String? = null
)
