//! \file GraphView2.java
//! \brief 基于普通View类的动画绘图测试视图类
// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package vgtest.testview.canvas;

import android.content.Context;

//! 基于普通View类的动画绘图测试视图类
public class GraphView2 extends GraphView1 {

    public GraphView2(Context context) {
        super(context);
        new Thread(new DrawThread()).start();
    }

    class DrawThread implements Runnable {
        public void run() {
            while (mCanvas != null) {
                postInvalidate();
            }
        }
    }
}
