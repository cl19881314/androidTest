package com.example.chen.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chen on 2018-03-06.
 */

public class MyVerticalView extends ViewGroup {
    public MyVerticalView(Context context) {
        this(context, null);
    }

    public MyVerticalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVerticalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
////        measureChildren(widthMeasureSpec, heightMeasureSpec);
//        // 计算所有child view 要占用的空间
//        int desireWidth = 0;
//        int desireHeight = 0;
//        int count = getChildCount();
//        for (int i = 0; i < count; ++i) {
//            View v = getChildAt(i);
//            if (v.getVisibility() != View.GONE) {
//                LayoutParams lp = (LayoutParams) v.getLayoutParams();
//                measureChildWithMargins(v, widthMeasureSpec, 0,
//                        heightMeasureSpec, 0);
//
//                    desireWidth = Math.max(desireWidth, v.getMeasuredWidth()
//                            + lp.leftMargin + lp.rightMargin);
//                    desireHeight += v.getMeasuredHeight() + lp.topMargin
//                            + lp.bottomMargin;
//            }
//        }
//
//        // count with padding
//        desireWidth += getPaddingLeft() + getPaddingRight();
//        desireHeight += getPaddingTop() + getPaddingBottom();
//
//        // see if the size is big enough
//        desireWidth = Math.max(desireWidth, getSuggestedMinimumWidth());
//        desireHeight = Math.max(desireHeight, getSuggestedMinimumHeight());
//
//        setMeasuredDimension(resolveSize(desireWidth, widthMeasureSpec),
//                resolveSize(desireHeight, heightMeasureSpec));
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        int count = getChildCount();
//        int childMeasureWidth = 0;
//        int childMeasureHeight = 0;
//        int height = top + getPaddingTop();
//        View child;
//        for (int i = 0; i < count; i++) {
//            child = getChildAt(i);
//            if (child.getVisibility() != GONE) {
//                childMeasureWidth = child.getMeasuredWidth();
//                childMeasureHeight = child.getMeasuredHeight();
//                right = left + childMeasureWidth;
//                top = height;
//                bottom = height + childMeasureHeight;
//                child.layout(left+getPaddingLeft(), top, right, bottom);
//                height = bottom;
//            }
//        }
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getPaddingLeft();
        int height = getPaddingTop();
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            width = getPaddingLeft();
            lp.x = width;
            lp.y = height;
            width += child.getMeasuredWidth();
        }
        width += getPaddingRight();
        height += getChildAt(getChildCount() - 1).getMeasuredHeight() + getPaddingBottom();
        Log.d("gnt","----");
        setMeasuredDimension(resolveSize(width, widthMeasureSpec),resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int height = t;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            Log.d("gnt","--4444-");
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            child.layout(lp.x, height, lp.x + child.getMeasuredWidth(), height + lp.y + child.getMeasuredHeight());
            height =  lp.y + child.getMeasuredHeight();
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p.width, p.height);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        int x;
        int y;
        public int verticalSpacing;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

    }
}

