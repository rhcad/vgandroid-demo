// Copyright (c) 2014, https://github.com/rhcad/touchvg

package touchvg.demo1;

import rhcad.touchvg.IGraphView;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

// Testing Activity using SFGraphView in PagerView
public class ExampleActivityImagePager extends ExampleActivity1 {
    private ViewPager mViewPager;
    private FrameLayout[] mViews = new FrameLayout[5];
    private int[] mChangeCounts = new int[5];
    private Bundle mInstanceState;

    @Override
    protected void createGraphView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pager1);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new ViewPagerAdapter());
    }

    protected String getFileName(int position) {
        return position + ".vg";
    }

    @Override
    protected String getFileName() {
        return (mViewPager != null ? mViewPager.getCurrentItem() : 0) + ".vg";
    }

    @Override
    protected void switchGestureEnabled() {
        int position = mViewPager.getCurrentItem();
        final FrameLayout layout = mViews[position];

        try {
            quitDrawing((IGraphView) layout.getChildAt(0), position);
            addImageView(position);
        } catch (ClassCastException e) {
            quitDrawing(null, position);
            addDrawingView(position, PATH + getFileName(position));
        }
    }

    private void addDrawingView(int position, String filename) {
        hlp.createSurfaceView(this, mViews[position]);
        hlp.setBackgroundDrawable(mViewPager.getBackground());
        hlp.setZoomEnabled(false);
        hlp.loadFromFile(filename);
        mChangeCounts[position] = hlp.getChangeCount();
        afterViewCreated(hlp);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("page", mViewPager.getCurrentItem());
        outState.putBoolean("drawing", hlp.getView() != null);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mInstanceState = savedInstanceState;
        mViewPager.setCurrentItem(savedInstanceState.getInt("page"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void quitDrawing(IGraphView view, int position) {
        if (view == null) {
            final ImageView imageView = (ImageView) mViews[position].getChildAt(0);
            if (imageView != null) {
                imageView.setImageBitmap(null);
                mViews[position].removeAllViews();
            }
        } else {
            final IViewHelper tmphlp = ViewFactory.createHelper(view);

            if (tmphlp.getView() == hlp.getView())
                hlp.setGraphView(null);
            if (mChangeCounts[position] != tmphlp.getChangeCount()) {
                tmphlp.saveToFile(PATH + getFileName(position));
                tmphlp.exportPNG(PATH + getFileName(position));
            }
            tmphlp.close();
        }
    }

    private void addImageView(int position) {
        final ImageView imageView = new ImageView(ExampleActivityImagePager.this);
        final Bitmap bitmap = BitmapFactory.decodeFile(PATH + position + ".png");
        imageView.setImageBitmap(bitmap != null ? bitmap : BitmapFactory.decodeResource(
                getResources(), R.drawable.dummy));
        mViews[position].addView(imageView);
    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViews.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (mViews[position] == null) {
                mViews[position] = new FrameLayout(ExampleActivityImagePager.this);
                if (mInstanceState != null && mInstanceState.getBoolean("drawing")
                        && mInstanceState.getInt("page") == position) {
                    addDrawingView(position, mInstanceState.getBundle("vg").getString("bakFile"));
                    mInstanceState = null;
                } else {
                    addImageView(position);
                }
            }
            container.addView(mViews[position]);
            return mViews[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            final FrameLayout layout = mViews[position];

            try {
                quitDrawing((IGraphView) layout.getChildAt(0), position);
            } catch (ClassCastException e) {
                quitDrawing(null, position);
            }
            container.removeView(layout);
            mViews[position] = null;
        }
    }
}
