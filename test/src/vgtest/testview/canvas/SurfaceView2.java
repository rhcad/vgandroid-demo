//! \file SurfaceView2.java
//! \brief 基于SurfaceView带绘图线程的测试视图类
// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package vgtest.testview.canvas;

import rhcad.touchvg.core.TestCanvas;
import rhcad.touchvg.view.CanvasAdapter;
import vgtest.app.R;
import vgtest.testview.TestFlags;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

//! 基于SurfaceView带绘图线程的测试视图类
public class SurfaceView2 extends SurfaceView {
    private static final String TAG = "vgtest";
    protected CanvasAdapter mCanvas;
    protected int mCreateFlags;
    private long mDrawnTime;
    private float lastX = 50;
    private float lastY = 50;
    private float mPhase = 0;

    public SurfaceView2(Context context) {
        super(context);
        mCanvas = new CanvasAdapter(this);
        mCreateFlags = ((Activity) context).getIntent().getExtras().getInt("flags");
        initCanvas();
        getHolder().addCallback(new SurfaceCallback());

        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);

        this.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                showTime(mDrawnTime);
                lastX = event.getX(0);
                lastY = event.getY(0);
                doDraw();
                return true;
            }
        });
    }

    private void initCanvas() {
        CanvasAdapter.setHandleImageIDs(new int[] { R.drawable.vgdot1, R.drawable.vgdot2,
                R.drawable.vgdot3, R.drawable.vg_lock, R.drawable.vg_unlock });
    }

    @Override
    public void setBackgroundColor(int color) {
        // 视图仍然是透明色
        mCanvas.setBackgroundColor(color);
    }

    protected void showTime(long ms) {
        Activity activity = (Activity) this.getContext();
        String title = activity.getTitle().toString();
        int pos = title.indexOf(' ');
        if (pos >= 0) {
            title = title.substring(0, pos);
        }
        activity.setTitle(title + " - " + Long.toString(ms) + " ms");
    }

    protected void doDraw() {
        if (!mCanvas.isDrawing()) {
            new Thread(new DrawThread()).start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mCanvas != null) {
            mCanvas.delete();
            mCanvas = null;
        }
        super.onDetachedFromWindow();
    }

    class SurfaceCallback implements SurfaceHolder.Callback {
        public void surfaceCreated(SurfaceHolder holder) {
            doDraw();
            showTime(mDrawnTime);
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.d(TAG, "surfaceChanged");
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.d(TAG, "surfaceChanged");
        }
    }

    private void dynDraw(CanvasAdapter canvas) {
        mPhase += 1;
        canvas.setPen(0, 0, 1, mPhase, 0);
        canvas.setBrush(0x22005500, 0);
        mCanvas.drawEllipse(lastX - 50, lastY - 50, 100, 100, true, true);
    }

    class DrawThread implements Runnable {
        public void run() {
            long ms = SystemClock.currentThreadTimeMillis();

            Canvas canvas = null;
            try {
                canvas = getHolder().lockCanvas();
                if (canvas != null) {
                    if (mCanvas.beginPaint(canvas)) {
                        canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
                        if ((mCreateFlags & TestFlags.INSCROLLVIEW) != 0) {
                            TestCanvas.test(mCanvas, mCreateFlags, 400);
                        } else {
                            TestCanvas.test(mCanvas, mCreateFlags);
                        }
                        dynDraw(mCanvas);
                        mCanvas.endPaint();
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "DrawThread", e);
            } finally {
                if (canvas != null) {
                    getHolder().unlockCanvasAndPost(canvas);
                }
            }

            mDrawnTime = SystemClock.currentThreadTimeMillis() - ms;
        }
    }
}
