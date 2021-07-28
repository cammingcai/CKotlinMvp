package com.example.kotlinmvp.ui.fragment

import android.graphics.Color
import com.camming.mvp.ui.fragment.BaseFragment
import com.example.kotlinmvp.R
import kotlinx.android.synthetic.main.fragment_collapsing_tool_bar.*
import qiu.niorgai.StatusBarCompat


class CollapsingToolbarFragment : BaseFragment() {
//    private var mAppBarLayout: AppBarLayout? = null
//    private var mCollapsingToolbarLayout: CollapsingToolbarLayout? = null
//    private var mToolbar: Toolbar? = null
    override fun getLayoutId(): Int {
        return R.layout.fragment_collapsing_tool_bar
    }
    override fun initListener() {

    }
    override fun initView() {
        //        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        StatusBarCompat.setStatusBarColorForCollapsingToolbar(
            activity!!,
            appbar,
            collapsing_toolbar,
            toolbar,
            Color.TRANSPARENT
        )
    }
    override fun initData() {}

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            StatusBarCompat.setStatusBarColorForCollapsingToolbar(
                activity!!,
                appbar,
                collapsing_toolbar,
                toolbar,
                Color.TRANSPARENT
            )
        }
    }
}