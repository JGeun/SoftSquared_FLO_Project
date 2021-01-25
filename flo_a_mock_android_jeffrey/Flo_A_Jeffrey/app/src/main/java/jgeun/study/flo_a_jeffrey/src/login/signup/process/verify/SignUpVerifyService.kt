package jgeun.study.flo_a_jeffrey.src.login.signup.process.verify

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.login.signup.process.verify.models.GetSignUpVerifyRequest
import jgeun.study.flo_a_jeffrey.src.login.signup.process.verify.models.SignUpVerifyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpVerifyService(val view : SignUpVerifyActivity) {

    fun tryGetSignUpVerify(name: String, phoneNo: String){
        val signUpVerifyRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpVerifyRetrofitInterface::class.java)
        signUpVerifyRetrofitInterface.getSignUpVerify(name, phoneNo).enqueue(object: Callback<SignUpVerifyResponse>{
            override fun onResponse(call: Call<SignUpVerifyResponse>, response: Response<SignUpVerifyResponse>){
                view.onGetSignUpVerifySuccess(response.body() as SignUpVerifyResponse)
            }

            override fun onFailure(call: Call<SignUpVerifyResponse>, t: Throwable) {
                view.onGetSignUpVerifyFailure(t.message ?: "통신 오류")
            }
        })
    }
}