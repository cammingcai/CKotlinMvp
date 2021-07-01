package com.camming.mvp.ui.widget.recyclerview.smartrefresh_horizontal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;


import com.camming.mvp.R;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.internal.InternalClassics;

@Deprecated
public class SimpleTextFooter extends InternalClassics<ClassicsFooter> implements RefreshFooter {

    protected TextView mTitleText;
    protected ImageView mArrowView;
    protected ImageView mProgressView;

    public SimpleTextFooter(Context context) {
        this(context, null);
    }

    public SimpleTextFooter(Context context, AttributeSet attrs) {
        super(context, attrs, 0);

        View.inflate(context, R.layout.simple_text_footer, this);

        final View thisView = this;
        final View arrowView = mArrowView = thisView.findViewById(R.id.srl_classics_arrow);
        final View progressView = mProgressView = thisView.findViewById(R.id.srl_classics_progress);
        mTitleText = thisView.findViewById(R.id.srl_classics_title);

        progressView.animate().setInterpolator(null);
        mArrowView.setVisibility(GONE);
        progressView.setVisibility(GONE);
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        super.onStartAnimator(refreshLayout, height, maxDragHeight);
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        return super.onFinish(layout, success);
    }

    /**
     * ClassicsFooter 在(SpinnerStyle.FixedBehind)时才有主题色
     */
    @Override
    @Deprecated
    public void setPrimaryColors(@ColorInt int ... colors) {
        if (mSpinnerStyle == SpinnerStyle.FixedBehind) {
            super.setPrimaryColors(colors);
        }
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return true;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        mTitleText.setText("释放查看更多");
    }

}
