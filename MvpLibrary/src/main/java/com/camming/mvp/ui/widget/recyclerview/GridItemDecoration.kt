package com.ui.widget.image.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GridItemDecoration: RecyclerView.ItemDecoration {

    private var spanCount: Int
    private var spacing: Int = 0
    private var left: Int = 0
    private var right: Int = 0
    private var top: Int = 0
    private var bottom: Int = 0

    constructor(spanCount: Int, spacing: Int){
        this.spanCount = spanCount
        this.spacing = spacing
    }

    constructor(spanCount: Int, left: Int,right: Int, top: Int, bottom: Int){
        this.spanCount = spanCount
        this.left = left
        this.right = right
        this.top = top
        this.bottom = bottom
    }

//    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        val position = parent.getChildAdapterPosition(view)
//        // 行数
//        val column = position % spanCount
//
//        outRect.bottom = spacing / 2
//        outRect.left = (column * spacing / spanCount)
//        outRect.right = spacing - (column + 1) * spacing / spanCount
//    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val layoutManager = parent.layoutManager as GridLayoutManager?         //判断总的数量是否可以整除
        val totalCount = layoutManager!!.itemCount
        val surplusCount = totalCount % layoutManager.spanCount
        val childPosition = parent.getChildAdapterPosition(view)
        if (layoutManager.orientation == GridLayoutManager.VERTICAL) {//竖直方向的
            if (surplusCount == 0 && childPosition > totalCount - layoutManager.spanCount - 1) {                 //后面几项需要bottom
                outRect.bottom = bottom
            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                outRect.bottom = bottom
            }
            if ((childPosition + 1) % layoutManager.spanCount == 0) {//被整除的需要右边
                outRect.right = right
            }
            outRect.top = top
            outRect.left = left
        } else {
            if (surplusCount == 0 && childPosition > totalCount - layoutManager.spanCount - 1) {                 //后面几项需要右边
                outRect.right = right
            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                outRect.right = right
            }
            if ((childPosition + 1) % layoutManager.spanCount == 0) {//被整除的需要下边
                outRect.bottom = bottom
            }
            outRect.top = top
            outRect.left = left
        }
    }
}