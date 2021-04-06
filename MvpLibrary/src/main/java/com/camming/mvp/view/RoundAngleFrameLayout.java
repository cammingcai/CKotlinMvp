package com.camming.mvp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.camming.mvp.R;


public class RoundAngleFrameLayout extends FrameLayout {

    private float topLeftRadius;
    private float topRightRadius;
    private float bottomLeftRadius;
    private float bottomRightRadius;

    private Paint roundPaint;
    private Paint imagePaint;
    private Paint borderPaint;

    private boolean isShowBorder = false;
    private int borderColor = Color.parseColor("#d6d6d6");

    public RoundAngleFrameLayout(Context context) {
        this(context, null);
    }

    public RoundAngleFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundAngleFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FlashRelativeLayout);
            float radius = ta.getDimension(R.styleable.FlashRelativeLayout_radius, 0);
            topLeftRadius = ta.getDimension(R.styleable.FlashRelativeLayout_topLeftRadius, radius);
            topRightRadius = ta.getDimension(R.styleable.FlashRelativeLayout_topRightRadius, radius);
            bottomLeftRadius = ta.getDimension(R.styleable.FlashRelativeLayout_bottomLeftRadius, radius);
            bottomRightRadius = ta.getDimension(R.styleable.FlashRelativeLayout_bottomRightRadius, radius);
            isShowBorder = ta.getBoolean(R.styleable.FlashRelativeLayout_isShowBorder, false);
            borderColor = ta.getInteger(R.styleable.FlashRelativeLayout_borderColor,borderColor);
            ta.recycle();
        }
        roundPaint = new Paint();
        roundPaint.setColor(Color.WHITE);
        roundPaint.setAntiAlias(true);
        roundPaint.setStyle(Paint.Style.FILL);
        roundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        borderPaint = new Paint();
        borderPaint.setColor(borderColor);

        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeWidth(1);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));

        imagePaint = new Paint();
        imagePaint.setXfermode(null);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), imagePaint, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);

        drawBorder(canvas);
        drawTopLeft(canvas);
        drawTopRight(canvas);
        drawBottomLeft(canvas);
        drawBottomRight(canvas);

        canvas.restore();
    }

    public void showBorder() {
        isShowBorder = true;
        invalidate();
    }

    public void hideBorder() {
        isShowBorder = false;
        invalidate();
    }

    private void drawBorder(Canvas canvas) {
        if (isShowBorder) {
            canvas.drawLine(topLeftRadius, 0, getWidth() - topRightRadius, 0, borderPaint);
            canvas.drawLine(bottomLeftRadius, getHeight(), getWidth() - bottomRightRadius, getHeight(), borderPaint);
            canvas.drawLine(0, topLeftRadius, 0, getHeight() - bottomLeftRadius, borderPaint);
            canvas.drawLine(getWidth(), topRightRadius, getWidth(), getHeight() - bottomRightRadius, borderPaint);
        }
    }

    private void drawTopLeft(Canvas canvas) {
        if (topLeftRadius > 0) {
            Path path = new Path();
            path.moveTo(0, topLeftRadius);
            path.lineTo(0, 0);
            path.lineTo(topLeftRadius, 0);
            path.arcTo(new RectF(0, 0, topLeftRadius * 2, topLeftRadius * 2), -90, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
            if (isShowBorder) {
                Path path1 = new Path();
                path1.arcTo(new RectF(0, 0, topLeftRadius * 2, topLeftRadius * 2), -90, -90);
                canvas.drawPath(path1, borderPaint);
            }
        }
    }

    private void drawTopRight(Canvas canvas) {
        if (topRightRadius > 0) {
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - topRightRadius, 0);
            path.lineTo(width, 0);
            path.lineTo(width, topRightRadius);
            path.arcTo(new RectF(width - 2 * topRightRadius, 0, width, topRightRadius * 2), 0, -90);
            path.close();
            canvas.drawPath(path, roundPaint);
            if (isShowBorder) {
                Path path1 = new Path();
                path1.arcTo(new RectF(width - 2 * topRightRadius, 0, width, topRightRadius * 2), 0, -90);
                canvas.drawPath(path1, borderPaint);
            }
        }
    }

    private void drawBottomLeft(Canvas canvas) {
        if (bottomLeftRadius > 0) {
            int height = getHeight();
            Path path = new Path();
            path.moveTo(0, height - bottomLeftRadius);
            path.lineTo(0, height);
            path.lineTo(bottomLeftRadius, height);
            path.arcTo(new RectF(0, height - 2 * bottomLeftRadius, bottomLeftRadius * 2, height), 90, 90);
            path.close();
            canvas.drawPath(path, roundPaint);
            if (isShowBorder) {
                Path path1 = new Path();
                path1.arcTo(new RectF(0, height - 2 * bottomLeftRadius, bottomLeftRadius * 2, height), 90, 90);
                canvas.drawPath(path1, borderPaint);
            }
        }
    }

    private void drawBottomRight(Canvas canvas) {
        if (bottomRightRadius > 0) {
            int height = getHeight();
            int width = getWidth();
            Path path = new Path();
            path.moveTo(width - bottomRightRadius, height);
            path.lineTo(width, height);
            path.lineTo(width, height - bottomRightRadius);
            path.arcTo(new RectF(width - 2 * bottomRightRadius, height - 2 * bottomRightRadius, width, height), 0, 90);
            path.close();
            canvas.drawPath(path, roundPaint);
            if (isShowBorder) {
                Path path1 = new Path();
                path1.arcTo(new RectF(width - 2 * bottomRightRadius, height - 2 * bottomRightRadius, width, height), 0, 90);
                canvas.drawPath(path1, borderPaint);
            }
        }
    }
}
