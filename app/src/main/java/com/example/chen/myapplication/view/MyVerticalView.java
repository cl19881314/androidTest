package com.example.chen.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        int childWidth = 0;
        int childHeight = 0;
        int parentHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == GONE){
                continue;
            }
            MarginLayoutParams params = (MarginLayoutParams) childAt.getLayoutParams();
            int w = childAt.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int h = childAt.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            if (childWidth < w) {
                childWidth = w;
            }
            if (childHeight < h) {
                childHeight = h;
            }
            parentHeight += h;
        }
        //开始处理wrap_content,如果一个子元素都没有，就设置为0
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //ViewGroup，宽，高都是wrap_content，根据我们的需求，宽度是子控件的宽度，高度则是所有子控件的总和
            setMeasuredDimension(childWidth, parentHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //ViewGroup的宽度为wrap_content,则高度不需要管，宽度还是自控件的宽度
            setMeasuredDimension(childWidth, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //ViewGroup的高度为wrap_content,则宽度不需要管，高度为子View的高度和
            setMeasuredDimension(widthSize, parentHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = 0;
        int count = getChildCount();
        View child;
        MarginLayoutParams params;
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child.getVisibility() == GONE){
                continue;
            }
            params = (MarginLayoutParams) child.getLayoutParams();
            int left = params.leftMargin;
            int top = params.topMargin + height;
            int right = left + child.getMeasuredWidth();
            int bottom = top + child.getMeasuredHeight();
            child.layout(left, top, right, bottom);
            height += child.getMeasuredHeight();
        }
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

}

