// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package touchvg.demo1;

import java.util.ArrayList;
import java.util.List;

import rhcad.touchvg.IGraphView;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;

// Testing Activity using SFGraphView in PagerView
public class ExampleActivityPager extends ExampleActivity1 {
    private static final String VGEXT = ".vg";
    private ViewPager viewPager;
    private List<IGraphView> views = new ArrayList<IGraphView>();

    @Override
    protected void createGraphView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pager1);

        views.add(createPage(0));
        for (int i = 1; i < 4; i++) {
            views.add(null);
        }
        hlp.setGraphView(views.get(0));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter());
        viewPager.setOnPageChangeListener(new ViewPagerChangeListener());
    }

    private IGraphView createPage(int position) {
        final IViewHelper tmphlp = ViewFactory.createHelper();

        tmphlp.createSurfaceView(this, null);
        if (viewPager != null) {
            tmphlp.setBackgroundDrawable(viewPager.getBackground());
        }
        if (position % 2 == 0) {
            tmphlp.setZoomEnabled(false);
        }
        tmphlp.loadFromFile(PATH + getFileName(position));
        tmphlp.setGestureEnabled(false);
        afterViewCreated(tmphlp);

        return tmphlp.getGraphView();
    }

    protected String getFileName(int position) {
        return position + VGEXT;
    }

    @Override
    protected String getFileName() {
        return (viewPager != null ? viewPager.getCurrentItem() : 0) + VGEXT;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("page", viewPager.getCurrentItem());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        viewPager.setCurrentItem(savedInstanceState.getInt("page"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (views.get(position) == null) {
                views.set(position, createPage(position));
            }

            final ViewGroup layout = (ViewGroup) views.get(position).getView().getParent();
            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            final IViewHelper tmphlp = ViewFactory.createHelper(views.get(position));

            if (tmphlp.getView() == hlp.getView()) {
                hlp.setGraphView(null);
            }
            tmphlp.saveToFile(PATH + getFileName(position));
            container.removeView((ViewGroup) tmphlp.getParent());
            tmphlp.close();
            views.set(position, null);
        }
    }

    private class ViewPagerChangeListener extends SimpleOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            hlp.setGraphView(views.get(position));
        }
    }
}
