// Copyright (c) 2014, https://github.com/rhcad/touchvg

package touchvg.demo1;

import rhcad.touchvg.IGraphView.OnViewDetachedListener;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
    private Bundle mState;
    private Drawable mBackground;
    private static final String VGEXT = ".vg";
    private static final String KEY_PAGE = "page";
    private static final String KEY_DRAWING = "drawing";

    @Override
    protected void createGraphView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pager1);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new ViewPagerAdapter());
    }

    private String getFileName(int position) {
        return position + VGEXT;
    }

    @Override
    protected String getFileName() {
        return (mViewPager != null ? mViewPager.getCurrentItem() : 0) + VGEXT;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_PAGE, mViewPager.getCurrentItem());
        outState.putBoolean(KEY_DRAWING, hlp.getView() != null);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mState = savedInstanceState;
        mViewPager.setCurrentItem(savedInstanceState.getInt(KEY_PAGE));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (mViewPager != null) {
            closeDrawing(hlp, mViewPager.getCurrentItem());
        }
        super.onDestroy();
    }

    @Override
    protected void switchGestureEnabled() {
        int position = mViewPager.getCurrentItem();
        if (!closeDrawing(position)) {
            addDrawingView(position, PATH + getFileName(position));
        }
    }

    private boolean closeDrawing(int position) {
        return closeDrawing(ViewFactory.createHelper(mViews[position]), position);
    }

    private boolean closeDrawing(IViewHelper tmphlp, final int position) {
        if (tmphlp != null && tmphlp.getView() != null) {
            if (tmphlp != hlp && tmphlp.getView() == hlp.getView()) {
                hlp.setGraphView(null);
            }

            if (mChangeCounts[position] != tmphlp.getChangeCount()) {
                tmphlp.saveToFile(PATH + getFileName(position));
                tmphlp.exportPNG(PATH + getFileName(position));
            }
            tmphlp.close(new OnViewDetachedListener() {
                @Override
                public void onGraphViewDetached() {
                    // Called in onDetachedFromWindow, so add the image view in the same parent view later.
                    // You may reload and show image content immediately in another parent view.
                    mViews[position].post(new Runnable() {
                        @Override
                        public void run() {
                            addImageView(position);
                        }
                    });
                }
            });
            return true;
        }

        return false;
    }

    private void addImageView(int position) {
        final ImageView imageView = new ImageView(ExampleActivityImagePager.this);
        final Bitmap bitmap = BitmapFactory.decodeFile(PATH + position + ".png");
        imageView.setImageBitmap(bitmap != null ? bitmap : BitmapFactory.decodeResource(
                getResources(), R.drawable.dummy));
        mViews[position].addView(imageView);
    }

    private void addDrawingView(int position, String filename) {
        hlp.createSurfaceAndImageView(this, mViews[position], mState);
        hlp.setBackgroundDrawable(mBackground);
        hlp.loadFromFile(filename);
        mChangeCounts[position] = hlp.getChangeCount();
        afterViewCreated(hlp);
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
            if (mBackground == null) {
                mBackground = mViewPager.getBackground();
            }
            if (mViews[position] == null) {
                mViews[position] = new FrameLayout(ExampleActivityImagePager.this);
            }

            final Bundle state = (mState != null && mState.getBoolean(KEY_DRAWING)
                    && mState.getInt(KEY_PAGE) == position) ? mState.getBundle("vg") : null;

            if (state != null) {
                mState = state;
                addDrawingView(position, mState.getString("bakFile"));
                mState = null;
            } else if (mViewPager.getCurrentItem() != position
                    || !closeDrawing(hlp, position)) {
                addImageView(position);
            }
            container.addView(mViews[position]);
            return mViews[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            final FrameLayout layout = mViews[position];
            final ImageView imageView = (ImageView) mViews[position].getChildAt(0);

            if (imageView != null) {
                imageView.setImageBitmap(null);
                mViews[position].removeAllViews();
            }
            container.removeView(layout);
            mViews[position] = null;
        }
    }
}
