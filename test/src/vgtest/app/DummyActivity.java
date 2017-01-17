// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package vgtest.app;

import java.lang.reflect.Constructor;

import rhcad.touchvg.IGraphView;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import vgtest.testview.TestFlags;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class DummyActivity extends Activity {
    private static final String TAG = "vgtest";
    private static final String FILEPATH = "mnt/sdcard/TouchVG";
    private IViewHelper mHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        final int flags = bundle.getInt("flags");
        View view = null;

        try {
            final Class<?> c = Class.forName(bundle.getString("className"));
            try {
                final Constructor<?> c2 = c.getDeclaredConstructor(
                        new Class[] { Context.class, Bundle.class });
                c2.setAccessible(true);
                view = (View) c2.newInstance(new Object[] { this, savedInstanceState });
            } catch (Exception e) {
                final Constructor<?> c1 = c.getDeclaredConstructor(new Class[] { Context.class });
                c1.setAccessible(true);
                view = (View) c1.newInstance(new Object[] { this });
            }
        } catch (Exception e) {
            Log.d(TAG, "onCreate fail", e);
        }

        if ((flags & TestFlags.MODEL_SURFACE) != 0) {
            this.setContentView(view);
        } else {
            // LinearLayout layout = new LinearLayout(this);
            // layout.setOrientation(LinearLayout.VERTICAL);
            final FrameLayout layout = new FrameLayout(this);
            layout.setBackgroundResource(R.drawable.vg_translucent_bg); // Test picture tile
            this.setContentView(layout);

            if (view != null) {
                final LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT);
                layout.addView(view, params);

                if (view instanceof IGraphView) {
                    final View dynview = ((IGraphView) view).createDynamicShapeView(this);
                    if (dynview != null) {
                        layout.addView(dynview, params);
                    }
                }
            }
        }
        this.setTitle(bundle.getString("title"));
        mHelper = ViewFactory.createHelper();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = this.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "ORIENTATION_LANDSCAPE");
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "ORIENTATION_PORTRAIT");
        }
    }

    @Override
    public void onDestroy() {
        mHelper.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        mHelper.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mHelper.onResume();
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mHelper.onSaveInstanceState(outState, FILEPATH);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mHelper.onRestoreInstanceState(savedInstanceState);
    }
}
