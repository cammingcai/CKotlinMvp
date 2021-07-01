package com.camming.mvp.ui.widget.recyclerview.smartrefresh_horizontal;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.AbsListView;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.impl.RefreshContentWrapper;

import static com.scwang.smartrefresh.layout.util.SmartUtil.scrollListBy;

public class RefreshContentHorizontal extends RefreshContentWrapper {

    RefreshContentHorizontal(@NonNull View view) {
        super(view);
    }

    @Override
    public ValueAnimator.AnimatorUpdateListener scrollContentWhenFinished(final int spinner) {
        if (mScrollableView != null && spinner != 0) {
            if ((spinner < 0 && ScrollBoundaryHorizontal.canScrollRight(mScrollableView)) || (spinner > 0 && ScrollBoundaryHorizontal.canScrollLeft(mScrollableView))) {
                mLastSpinner = spinner;
                return this;
            }
        }
        return null;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();
        try {
            if (mScrollableView instanceof AbsListView) {
                scrollListBy((AbsListView) mScrollableView, value - mLastSpinner);
            } else {
                mScrollableView.scrollBy(value - mLastSpinner, 0);
            }
        } catch (Throwable ignored) {
            //根据用户反馈，此处可能会有BUG
        }
        mLastSpinner = value;
    }
}
