package jgeun.study.flo_a_jeffrey.src.main.storage.mylist

import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StorageListRetrofitInterface {
    @POST("/lists")
    fun postFolderName(@Body params: PostListName) : Call<MakeFolderResponse>

    @GET("/lists")
    fun getUserList() : Call<UserListResponse>

    @POST("/lists/{listid}/songs")
    fun PostMusicInList(@Path("listid") listid: Int, @Body params: PostSongInfo) : Call<PostMusicInListResponse>
}