package jgeun.study.flo_a_jeffrey.src.main.storage.mylist

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentStorageMyListBinding
import jgeun.study.flo_a_jeffrey.src.main.storage.addfolder.StorageAddFolderActivity
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.addlistmusic.AddListMusicActivity
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models.*
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.recyclerview.MyListAdapter
import jgeun.study.flo_a_jeffrey.src.music.list.recyclerview.MusicListAdapter

class StorageMyListFragment : BaseFragment<FragmentStorageMyListBinding>(FragmentStorageMyListBinding::bind, R.layout.fragment_storage_my_list), StorageListView{
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var clickListIdx: Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addFolderLlMakeFolder.setOnClickListener{
            startActivityForResult(Intent(context, StorageAddFolderActivity::class.java), 1002)
        }

        showLoadingDialog(context!!)
        StorageListService(this).tryGetUserList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1002){
            if(resultCode == RESULT_OK){
                val listName = data?.getStringExtra("listName")
                if(listName != null) {
                    showLoadingDialog(context!!)
                    StorageListService(this).tryPostListName(PostListName(listName= listName))
                }
            }
        }else if(requestCode == 1003){
            if(resultCode == RESULT_OK){
                val songIdxArray = ArrayList<ResultSongIdx>()
                val selectSongPositionArray = data?.getIntegerArrayListExtra("selectSongPositionArray")
                if(selectSongPositionArray != null) {
                    for(position in selectSongPositionArray)
                        songIdxArray.add(ResultSongIdx(ApplicationClass.userMusicPlayList[position].songIdx))
                }

                if(songIdxArray != null){
                    if(songIdxArray.size != 0){
                        showLoadingDialog(context!!)
                        StorageListService(this).tryPostMusicInList(clickListIdx, PostSongInfo(songInfo = songIdxArray))
                    }else
                        showCustomToast("에러가 발생했습니다.")
                }else{
                    showCustomToast("비어있음")
                }
            }
        }

    }

    override fun onGetAddFolderSuccess(response: MakeFolderResponse) {
        dismissLoadingDialog()
        if(response.code == 1000) {
            clickListIdx = response.result.listIdx
            startActivityForResult(Intent(context, AddListMusicActivity::class.java), 1003)
            StorageListService(this).tryGetUserList()
        }
    }

    override fun onGetAddFolderFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onGetUserListSuccess(response: UserListResponse) {
        dismissLoadingDialog()
        if(response.code == 1000){
            val userList = response.result

            if(userList.size != 0) {
                binding.myListRlEditList.visibility = View.VISIBLE
                binding.myListShowNoList.visibility = View.GONE

                viewManager = LinearLayoutManager(context)
                viewAdapter = MyListAdapter(context!!, userList)

                recyclerView = view!!.findViewById<RecyclerView>(R.id.myList_rv_list).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            }else{
                binding.myListRlEditList.visibility = View.GONE
                binding.myListShowNoList.visibility = View.VISIBLE
            }
        }
    }

    override fun onGetUserListFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

    override fun onPostMusicInListSuccess(response: PostMusicInListResponse) {
        dismissLoadingDialog()
        StorageListService(this).tryGetUserList()
//        viewAdapter.notifyDataSetChanged()
    }

    override fun onPostMusicInListFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}