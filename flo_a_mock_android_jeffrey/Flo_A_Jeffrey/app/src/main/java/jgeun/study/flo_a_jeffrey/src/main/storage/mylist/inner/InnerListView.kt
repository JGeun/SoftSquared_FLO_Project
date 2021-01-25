package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner

import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.models.InnerListResponse

interface InnerListView{
    fun onGetInnerListSuccess(response: InnerListResponse)
    fun onGetInnerListFailure(message : String)
}