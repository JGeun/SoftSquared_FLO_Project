package jgeun.study.flo_a_jeffrey.src.selecttaste.models

import com.google.gson.annotations.SerializedName

data class PutUserTaste(
        @SerializedName("modifyTaste") val modifyTaste: ArrayList<PutUserTasteInfo>
)