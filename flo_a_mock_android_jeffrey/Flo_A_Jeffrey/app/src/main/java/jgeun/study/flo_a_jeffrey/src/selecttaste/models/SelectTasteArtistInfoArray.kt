package jgeun.study.flo_a_jeffrey.src.selecttaste.models

import com.google.gson.annotations.SerializedName

data class SelectTasteArtistInfoArray(
        @SerializedName("classificationIdx") val artistIdx: Int,
        @SerializedName("classificationName") val classificationName: String,
        @SerializedName("mainArtist") val mainArtist: ArrayList<SelectTasteArtistInfo>
)