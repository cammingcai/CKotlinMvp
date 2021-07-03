package com.example.kotlinmvp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.camming.mvp.ui.fragment.BaseFragment


class PViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int = mFragments?.size

    private var mFragments = mutableListOf<BaseFragment>()

    fun setData(fragments: List<BaseFragment>) {
        mFragments = if (fragments == null) {
            ArrayList()
        } else {
            ArrayList(fragments)
        }
//        mFragments.clear()
//        mFragments.addAll(fragments)
//        mFragments = ArrayList(fragments)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): BaseFragment {
        return mFragments?.get(position)!!
    }

    override fun getItemPosition(item: Any): Int {
        return POSITION_NONE
    }



    override fun getPageTitle(position: Int): CharSequence {
//        return if(mTitles.isNotEmpty()) mTitles[position] else ""
        return ""
    }
}