package jgeun.study.flo_a_jeffrey.src.selecttaste

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivitySelectTasteBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.PutUserTaste
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.PutUserTasteInfo
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.PutUserTasteResponse
import jgeun.study.flo_a_jeffrey.src.selecttaste.models.SelectTasteResponse
import jgeun.study.flo_a_jeffrey.src.selecttaste.recyclerview.SelectTasteAdapter
import jgeun.study.flo_a_jeffrey.src.selecttaste.recyclerview.SelectTasteItem

class SelectTasteActivity : BaseActivity<ActivitySelectTasteBinding>(ActivitySelectTasteBinding::inflate), SelectTasteView{
    private lateinit var balladRecyclerView: RecyclerView
    private lateinit var balladViewAdapter: RecyclerView.Adapter<*>
    private lateinit var balladViewManager: RecyclerView.LayoutManager
    private var selectBalladTasteItem = ArrayList<SelectTasteItem>()

    private lateinit var danceRecyclerView: RecyclerView
    private lateinit var danceViewAdapter: RecyclerView.Adapter<*>
    private lateinit var danceViewManager: RecyclerView.LayoutManager

    private var userSelectTasteStoreArray  = ArrayList<UserTasteStore>()
    private var selectDanceTasteItem = ArrayList<SelectTasteItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      //  Log.d("jwt: ", ApplicationClass.sSharedPreferences.getString(X_ACCESS_TOKEN, null)!!)

        showLoadingDialog(this)
        SelectTasteService(this).tryGetUserArtistTaste()

        binding.selectTasteIvBack.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.selectTasteBtnStoreUserTaste.setOnClickListener{
            var putModifyTaste = ArrayList<PutUserTasteInfo>()
            for(userTaste in userSelectTasteStoreArray){
                putModifyTaste.add(PutUserTasteInfo(userTaste.artistIdx,userTaste.artistTaste))
            }
            val putUserTasteRequest = PutUserTaste(putModifyTaste)
            SelectTasteService(this).tryPutUserTaste(putUserTasteRequest)
        }
    }

    override fun onGetArtistInfoSuccess(response: SelectTasteResponse) {
        dismissLoadingDialog()
        if(response.code == 1000){
            Log.d("ArtistArrayCheck", response.message)
            val artistArray = response.result
            Log.d("ArtistArrayCheck", artistArray.get(0).classificationName)

            val balladArtistArray = artistArray.get(0).mainArtist
            for(ballArtist in balladArtistArray)
                selectBalladTasteItem.add(SelectTasteItem(ballArtist.artistIdx, ballArtist.artistName, ballArtist.artistProfileImageUrl, ballArtist.artistTaste))

            val danceArtistList = artistArray.get(1).mainArtist
            for(danceArtist in danceArtistList)
                selectDanceTasteItem.add(SelectTasteItem(danceArtist.artistIdx, danceArtist.artistName, danceArtist.artistProfileImageUrl, danceArtist.artistTaste))

            setRecyclerView()
        }else{
            Log.d("ArtistArrayCheck", response.message)
        }
    }

    override fun onGetArtistInfoFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")

        for(i in 0..5)
            selectBalladTasteItem.add(SelectTasteItem((i+1), "Singer{$(i+1)}", "", "N"))

        for(i in 0..5)
            selectDanceTasteItem.add(SelectTasteItem((i+1), "Singer{$(i+1)}", "", "N"))
        setRecyclerView()
    }

    fun setRecyclerView(){
        balladViewManager = GridLayoutManager(this, 3)
        balladViewAdapter = SelectTasteAdapter(this, selectBalladTasteItem, userSelectTasteStoreArray)

        balladRecyclerView = findViewById<RecyclerView>(R.id.selectTaste_rv_ballad).apply{
            setHasFixedSize(true)
            layoutManager = balladViewManager
            adapter = balladViewAdapter
        }

        danceViewManager = GridLayoutManager(this, 3)
        danceViewAdapter = SelectTasteAdapter(this, selectDanceTasteItem, userSelectTasteStoreArray)
        danceRecyclerView = findViewById<RecyclerView>(R.id.selectTaste_rv_dance).apply{
            setHasFixedSize(true)
            layoutManager = danceViewManager
            adapter = danceViewAdapter
        }
        danceRecyclerView.isNestedScrollingEnabled =false
    }

    override fun onPutUserTasteSuccess(response: PutUserTasteResponse) {
        dismissLoadingDialog()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onPutUserTasteFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }


}