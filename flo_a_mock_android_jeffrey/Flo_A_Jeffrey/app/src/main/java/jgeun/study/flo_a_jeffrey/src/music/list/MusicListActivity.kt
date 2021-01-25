package jgeun.study.flo_a_jeffrey.src.music.list

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.StrictMode
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivityMusicListBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.music.MusicPlayerActivity
import jgeun.study.flo_a_jeffrey.src.music.list.recyclerview.MusicListAdapter
import okhttp3.internal.notify

class MusicListActivity : BaseActivity<ActivityMusicListBinding>(ActivityMusicListBinding::inflate) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build(); StrictMode.setThreadPolicy(policy);

        viewManager = LinearLayoutManager(this)
        viewAdapter = MusicListAdapter(this)

        recyclerView = findViewById<RecyclerView>(R.id.musicList_rv_userPlayList).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        if(ApplicationClass.isPlaying){
            binding.musicListIvPlay.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_pause, null))
            binding.musicListIvPlay.setPadding(0, 0, 15, 0)
            binding.musicListIvPlay.setTag("2")
        }else{
            binding.musicListIvPlay.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_list_play, null))
            binding.musicListIvPlay.setPadding(0, 0, 0, 0)
            binding.musicListIvPlay.setTag("2")
        }

        val musicPath = "https://product.jun-softsquared.shop/spring.mp3"
        val url = musicPath

        val listMusicPlay = binding.musicListIvPlay
        listMusicPlay.setOnClickListener{
            if(listMusicPlay.getTag().equals("1")) {
                if (ApplicationClass.pausePosition == -1) {
                    ApplicationClass.mediaPlayer = MediaPlayer().apply {
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        setDataSource(url)
                        prepare()
                        start()
                        ApplicationClass.pausePosition = 0
                    }

                    ApplicationClass.mediaPlayer.setOnCompletionListener {
                        listMusicPlay.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_play, null))
                        listMusicPlay.setPadding(15, 0, 0, 0)
                        ApplicationClass.isPlaying = false
                        ApplicationClass.pausePosition = -1
                        ApplicationClass.mediaPlayer.release()
                        listMusicPlay.setTag("1")
                    }
                }
                listMusicPlay.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_player_pause, null))
                listMusicPlay.setPadding(0, 0, 15, 0)
                listMusicPlay.setTag("2")
                ApplicationClass.mediaPlayer.seekTo(ApplicationClass.pausePosition)
                ApplicationClass.mediaPlayer.start()
                ApplicationClass.isPlaying = true
            }else{
                listMusicPlay.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_music_list_play, null))
                listMusicPlay.setPadding(0, 0, 0, 0)
                listMusicPlay.setTag("1")
                ApplicationClass.pausePosition = ApplicationClass.mediaPlayer.currentPosition
                ApplicationClass.mediaPlayer.pause()
                ApplicationClass.isPlaying = false
            }
        }
        Glide.with(this).load(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].songImageUrl).into(binding.musicListIvAlbum)

        binding.musicListIvPlayerDown.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.musicListIvAlbum.setOnClickListener {
            startActivity(Intent(this, MusicPlayerActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1000){
            if(resultCode == RESULT_OK){
                viewAdapter.notifyDataSetChanged()
                Glide.with(this).load(ApplicationClass.userMusicPlayList[ApplicationClass.musicPlayIndex].songImageUrl).into(binding.musicListIvAlbum)
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        super.onBackPressed()
    }
}