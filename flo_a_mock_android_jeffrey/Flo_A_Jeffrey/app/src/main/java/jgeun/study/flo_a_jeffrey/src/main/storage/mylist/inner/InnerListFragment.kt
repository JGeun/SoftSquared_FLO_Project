package jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jgeun.study.flo_a_jeffrey.R
import jgeun.study.flo_a_jeffrey.config.BaseFragment
import jgeun.study.flo_a_jeffrey.databinding.FragmentListInnerBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity
import jgeun.study.flo_a_jeffrey.src.main.home.HomeFragment
import jgeun.study.flo_a_jeffrey.src.main.storage.StorageFragment
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.models.InnerListResponse
import jgeun.study.flo_a_jeffrey.src.main.storage.mylist.inner.recyclerview.InnerListAdapter

class InnerListFragment(val listIdx: Int) : BaseFragment<FragmentListInnerBinding>(FragmentListInnerBinding::bind, R.layout.fragment_list_inner),InnerListView{
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(context!!)
        InnerListService(this).tryGetInnerList(listIdx)

        binding.innerListIvExit.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm, StorageFragment()).commitAllowingStateLoss()
        }
    }

    override fun onGetInnerListSuccess(response: InnerListResponse) {
        dismissLoadingDialog()

        if(response.code == 1000){
            val listImageUrl =  response.result.get(0).listImageUrl
            Glide.with(this).load(listImageUrl).into(binding.innerListIvListAlbum)
            binding.innerListTvListTitle.setText(response.result.get(0).listName)
            binding.innerListTvTimeStamp.setText(response.result.get(0).listTimestamp)
            binding.innerListTvSongCount.setText(response.result.get(0).songCount.toString())
            val songArray = response.result.get(0).songInfo

            viewManager = LinearLayoutManager(context)
            viewAdapter = InnerListAdapter(context!!, songArray)

            recyclerView = view!!.findViewById<RecyclerView>(R.id.innerList_rv_item).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        }

    }

    override fun onGetInnerListFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}