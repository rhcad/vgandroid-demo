//! \file TestDoubleViews.java
//! \brief 测试双视图布局的测试视图类
// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package vgtest.testview.view;

import vgtest.testview.TestFlags;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.FrameLayout;

//! 测试双视图布局的测试视图类
public class TestDoubleViews extends FrameLayout {

    public TestDoubleViews(Context context) {
        super(context);

        final int flags = ((Activity) context).getIntent().getExtras().getInt("flags");
        final LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        if ((flags & TestFlags.MAIN_CACHEDVIEW) != 0) {
            useGraphViewCached(context, params, flags);
        } else if ((flags & TestFlags.MAIN_STDVIEW) == 0) {
            useStdViewCached(context, params, flags);
        } else if ((flags & TestFlags.MAIN_SFVIEW) == 0) {
            useSfViewCached(context, params, flags);
        }

        final Button midview = new Button(context);
        midview.setText("This is a button in the FrameLayout.");
        midview.setBackgroundColor(Color.GRAY);
        addView(midview, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    // 主视图使用GraphViewCached
    private void useGraphViewCached(Context context, LayoutParams params, int flags) {
        final GraphViewCached view = new GraphViewCached(context);
        addView(view, params);

        // 动态视图使用普通View
        if ((flags & TestFlags.DYN_STDVIEW) != 0) {
            final DynDrawStdView dynview = new DynDrawStdView(context);
            view.setDynDrawView(dynview);
            addView(dynview, params);
        } else if ((flags & TestFlags.DYN_SFVIEW) != 0) {
            // 动态视图使用SurfaceView
            final DynDrawSfView dynview = new DynDrawSfView(context);
            view.setDynDrawView(dynview);
            addView(dynview, params);
        }
    }

    // 主视图使用普通View
    private void useStdViewCached(Context context, LayoutParams params, int flags) {
        final GraphView view = new GraphView(context);
        addView(view, params);

        if ((flags & TestFlags.DYN_STDVIEW) != 0) {
            final DynDrawStdView dynview = new DynDrawStdView(context);
            view.setDynDrawView(dynview);
            addView(dynview, params);
        } else if ((flags & TestFlags.DYN_SFVIEW) != 0) {
            final DynDrawSfView dynview = new DynDrawSfView(context);
            view.setDynDrawView(dynview);
            addView(dynview, params);
        }
    }

    // 主视图使用SurfaceView
    private void useSfViewCached(Context context, LayoutParams params, int flags) {
        final GraphSfView view = new GraphSfView(context);

        if ((flags & TestFlags.MODEL_SURFACE) == 0) {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        addView(view, params);

        if ((flags & TestFlags.DYN_STDVIEW) != 0) {
            final DynDrawStdView dynview = new DynDrawStdView(context);
            view.setDynDrawView(dynview);
            addView(dynview, params);
        } else if ((flags & TestFlags.DYN_SFVIEW) != 0) {
            final DynDrawSfView dynview = new DynDrawSfView(context);
            view.setDynDrawView(dynview);
            addView(dynview, params);
        }
    }
}
