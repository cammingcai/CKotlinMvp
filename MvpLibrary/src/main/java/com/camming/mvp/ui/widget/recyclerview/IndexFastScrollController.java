package com.camming.mvp.ui.widget.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.SectionIndexer;

import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class IndexFastScrollController extends RecyclerView.AdapterDataObserver {

    private int setIndexTextSize = 12;
    private float mIndexbarWidth = 20;
    private float mIndexbarMargin = 5;
    private float mPreviewPadding = 5f;
    private float mIndexBarTransparentValue = (float) 0.6;
    private float mPreviewTransparentValue = (float) 0.4;

    private float mDensity;
    private float mScaledDensity;
    private int mListViewWidth;
    private int mListViewHeight;
    private int mCurrentSection = -1;
    private boolean mIsIndexing = false;
    private RecyclerView mRecyclerView = null;
    private ViewGroup mRVViewGroup = null;
    private SectionIndexer mIndexer = null;
    private String[] mSections = null;
    private RectF mIndexbarRect;

    private float setIndexbarWidth = 20;
    private float setIndexbarMargin = 5;
    private float setPreviewPadding = 5;
    private boolean previewVisibility = true;
    private int setIndexBarCornerRadius = 5;
    private Typeface setTypeface = null;
    private Boolean setIndexBarVisibility = true;
    private Boolean setSetIndexBarHighLateTextVisibility = false;
    private @ColorInt int indexbarBackgroudColor = Color.BLACK;
    private @ColorInt int indexbarTextColor = Color.WHITE;
    private @ColorInt int indexbarHighLateTextColor = Color.BLACK;

    private int setPreviewTextSize = 50;
    private @ColorInt int previewBackgroundColor = Color.BLACK;
    private @ColorInt int previewTextColor = Color.WHITE;
    private int previewBackgroudAlpha;
    private int indexbarBackgroudAlpha;

    private int mRecyclerViewHeight = 0;

    public IndexFastScrollController(Context context, ViewGroup viewGroup, RecyclerView rv, RecyclerView.Adapter adapter) {

        previewBackgroudAlpha = convertTransparentValueToBackgroundAlpha(mPreviewTransparentValue);
        indexbarBackgroudAlpha = convertTransparentValueToBackgroundAlpha(mIndexBarTransparentValue);

        mDensity = context.getResources().getDisplayMetrics().density;
        mScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        mRecyclerView = rv;
        mRVViewGroup = viewGroup;
        setAdapter(adapter);

        mIndexbarWidth = setIndexbarWidth * mDensity;
        mIndexbarMargin = setIndexbarMargin * mDensity;
        mPreviewPadding = setPreviewPadding * mDensity;
    }

    public void draw(Canvas canvas) {

        if (setIndexBarVisibility) {
            if (mRecyclerViewHeight == 0){
                mRecyclerViewHeight = mRecyclerView.getHeight();
            }

            Paint indexbarPaint = new Paint();
            indexbarPaint.setColor(indexbarBackgroudColor);
            indexbarPaint.setAlpha(indexbarBackgroudAlpha);
            indexbarPaint.setAntiAlias(true);
            canvas.drawRoundRect(mIndexbarRect, setIndexBarCornerRadius * mDensity, setIndexBarCornerRadius * mDensity, indexbarPaint);

            if (mSections != null && mSections.length > 0) {
                // Preview is shown when mCurrentSection is set
                if (previewVisibility && mCurrentSection >= 0 && mSections[mCurrentSection] != "") {
                    Paint previewPaint = new Paint();
                    previewPaint.setColor(previewBackgroundColor);
                    previewPaint.setAlpha(previewBackgroudAlpha);
                    previewPaint.setAntiAlias(true);
                    previewPaint.setShadowLayer(3, 0, 0, Color.argb(64, 0, 0, 0));

                    Paint previewTextPaint = new Paint();
                    previewTextPaint.setColor(previewTextColor);
                    previewTextPaint.setAntiAlias(true);
                    previewTextPaint.setTextSize(setPreviewTextSize * mScaledDensity);
                    previewTextPaint.setTypeface(setTypeface);

                    float previewTextWidth = previewTextPaint.measureText(mSections[mCurrentSection]);
                    float previewSize = 2 * mPreviewPadding + previewTextPaint.descent() - previewTextPaint.ascent();
                    previewSize = Math.max(previewSize, previewTextWidth + 2 * mPreviewPadding);
                    RectF previewRect = new RectF((mListViewWidth - previewSize) / 2
                            , (mRecyclerViewHeight - previewSize) / 2
                            , (mListViewWidth - previewSize) / 2 + previewSize
                            , (mRecyclerViewHeight - previewSize) / 2 + previewSize);

                    canvas.drawRoundRect(previewRect, 5 * mDensity, 5 * mDensity, previewPaint);
                    canvas.drawText(mSections[mCurrentSection], previewRect.left + (previewSize - previewTextWidth) / 2 - 1
                            , previewRect.top + (previewSize - (previewTextPaint.descent() - previewTextPaint.ascent())) / 2 - previewTextPaint.ascent(), previewTextPaint);
                    fade(300);
                }

                Paint indexPaint = new Paint();
                indexPaint.setColor(indexbarTextColor);
                indexPaint.setAntiAlias(true);
                indexPaint.setTextSize(setIndexTextSize * mScaledDensity);
                indexPaint.setTypeface(setTypeface);

                float sectionHeight = (mIndexbarRect.height() - 2 * mIndexbarMargin) / mSections.length;
//                float sectionHeight = 30;
                float paddingTop = (sectionHeight - (indexPaint.descent() - indexPaint.ascent())) / 2;
                for (int i = 0; i < mSections.length; i++) {

                    if (setSetIndexBarHighLateTextVisibility) {

                        if (mCurrentSection > -1 && i == mCurrentSection) {
                            indexPaint.setTypeface(Typeface.create(setTypeface, Typeface.BOLD));
                            indexPaint.setTextSize((setIndexTextSize + 3) * mScaledDensity);
                            indexPaint.setColor(indexbarHighLateTextColor);
                        } else {
                            indexPaint.setTypeface(setTypeface);
                            indexPaint.setTextSize(setIndexTextSize * mScaledDensity);
                            indexPaint.setColor(indexbarTextColor);
                        }
                        float paddingLeft = (mIndexbarWidth - indexPaint.measureText(mSections[i])) / 2;
                        canvas.drawText(mSections[i], mIndexbarRect.left + paddingLeft
                                , mIndexbarRect.top + mIndexbarMargin + sectionHeight * i + paddingTop - indexPaint.ascent(), indexPaint);


                    } else {
                        float paddingLeft = (mIndexbarWidth - indexPaint.measureText(mSections[i])) / 2;
                        canvas.drawText(mSections[i], mIndexbarRect.left + paddingLeft
                                , mIndexbarRect.top + mIndexbarMargin + sectionHeight * i + paddingTop - indexPaint.ascent(), indexPaint);
                    }

                }
            }
        }

    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // If down event occurs inside index bar region, start indexing
                if (contains(ev.getX(), ev.getY())) {

                    // It demonstrates that the motion event started from index bar
                    mIsIndexing = true;
                    // Determine which section the point is in, and move the list to that section
                    mCurrentSection = getSectionByPoint(ev.getY());
                    scrollToPosition();
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsIndexing) {
                    // If this event moves inside index bar
                    if (contains(ev.getX(), ev.getY())) {
                        // Determine which section the point is in, and move the list to that section
                        mCurrentSection = getSectionByPoint(ev.getY());
                        scrollToPosition();
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsIndexing) {
                    mIsIndexing = false;
                    mCurrentSection = -1;
                }
                break;
        }
        return false;
    }

    private void scrollToPosition() {
        try {
            int position = mIndexer.getPositionForSection(mCurrentSection);
            RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(position, 0);
            } else {
                layoutManager.scrollToPosition(position);
            }
        } catch (Exception e) {
            Log.d("INDEX_BAR", "Data size returns null");
        }
    }

    private int mViewHeight = 0;
    private int mViewWidth = 0;

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewHeight = h;
        mViewWidth =w;
        mListViewWidth = w;
        int  margin= 0;
        if (mSections == null || mSections.length == 0 ){
            mListViewHeight = h;
        }else {
            mListViewHeight = (int)(mSections.length * mDensity*16);
            margin =( h - mListViewHeight)/2;
        }

        mIndexbarRect = new RectF(w - mIndexbarMargin - mIndexbarWidth
                , mIndexbarMargin + margin
                , w - mIndexbarMargin
                , mListViewHeight - mIndexbarMargin +margin);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter instanceof SectionIndexer) {
            adapter.registerAdapterDataObserver(this);
            mIndexer = (SectionIndexer) adapter;
            mSections = (String[]) mIndexer.getSections();
        }
    }

    @Override
    public void onChanged() {
        super.onChanged();
        updateSections();
        if (mSections!=null && mSections.length>0){
            mListViewHeight = (int)(mSections.length * mDensity*16);
            int margin =( mViewHeight - mListViewHeight)/2;
            mIndexbarRect = new RectF(mViewWidth - mIndexbarMargin - mIndexbarWidth
                    , mIndexbarMargin + margin
                    , mViewWidth - mIndexbarMargin
                    , mListViewHeight - mIndexbarMargin +margin);
        }
    }

    public void updateSections() {
        mSections = (String[]) mIndexer.getSections();
    }

    public boolean contains(float x, float y) {
        // Determine if the point is in index bar region, which includes the right margin of the bar
        return (x >= mIndexbarRect.left && y >= mIndexbarRect.top && y <= mIndexbarRect.top + mIndexbarRect.height());
    }

    private int getSectionByPoint(float y) {
        if (mSections == null || mSections.length == 0)
            return 0;
        if (y < mIndexbarRect.top + mIndexbarMargin)
            return 0;
        if (y >= mIndexbarRect.top + mIndexbarRect.height() - mIndexbarMargin)
            return mSections.length - 1;
        return (int) ((y - mIndexbarRect.top - mIndexbarMargin) / ((mIndexbarRect.height() - 2 * mIndexbarMargin) / mSections.length));
    }

    private Runnable mLastFadeRunnable = null;

    private void fade(long delay) {
        if (mRecyclerView != null) {
            if (mLastFadeRunnable != null) {
                mRecyclerView.removeCallbacks(mLastFadeRunnable);
            }
            mLastFadeRunnable = new Runnable() {
                @Override
                public void run() {
//                    mRecyclerView.invalidate();
                    mRVViewGroup.invalidate();
                }
            };
            mRecyclerView.postDelayed(mLastFadeRunnable, delay);
        }
    }

    private int convertTransparentValueToBackgroundAlpha(float value) {
        return (int) (255 * value);
    }

    public IndexFastScrollController addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mRecyclerView.addItemDecoration(itemDecoration);
        return this;
    }

    /**
     * @param value int to set the text size of the index bar
     */
    public IndexFastScrollController setIndexTextSize(int value) {
        setIndexTextSize = value;
        return this;
    }

    /**
     * @param value float to set the width of the index bar
     */
    public IndexFastScrollController setIndexbarWidth(float value) {
        mIndexbarWidth = value;
        return this;
    }

    /**
     * @param value float to set the margin of the index bar
     */
    public IndexFastScrollController setIndexbarMargin(float value) {
        mIndexbarMargin = value;
        return this;
    }

    /**
     * @param value int to set preview padding
     */
    public IndexFastScrollController setPreviewPadding(int value) {
        setPreviewPadding = value;
        return this;
    }

    /**
     * @param value int to set the radius of the index bar
     */
    public IndexFastScrollController setIndexBarCornerRadius(int value) {
        setIndexBarCornerRadius = value;
        return this;
    }

    /**
     * @param value float to set the transparency of the color for index bar
     */
    public IndexFastScrollController setIndexBarTransparentValue(float value) {
        indexbarBackgroudAlpha = convertTransparentValueToBackgroundAlpha(value);
        return this;
    }

    /**
     * @param typeface Typeface to set the typeface of the preview & the index bar
     */
    public IndexFastScrollController setTypeface(Typeface typeface) {
        setTypeface = typeface;
        return this;
    }

    /**
     * @param shown boolean to show or hide the index bar
     */
    public IndexFastScrollController setIndexBarVisibility(boolean shown) {
        setIndexBarVisibility = shown;
        return this;
    }

    /**
     * @param shown boolean to show or hide the preview box
     */
    public IndexFastScrollController setPreviewVisibility(boolean shown) {
        previewVisibility = shown;
        return this;
    }

    /**
     * @param value int to set the text size of the preview box
     */
    public IndexFastScrollController setPreviewTextSize(int value) {
        setPreviewTextSize = value;
        return this;
    }

    /**
     * @param color The color for the preview box
     */
    public IndexFastScrollController setPreviewColor(@ColorInt int color) {
        previewBackgroundColor = color;
        return this;
    }

    /**
     * @param color The text color for the preview box
     */
    public IndexFastScrollController setPreviewTextColor(@ColorInt int color) {
        previewTextColor = color;
        return this;
    }

    /**
     * @param value float to set the transparency value of the preview box
     */
    public IndexFastScrollController setPreviewTransparentValue(float value) {
        previewBackgroudAlpha = convertTransparentValueToBackgroundAlpha(value);
        return this;
    }

    /**
     * @param color The color for the scroll track
     */
    public IndexFastScrollController setIndexBarColor(@ColorInt int color) {
        indexbarBackgroudColor = color;
        return this;
    }

    /**
     * @param color The text color for the index bar
     */
    public IndexFastScrollController setIndexBarTextColor(@ColorInt int color) {
        indexbarTextColor = color;
        return this;
    }

    /**
     * @param color The text color for the index bar
     */
    public IndexFastScrollController setIndexBarHighLateTextColor(@ColorInt int color) {
        indexbarHighLateTextColor = color;
        return this;
    }

    /**
     * @param shown boolean to show or hide the index bar
     */
    public IndexFastScrollController setIndexBarHighLateTextVisibility(boolean shown) {
        setSetIndexBarHighLateTextVisibility = shown;
        return this;
    }

}
