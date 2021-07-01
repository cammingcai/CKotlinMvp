package com.camming.mvp.ui.widget.recyclerview.smartrefresh_horizontal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.camming.mvp.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

@SuppressWarnings("unused")
public class SmartRefreshHorizontal extends SmartRefreshLayout {

    protected static DefaultRefreshHeaderCreator sHeaderCreator = null;
    protected static DefaultRefreshFooterCreator sFooterCreator = null;
    protected static DefaultRefreshInitializer sRefreshInitializer = null;

    protected boolean isInLayout = false;

    public SmartRefreshHorizontal(Context context) {
        this(context, null);
    }

    public SmartRefreshHorizontal(Context context, AttributeSet attrs) {
        super(wrapper(context), attrs);
        setScrollBoundaryDecider(new ScrollBoundaryHorizontal());
    }

    protected static Context wrapper(Context context) {
        DefaultRefreshInitializer old = SmartRefreshLayout.sRefreshInitializer;
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultHorizontalInitializer(sRefreshInitializer, old));
        return context;
    }


    //<editor-fold desc="开发接口">
    /**
     * 设置默认 Header 构建器
     * @param creator Header构建器
     */
    public static void setDefaultRefreshHeaderCreator(@NonNull DefaultRefreshHeaderCreator creator) {
        sHeaderCreator = creator;
    }

    /**
     * 设置默认 Footer 构建器
     * @param creator Footer构建器
     */
    public static void setDefaultRefreshFooterCreator(@NonNull DefaultRefreshFooterCreator creator) {
        sFooterCreator = creator;
    }

    /**
     * 设置默认 Refresh 初始化器
     * @param initializer 全局初始化器
     */
    public static void setDefaultRefreshInitializer(@NonNull DefaultRefreshInitializer initializer) {
        sRefreshInitializer = initializer;
    }
    //</editor-fold>

    //<editor-fold desc="重写方法">

    @Override
    protected void onAttachedToWindow() {
        DefaultRefreshHeaderCreator oldHeaderCreator = SmartRefreshLayout.sHeaderCreator;
        DefaultRefreshFooterCreator oldFooterCreator = SmartRefreshLayout.sFooterCreator;
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(sHeaderCreator);
        SmartRefreshLayout.setDefaultRefreshFooterCreator(sFooterCreator);
        super.onAttachedToWindow();
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(oldHeaderCreator);
        SmartRefreshLayout.setDefaultRefreshFooterCreator(oldFooterCreator);

        final View thisView = this;
        if (mRefreshContent != null && !(mRefreshContent instanceof RefreshContentHorizontal)) {
            mRefreshContent = new RefreshContentHorizontal(mRefreshContent.getView());
            View fixedHeaderView = mFixedHeaderViewId > 0 ? thisView.findViewById(mFixedHeaderViewId) : null;
            View fixedFooterView = mFixedFooterViewId > 0 ? thisView.findViewById(mFixedFooterViewId) : null;

            mRefreshContent.setScrollBoundaryDecider(mScrollBoundaryDecider);
            mRefreshContent.setEnableLoadMoreWhenContentNotFull(mEnableLoadMoreWhenContentNotFull);
            mRefreshContent.setUpComponent(mKernel, fixedHeaderView, fixedFooterView);
        }

        thisView.setRotation(-90);
    }


    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = right - left;
        int height = bottom - top;
        int div = (height - width) / 2;
        if (isInLayout) {
            RefreshInternal header = mRefreshHeader;
            RefreshInternal footer = mRefreshFooter;

            final View thisView = this;
            int paddingLeft = thisView.getPaddingLeft();
            int paddingRight = thisView.getPaddingRight();
            int paddingTop = thisView.getPaddingTop();
            int paddingBottom = thisView.getPaddingBottom();

            for (int i = 0, len = getChildCount(); i < len; i++) {
                View child = getChildAt(i);
                if ((header == null || child != header.getView()) && (footer == null || child != footer.getView())) {
                    if (child.getVisibility() != GONE) {

                        int w = height;
                        int h = width;
                        int l = paddingBottom;
                        int t = paddingLeft;

                        h -= paddingTop + paddingBottom;
                        w -= paddingLeft + paddingRight;

                        ViewGroup.LayoutParams params = child.getLayoutParams();
                        if (params instanceof ViewGroup.MarginLayoutParams) {
                            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) params;
                            h -= lp.topMargin + lp.bottomMargin;
                            w -= lp.leftMargin + lp.rightMargin;
                            l += lp.bottomMargin;
                            t += lp.leftMargin;
                        }

                        div = (h - w) / 2;
                        l += div;
                        t -= div;

//                        child.setTag(R.string.srl_tag, "GONE");
                        child.setRotation(90);
                        child.setTag(R.string.srl_component_falsify, child);
                        child.measure(View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY));
                        child.layout(l, t, l + w, t + h);
                    }
                }
            }
            super.onLayout(changed, left, top, right, bottom);
        } else {
            top -= div;
            left += div;
            isInLayout = true;
            super.layout(left, top, left + width, top + height);
            isInLayout = false;
        }

    }
    //</editor-fold>

    @SuppressWarnings("WeakerAccess")
    protected static class DefaultHorizontalInitializer implements DefaultRefreshInitializer {
        protected DefaultRefreshInitializer initializer;
        protected DefaultRefreshInitializer oldInitializer;

        protected DefaultHorizontalInitializer(DefaultRefreshInitializer initializer, DefaultRefreshInitializer old) {
            this.oldInitializer = old;
            this.initializer = initializer;
        }

        @Override
        public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
            layout.setEnableLoadMore(true);
            if (initializer != null) {
                initializer.initialize(context, layout);
            }
            if (oldInitializer != null) {
                SmartRefreshLayout.setDefaultRefreshInitializer(oldInitializer);
            }
        }
    }
}
