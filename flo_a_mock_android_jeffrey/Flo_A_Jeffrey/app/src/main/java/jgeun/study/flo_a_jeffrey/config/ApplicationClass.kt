package jgeun.study.flo_a_jeffrey.config

import android.app.Application
import android.content.SharedPreferences
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kakao.sdk.common.KakaoSdk
import jgeun.study.flo_a_jeffrey.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    val API_URL = "https://product.jun-softsquared.shop/"

    // 테스트 서버 주소
    // val API_URL = "http://dev-api.test.com/"

    // 실 서버 주소
    // val API_URL = "http://api.test.com/"

    // 코틀린의 전역변수 문법
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences

        // JWT Token Header 키 값
        var X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        var USER_EMAIL = "USER-EMAIL"

        // Retrofit 인스턴스, 앱 실행시 한번만 생성하여 사용합니다.
        lateinit var sRetrofit: Retrofit

        var musicUrl: String = "https://product.jun-softsquared.shop/night.mp3"
        var userMusicPlayList = ArrayList<UserPlayList>()
        var musicPlayIndex = 0
        var mediaPlayer =  MediaPlayer()
        var pausePosition : Int = -1
        var isPlaying: Boolean = false
        var isHaveDetailInfo: Boolean = false
        lateinit var musicDetail : UserPlayDetail
    }

    // 앱이 처음 생성되는 순간, SP를 새로 만들어주고, 레트로핏 인스턴스를 생성합니다.
    override fun onCreate() {
        super.onCreate()
        sSharedPreferences =
                applicationContext.getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
        // 레트로핏 인스턴스 생성
        initRetrofitInstance()

        //카카오 init
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }

    // 레트로핏 인스턴스를 생성하고, 레트로핏에 각종 설정값들을 지정해줍니다.
    // 연결 타임아웃시간은 5초로 지정이 되어있고, HttpLoggingInterceptor를 붙여서 어떤 요청이 나가고 들어오는지를 보여줍니다.
    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(HttpLoggingInterceptor { message: String ->
                    Log.d(
                            "network_info",
                            message
                    )
                }.setLevel(HttpLoggingInterceptor.Level.BODY)) // API Response 로그 작성용
                .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                .build()

       val gson: Gson = GsonBuilder().setLenient().create()
        // sRetrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        sRetrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

}