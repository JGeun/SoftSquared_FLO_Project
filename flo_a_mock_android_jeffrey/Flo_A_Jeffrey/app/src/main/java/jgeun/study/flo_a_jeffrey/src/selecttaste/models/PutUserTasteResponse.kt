package jgeun.study.flo_a_jeffrey.src.selecttaste.models

import com.google.gson.annotations.SerializedName

data class PutUserTasteResponse(
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)