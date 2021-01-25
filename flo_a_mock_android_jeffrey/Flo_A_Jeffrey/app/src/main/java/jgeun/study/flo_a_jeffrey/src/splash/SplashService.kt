package jgeun.study.flo_a_jeffrey.src.splash

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.main.home.models.HomeInfoResponse
import jgeun.study.flo_a_jeffrey.src.splash.models.AutoLoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashService(val view: SplashActivity) {
    fun tryGetAutoLogin(){
        val splashRetrofitInterface = ApplicationClass.sRetrofit.create(SplashRetrofitInterface::class.java)
        splashRetrofitInterface.getAutoLogin().enqueue(object : Callback<AutoLoginResponse> {
            override fun onResponse(call: Call<AutoLoginResponse>, response: Response<AutoLoginResponse>) {
                view.onGetAutoLoginSuccess(response.body() as AutoLoginResponse)
            }

            override fun onFailure(call: Call<AutoLoginResponse>, t: Throwable) {
                view.onGetAutoLoginFailure(t.message ?: "통신 오류")
            }
        })
    }
}