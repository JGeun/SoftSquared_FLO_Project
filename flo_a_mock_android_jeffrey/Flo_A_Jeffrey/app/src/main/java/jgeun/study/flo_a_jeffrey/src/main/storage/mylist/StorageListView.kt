package jgeun.study.flo_a_jeffrey.src.main.storage.mylist

import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models.MakeFolderResponse
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models.PostMusicInListResponse
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.models.UserListResponse

interface StorageListView {
    fun onGetAddFolderSuccess(response: MakeFolderResponse)
    fun onGetAddFolderFailure(message: String)

    fun onGetUserListSuccess(response: UserListResponse)
    fun onGetUserListFailure(message: String)

    fun onPostMusicInListSuccess(response: PostMusicInListResponse)
    fun onPostMusicInListFailure(message: String)
}