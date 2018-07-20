package com.encdata.catchcraycat.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by zhaoyuejun on 2018/7/20.
 */

public class MainUI extends RelativeLayout {

    private Context context;
    private FrameLayout leftMenu;
    private FrameLayout midlMenu;
    private FrameLayout rightMenu;
    private FrameLayout midlMask;
    private Scroller mScroller;
    public static final int LEFT_ID = 0xaabbcc;
    public static final int MIDDLE_ID = 0xbbaacc;
    public static final int RIGHT_ID = 0xccaabb;

    public MainUI(Context context) {
        super(context);
        initView(context);
    }

    public MainUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        mScroller = new Scroller(context, new DecelerateInterpolator());
        leftMenu = new FrameLayout(context);
        midlMenu = new FrameLayout(context);
        rightMenu = new FrameLayout(context);
        midlMask = new FrameLayout(context);
        leftMenu.setBackgroundColor(Color.RED);
        midlMenu.setBackgroundColor(Color.GREEN);
        rightMenu.setBackgroundColor(Color.RED);
        midlMask.setBackgroundColor(0x88000000);
        leftMenu.setId(LEFT_ID);
        midlMenu.setId(MIDDLE_ID);
        rightMenu.setId(RIGHT_ID);
        addView(leftMenu);
        addView(midlMenu);
        addView(rightMenu);
        addView(midlMask);
        midlMask.setAlpha(0);
    }

    //滚动监听方法
    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        int curX = Math.abs(getScrollX());
        float scale = curX / (float) leftMenu.getMeasuredWidth();
        midlMask.setAlpha(scale);
    }

    //测量方法，提供控件的宽高设置
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        midlMenu.measure(widthMeasureSpec, heightMeasureSpec);
        midlMask.measure(widthMeasureSpec, heightMeasureSpec);
        int realWidth = MeasureSpec.getSize(widthMeasureSpec);
        int tempWidthMeasure = MeasureSpec.makeMeasureSpec((int) (realWidth * 0.8f), MeasureSpec.EXACTLY);
        leftMenu.measure(tempWidthMeasure, heightMeasureSpec);
        rightMenu.measure(tempWidthMeasure, heightMeasureSpec);
    }

    //布局方法，提供控件放置位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        midlMenu.layout(l, t, r, b);
        midlMask.layout(l, t, r, b);
        leftMenu.layout(l - leftMenu.getMeasuredWidth(), t, r, b);
        rightMenu.layout(l + midlMenu.getMeasuredWidth(), t, l + midlMenu.getMeasuredWidth() + rightMenu.getMeasuredWidth(), b);
    }

    private boolean isTestCompete;//是一个怎样的事件，true代表移动事件
    private boolean isLeftRightEvent;

    //事件分发方法，提供控件手势监听
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isTestCompete) {
            getEventType(ev);
            return true;
        }
        if (isLeftRightEvent) {
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:
                    int curScrollX = getScrollX();//滚动距离
                    int dis_x = (int) (ev.getX() - point.x);//滑动距离
                    int expectX = -dis_x + curScrollX;
                    int finalX = 0;
                    if (expectX < 0) {
                        finalX = Math.max(expectX, -leftMenu.getMeasuredWidth());
                    } else {
                        finalX = Math.min(expectX, rightMenu.getMeasuredWidth());
                    }
                    scrollTo(finalX, 0);
                    point.x = (int) ev.getX();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    curScrollX = getScrollX();//滚动距离
                    if (Math.abs(curScrollX) > leftMenu.getMeasuredWidth() >> 1) {
                        if (curScrollX < 0) {
                            mScroller.startScroll(curScrollX, 0, -leftMenu.getMeasuredWidth() - curScrollX, 0, 200);
                        } else {
                            mScroller.startScroll(curScrollX, 0, leftMenu.getMeasuredWidth() - curScrollX, 0, 200);
                        }
                    } else {
                        mScroller.startScroll(curScrollX, 0, -curScrollX, 0);
                    }
                    invalidate();
                    isLeftRightEvent = false;
                    isTestCompete = false;
                    break;
            }
        } else {
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_UP:
                    isLeftRightEvent = false;
                    isTestCompete = false;
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    //通过invalidate操纵，此方法通过draw方法调用。配合mScroller使用
    //重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (!mScroller.computeScrollOffset()) {
            return;
        }
        int tempX = mScroller.getCurrX();
        scrollTo(tempX, 0);
    }

    private Point point = new Point();//上一次点击或者移动结束的点的位置
    private static final int TEST_DIS = 20;

    public void getEventType(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                point.x = (int) ev.getX();
                point.y = (int) ev.getY();
                super.dispatchTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) Math.abs(ev.getX() - point.x);
                int dy = (int) Math.abs(ev.getY() - point.y);
                if (dx >= TEST_DIS && dx > dy) {//左右滑动
                    isLeftRightEvent = true;
                    isTestCompete = true;
                    point.x = (int) ev.getX();
                    point.y = (int) ev.getY();
                } else if (dy >= TEST_DIS && dy > dx) {//上下滑动
                    isLeftRightEvent = false;
                    isTestCompete = true;
                    point.x = (int) ev.getX();
                    point.y = (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                super.dispatchTouchEvent(ev);
                isLeftRightEvent = false;
                isTestCompete = false;
                break;
        }
    }
}
