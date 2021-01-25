package jgeun.study.flo_a_jeffrey.src.music.songinfo

import android.os.Bundle
import android.view.View
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentSongInfoBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.main.home.HomeFragment
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.InnerListFragment
import jgeun.study.flo_a_jeffrey.src.music.MusicPlayerActivity
import jgeun.study.flo_a_jeffrey.src.music.MusicService
import jgeun.study.flo_a_jeffrey.src.music.MusicView
import jgeun.study.flo_a_jeffrey.src.music.models.MusicResponse
import jgeun.study.flo_a_jeffrey.src.music.songinfo.models.SongInfoResponse

class SongInfoFragment : BaseFragment<FragmentSongInfoBinding>(FragmentSongInfoBinding::bind, R.layout.fragment_song_info), SongInfoView{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.songInfoIvExit.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
        }
        SongInfoService(this).tryGetSongInfo(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].songIdx)
    }

    override fun onGetSongInfoSuccess(response: SongInfoResponse) {
        if(response.code == 1000 || response.code == 1001){

            binding.songInfoTvLyrics.setText(response.result.songLyrics)
        }
    }

    override fun onGetSongInfoFailure(message: String) {
        showCustomToast("오류 : $message")
    }


}