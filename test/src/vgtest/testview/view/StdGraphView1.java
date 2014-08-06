// Copyright (c) 2012-2013, https://github.com/rhcad/touchvg

package vgtest.testview.view;

import rhcad.touchvg.Const;
import rhcad.touchvg.IGraphView;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import rhcad.touchvg.view.StdGraphView;
import vgtest.testview.TestFlags;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import democmds.core.DemoCmdsGate;

public class StdGraphView1 extends StdGraphView {
    protected static final String PATH = "mnt/sdcard/TouchVG/";

    static {
        System.loadLibrary("democmds");
    }

    public StdGraphView1(Context context) {
        this(context, null);
    }

    public StdGraphView1(Context context, Bundle savedInstanceState) {
        super(context, savedInstanceState);

        final int flags = ((Activity) context).getIntent().getExtras().getInt("flags");
        final IViewHelper helper = ViewFactory.createHelper(this);

        if ((flags & TestFlags.RAND_SHAPES) != 0) {
            helper.addShapesForTest();
        }
        if (savedInstanceState == null && (flags & TestFlags.RECORD) != 0) {
            setOnFirstRegenListener(new IGraphView.OnFirstRegenListener() {

                public void onFirstRegen(IGraphView view) {
                    helper.startRecord(PATH + "record");
                }
            });
        }
        if ((flags & TestFlags.SWITCH_CMD) != 0) {
            setOnGestureListener(new IGraphView.OnDrawGestureListener() {

                public boolean onPreGesture(int gestureType, float x, float y) {
                    if (gestureType == Const.GESTURE_DBLTAP) {
                        helper.switchCommand();
                        Toast.makeText(getContext(), helper.getCommand(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    return false;
                }

                public void onPostGesture(int gestureType, float x, float y) {
                    // Do nothing
                }
            });
        }

        switch (flags & TestFlags.CMD_MASK) {
        case TestFlags.SELECT_CMD:
            helper.setCommand("select");
            break;
        case TestFlags.SPLINES_CMD:
            helper.setCommand("splines");
            break;
        case TestFlags.LINE_CMD:
            helper.setCommand("line");
            break;
        case TestFlags.LINES_CMD:
            helper.setCommand("lines");
            break;
        case TestFlags.HITTEST_CMD:
            int n = DemoCmdsGate.registerCmds(helper.cmdViewHandle());
            helper.setCommand("hittest");
            Log.d("Test", "DemoCmdsGate.registerCmds = " + n + ", " + helper.getCommand());
            break;
        }
    }
}
