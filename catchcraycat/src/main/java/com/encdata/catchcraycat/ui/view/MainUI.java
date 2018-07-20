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

import com.socks.library.KLog;

/**
 * 自定义可以左右滑动-带有动画-带有渐变效果的布局
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

    /**
     * 1.初始化资源数据
     * ->创建4个布局、设置布局的资源id(供其他界面使用比如其他界面需要在其上面添加控件或者依赖)、设置中间透明遮罩的透明度
     * ->创建Scroller(提供界面滚动效果)
     * ->添加子布局到自定义布局界面
     *
     * @param context
     */
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

    /**
     * 2.重写测量方法，设置绘制控件的宽高属性。每个控件都需要设置
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
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


    /**
     * 3.布局定位方法，设置控件放置位置
     * 在onLayout方法中对子View，viewChild.layout();布局定位
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        midlMenu.layout(l, t, r, b);
        midlMask.layout(l, t, r, b);
        leftMenu.layout(l - leftMenu.getMeasuredWidth(), t, r, b);
        rightMenu.layout(l + midlMenu.getMeasuredWidth(), t, l + midlMenu.getMeasuredWidth() + rightMenu.getMeasuredWidth(), b);
    }

    private boolean isTestCompete;//是否是移动事件
    private boolean isLeftRightEvent;//是否是左右滑动事件

    /**
     * 4.事件分发方法，提供控件手势监听
     * =>判断上次动作的事件类型(通过上次UP、Cancel动作设置false，通过上次MOVE动作设置true)：
     * 如果不是移动事件：再次记录动作的类型和动作结束后的点的位置(通过DOWN和MOVE动作记录，方便下次判断)
     * 如果是移动事件
     * =>判断是否是左右移动事件
     * 如果是：判断动作类型
     *                  ->如果是MOVE动作->判断移动方向并计算移动距离执行scrollTo方法进行移动
     *                    如果是UP或者CANCEL动作->判断移动方向利用Scroller执行滚动动画并执行重绘方法
     * 如果不是：重置是否滑动和是否左右滑动标识
     *
     * 问题疑点？？？
     * dis_x定义、在156行判断滑动距离是否超过左右布局的一半。
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isTestCompete) {
            getEventType(ev);
            return true;
        }
        if (isLeftRightEvent) {
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:
                    int curScrollX = getScrollX();//滚动距离->向左移动值为正，向右移动值为负
                    int dis_x = (int) (ev.getX() - point.x);//滑动距离
                    KLog.d("curScrollX=" + curScrollX+"____dis_x="+dis_x);
                    int expectX = -dis_x + curScrollX;
                    int finalX = 0;//手指滑动的差距
                    if (expectX < 0) {//向左滑动
                        KLog.d("expectX="+expectX+"____leftMenu="+-leftMenu.getMeasuredWidth());
                        finalX = Math.max(expectX, -leftMenu.getMeasuredWidth());
                    } else {//向右滑动
                        KLog.d("expectX="+expectX+"____rightMenu="+rightMenu.getMeasuredWidth());
                        finalX = Math.min(expectX, rightMenu.getMeasuredWidth());
                    }
                    KLog.d("finalX=" + finalX);
//                    KLog.d("curScrollX=" + curScrollX);
                    scrollTo(expectX, 0);
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

    //滚动监听方法，通过滑动距离和控件宽度的比例来设置主布局的透明度
    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        int curX = Math.abs(getScrollX());
        float scale = curX / (float) leftMenu.getMeasuredWidth();
        midlMask.setAlpha(scale);
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
                KLog.d("ACTION_DOWN");
                super.dispatchTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) Math.abs(ev.getX() - point.x);//移动结束的点的坐标减去按下的点的坐标
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
                KLog.d("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                KLog.d("ACTION_DOWN_CANCEL");
                super.dispatchTouchEvent(ev);
                isLeftRightEvent = false;
                isTestCompete = false;
                break;
        }
    }
}
