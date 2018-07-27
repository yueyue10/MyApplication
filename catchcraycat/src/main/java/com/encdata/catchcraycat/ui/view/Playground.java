package com.encdata.catchcraycat.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.encdata.catchcraycat.ui.bean.Dot;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Vector;

/**
 * 自定义游戏面板布局
 * Created by zhaoyuejun on 2018/7/19.
 */

public class Playground extends SurfaceView implements View.OnTouchListener {

    private static int WIDTH = 40;
    private static final int COL = 10;//列
    private static final int ROW = 10;//行
    private static final int BLOCKS = 10;//默认添加的路障数量
    //    private boolean justInit;
    private Dot matrix[][];//存放游戏面板上面的点的二维数组
    private Dot cat;//猫所在的位置

    public Playground(Context context) {
        super(context);
        //1.为SurfaceView添加状态监听->创建和改变的时候绘制界面布局
        getHolder().addCallback(callBack);
        //2.初始化游戏数据
        matrix = new Dot[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                matrix[i][j] = new Dot(j, i);//x取列值，y取行值.正好相反
            }
        }
        //3.设置触摸监听=====>>>用户点击->更新游戏数据(猫选择逃生的最优的位置、用户点击的点的位置)->更新界面布局
        setOnTouchListener(this);
        //4.实例化游戏数据->实例化布局中所有点的属性STATUS_OFF->实例化地图中间点的猫的数据STATUS_IN->实例化一定数量的路障点STATUS_ON
        initGame();
    }

    private Dot getDot(int x, int y) {
        return matrix[y][x];
    }

    /**
     * 是否在边界
     *
     * @param dot
     * @return
     */
    private boolean isAtEdge(Dot dot) {
        if (dot.getX() * dot.getY() == 0 || dot.getX() + 1 == COL || dot.getY() + 1 == ROW) {
            return true;
        }
        return false;
    }

    /**
     * 获取周边相邻点
     *
     * @param dot
     * @param dir
     * @return
     */
    private Dot getNrighbour(Dot dot, int dir) {
        switch (dir) {
            case 1:
                return getDot(dot.getX() - 1, dot.getY());
            case 2:
                if (dot.getY() % 2 == 0) {//奇数行
                    return getDot(dot.getX() - 1, dot.getY() - 1);
                } else {
                    return getDot(dot.getX(), dot.getY() - 1);
                }
            case 3:
                if (dot.getY() % 2 == 0) {
                    return getDot(dot.getX(), dot.getY() - 1);
                } else {
                    return getDot(dot.getX() + 1, dot.getY() - 1);
                }
            case 4:
                return getDot(dot.getX() + 1, dot.getY());
            case 5:
                if (dot.getY() % 2 == 0) {
                    return getDot(dot.getX(), dot.getY() + 1);
                } else {
                    return getDot(dot.getX() + 1, dot.getY() + 1);
                }
            case 6:
                if (dot.getY() % 2 == 0) {
                    return getDot(dot.getX() - 1, dot.getY() + 1);
                } else {
                    return getDot(dot.getX(), dot.getY() + 1);
                }
        }
        return null;
    }

    /**
     * 一定方向的可行走距离
     *
     * @param dot
     * @param dir
     * @return 负数表示走不出去，正数表示走出去需要的步数
     */
    private int getDistance(Dot dot, int dir) {
        int distance = 0;
        if (isAtEdge(dot)) {
            return 1;
        }
        boolean toedge = true;
        Dot ori = dot, next;
        while (true) {
            next = getNrighbour(ori, dir);
            if (next.getStatus() == Dot.STATUS_ON)
                return distance * -1;
            if (isAtEdge(next))
                return distance + 1;
            distance++;
            ori = next;
        }
    }

    /**
     * 猫移动到一个点的位置
     *
     * @param one
     */
    private void MoveTo(Dot one) {
        one.setStatus(Dot.STATUS_IN);
        getDot(cat.getX(), cat.getY()).setStatus(Dot.STATUS_OFF);
        cat.setXY(one.getX(), one.getY());

    }

    /**
     * 猫移动的方法
     */
    private void moveCat() {
        if (isAtEdge(cat)) {//已经在场景边界
            lose();
            return;
        }
        Vector<Dot> avaliable = new Vector<>();
        Vector<Dot> positive = new Vector<>();
        HashMap<Dot, Integer> al = new HashMap<>();
        for (int i = 1; i < 7; i++) {
            Dot n = getNrighbour(cat, i);
            if (n.getStatus() == Dot.STATUS_OFF) {
                avaliable.add(n);
                al.put(n, i);
                if (getDistance(n, i) > 0) {
                    positive.add(n);
                }
            }
        }
        if (avaliable.size() == 0) {
            win();
        } else if (avaliable.size() == 1) {
            MoveTo(avaliable.get(0));
        }
//        else if (justInit) {
//            int s = (int) (Math.random() * 1000) % avaliable.size();
//            MoveTo(avaliable.get(s));
//        }
        else {
            Dot best = null;
            if (positive.size() != 0) {//存在可以直接到达屏幕边缘的走向
                int min = 999;//最大值
                for (int i = 0; i < positive.size(); i++) {
                    int a = getDistance(positive.get(i), al.get(positive.get(i)));
                    if (a < min) {
                        min = a;
                        best = positive.get(i);
                    }
                }
            } else {//所有方向都存在路障
                int max = 0;
                for (int i = 0; i < avaliable.size(); i++) {
                    int k = getDistance(avaliable.get(i), al.get(avaliable.get(i)));
                    if (k < max) {
                        max = k;
                        best = avaliable.get(i);
                    }
                }
            }
            MoveTo(best);
        }
    }

    private void lose() {
        Toast.makeText(getContext(), "Lose", Toast.LENGTH_SHORT).show();
    }

    private void win() {
        Toast.makeText(getContext(), "You Win", Toast.LENGTH_SHORT).show();
    }

    /**
     * 绘制方法
     */
    private void reDraw() {
        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.LTGRAY);
        Paint paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        for (int i = 0; i < ROW; i++) {
            int offset = 0;
            for (int j = 0; j < COL; j++) {
                if (i % 2 != 0) {//如果是奇数行。(从0开始，)
                    offset = WIDTH / 2;
                }
                Dot one = getDot(j, i);
                switch (one.getStatus()) {
                    case Dot.STATUS_ON:
                        paint.setColor(Color.YELLOW);
                        break;
                    case Dot.STATUS_OFF:
                        paint.setColor(Color.WHITE);
                        break;
                    case Dot.STATUS_IN:
                        paint.setColor(Color.RED);
                        break;
                }
                canvas.drawOval(new RectF(one.getX() * WIDTH + offset, one.getY() * WIDTH, (one.getX() + 1) * WIDTH + offset, (one.getY() + 1) * WIDTH), paint);
            }
        }
        getHolder().unlockCanvasAndPost(canvas);
    }

    SurfaceHolder.Callback callBack = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            reDraw();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            WIDTH = width / (COL + 1);
            reDraw();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

    /**
     * 初始化游戏中的数据
     */
    private void initGame() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                matrix[i][j].setStatus(Dot.STATUS_OFF);
            }
        }
        cat = new Dot(4, 5);
        getDot(4, 5).setStatus(Dot.STATUS_IN);
        for (int i = 0; i < BLOCKS; ) {
            int x = (int) ((Math.random() * 1000) % COL);
            int y = (int) ((Math.random() * 1000) % ROW);
            if (getDot(x, y).getStatus() == Dot.STATUS_OFF) {
                getDot(x, y).setStatus(Dot.STATUS_ON);
                i++;
                KLog.d(i);
            }
        }
//        justInit = true;
    }

    /**
     * 1.更新点击的游戏点的状态
     * 2.更新猫的位置
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int x, y;
            y = (int) (event.getY() / WIDTH);
            if (y % 2 == 0) {
                x = (int) (event.getX() / WIDTH);
            } else {
                x = (int) ((event.getX() - WIDTH / 2) / WIDTH);
            }
            if (x + 1 > COL || y + 1 > ROW) {//超出游戏面板中所有点的位置
                initGame();
//                getNrighbour(cat, k).setStatus(Dot.STATUS_IN);
//                k++;
//                redraw();
//                for (int i = 1; i < 7; i++) {
//                    KLog.d(i + "@" + getDistance(cat, i));
//                }
            } else if (getDot(x, y).getStatus() == Dot.STATUS_OFF) {//如果点击的点是未激活状态
                getDot(x, y).setStatus(Dot.STATUS_ON);
                moveCat();
            }
            reDraw();
        }
        return true;
    }
}
