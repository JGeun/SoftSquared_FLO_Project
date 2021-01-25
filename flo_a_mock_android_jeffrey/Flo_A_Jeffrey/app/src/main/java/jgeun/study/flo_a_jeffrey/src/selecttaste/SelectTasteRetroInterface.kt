package jgeun.study.flo_a_jeffrey.src.selecttaste

import jgeun.study.flo_a_jeffrey.src.selecttaste.models.PutUserTaste
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.PutUserTasteResponse
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.SelectTasteResponse
import retrofit2.Call
import retrofit2.http.*

interface SelectTasteRetroInterface {
    @GET("/tastes")
    fun getUserArtistTaste() : Call<SelectTasteResponse>

    @PUT("/tastes")
    fun putUserTaste(@Body params: PutUserTaste) : Call<PutUserTasteResponse>
}