package com.hellorobotedu.aiparent.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.core.widget.NestedScrollView;

/**
 * @Created SiberiaDante
 * @Describe：
 * @CreateTime: 2017/12/17
 * @UpDateTime:
 * @Email: 2654828081@qq.com
 * @GitHub: https://github.com/SiberiaDante
 */

public class JudgeNestedScrollView extends NestedScrollView {
    private boolean isNeedScroll = true;
    private float xDistance, yDistance, xLast, yLast;
    private int scaledTouchSlop;

    public JudgeNestedScrollView(Context context) {
        super(context, null);
    }

    public JudgeNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public JudgeNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                boolean b=!(xDistance >= yDistance || yDistance < scaledTouchSlop) && isNeedScroll;
                return b;

        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 是否消耗事件
     * @param isNeedScroll
     */
    public void setNeedScroll(boolean isNeedScroll) {
        this.isNeedScroll = isNeedScroll;
    }

    /*****************事件拦截end**********************************/

    private Callbacks mCallbacks;
    private TopOrBottomCallbacks topOrBottomCallbacks;
    private ScrollModel scrollModel;

    public void setCallbacks(Callbacks listener) {
        mCallbacks = listener;
    }
    public void setTopOrBottomCallbacks(TopOrBottomCallbacks s){
        topOrBottomCallbacks=s;
    }
    public void setScrollModel(ScrollModel s){
        scrollModel=s;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mCallbacks != null) {
            mCallbacks.onScrollChanged();
        }
        int schedule=getScrollY()+getHeight()-getPaddingTop()-getPaddingBottom();
        if((schedule==getChildAt(0).getHeight())&&topOrBottomCallbacks!=null){
            topOrBottomCallbacks.onScrolledToBottom();
        }
        if(schedule==getHeight()&&topOrBottomCallbacks!=null){
            topOrBottomCallbacks.onScrolledToTop();
        }
        if(t>oldt&&scrollModel!=null){
            scrollModel.scrollDown(t,oldt);
        }
        if(t<oldt&&scrollModel!=null){
            scrollModel.scrollUp(t,oldt);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int scrollY = getScrollY();
        // hack to call onScrollChanged on screen rotate
        if (scrollY > 0 && mCallbacks != null) {
            mCallbacks.onScrollChanged();
        }
    }

    public static interface Callbacks {
        public void onScrollChanged();
    }
    public interface TopOrBottomCallbacks{
        public void onScrolledToTop();
        public void onScrolledToBottom();
    }
    public interface ScrollModel{
        public void scrollDown(int t, int oldt);
        public void scrollUp(int t, int oldt);
    }


}
