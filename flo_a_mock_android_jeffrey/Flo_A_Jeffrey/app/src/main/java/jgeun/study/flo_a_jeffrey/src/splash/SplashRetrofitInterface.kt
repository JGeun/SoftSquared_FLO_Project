package jgeun.study.flo_a_jeffrey.src.splash

import jgeun.study.flo_a_jeffrey.src.splash.models.AutoLoginResponse
import retrofit2.Call
import retrofit2.http.GET

interface SplashRetrofitInterface {
    @GET("/logins")
    fun getAutoLogin() : Call<AutoLoginResponse>
}