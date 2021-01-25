package jgeun.study.flo_a_jeffrey.src.music.recommend.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecommendRecyclerViewDecoration(val divWidth: Int) : RecyclerView.ItemDecoration() {
    @Override
    override fun getItemOffsets(outRect : Rect, view: View, parent: RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = divWidth;
    }
}