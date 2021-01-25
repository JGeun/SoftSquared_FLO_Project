package jgeun.study.flo_a_jeffrey.src.selecttaste.models

import com.google.gson.annotations.SerializedName

data class PutUserTasteInfo(
        @SerializedName("artistIdx") val artistIdx: Int,
        @SerializedName("artistTaste") val artistTaste: String
)