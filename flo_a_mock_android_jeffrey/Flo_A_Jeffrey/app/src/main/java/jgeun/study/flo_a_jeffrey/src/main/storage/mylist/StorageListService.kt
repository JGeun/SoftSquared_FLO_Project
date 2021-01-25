package jgeun.study.flo_a_jeffrey.src.main.storage.mylist

import jgeun.study.flo_a_jeffrey.config.ApplicationClass
import jgeun.study.flo_a_jeffrey.src.main.storage.StorageFragment
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StorageListService(val view: StorageMyListFragment) {
    fun tryPostListName(postListName: PostListName){
        val storageRetrofitInterface = ApplicationClass.sRetrofit.create(StorageListRetrofitInterface::class.java)
        storageRetrofitInterface.postFolderName(postListName).enqueue(object : Callback<MakeFolderResponse>{
            override fun onResponse(call: Call<MakeFolderResponse>, response: Response<MakeFolderResponse>) {
                view.onGetAddFolderSuccess(response.body() as MakeFolderResponse)
            }

            override fun onFailure(call: Call<MakeFolderResponse>, t: Throwable) {
                view.onGetAddFolderFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetUserList(){
        val storageRetrofitInterface = ApplicationClass.sRetrofit.create(StorageListRetrofitInterface::class.java)
        storageRetrofitInterface.getUserList().enqueue(object : Callback<UserListResponse>{
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                view.onGetUserListSuccess(response.body() as UserListResponse)
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                view.onGetUserListFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostMusicInList(listIdx:Int, postSongInfo: PostSongInfo) {
        val storageRetrofitInterface = ApplicationClass.sRetrofit.create(StorageListRetrofitInterface::class.java)
        storageRetrofitInterface.PostMusicInList(listIdx, postSongInfo).enqueue(object : Callback<PostMusicInListResponse> {
            override fun onResponse(call: Call<PostMusicInListResponse>, response: Response<PostMusicInListResponse>) {
                view.onPostMusicInListSuccess(response.body() as PostMusicInListResponse)
            }

            override fun onFailure(call: Call<PostMusicInListResponse>, t: Throwable) {
                view.onPostMusicInListFailure(t.message ?: "통신 오류")
            }
        })
    }
}