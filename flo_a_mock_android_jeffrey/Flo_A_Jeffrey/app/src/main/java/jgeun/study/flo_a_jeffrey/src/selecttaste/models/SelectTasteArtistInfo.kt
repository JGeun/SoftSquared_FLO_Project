package jgeun.study.flo_a_jeffrey.src.selecttaste.models

import com.google.gson.annotations.SerializedName

data class SelectTasteArtistInfo(
        @SerializedName("artistIdx") val artistIdx: Int,
        @SerializedName("artistName") val artistName: String,
        @SerializedName("artistProfileImageUrl") val artistProfileImageUrl: String,
        @SerializedName("artistTaste") val artistTaste: String
)