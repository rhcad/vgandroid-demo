// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package vgtest.testview;

public class TestFlags {
    // see SFGraphView1 or StdGraphView1
    public static final int SPLINES_CMD = 1;
    public static final int SELECT_CMD = 2;
    public static final int LINE_CMD = 4;
    public static final int LINES_CMD = 5;
    public static final int HITTEST_CMD = 6;
    public static final int CMD_MASK = 7;
    public static final int SWITCH_CMD = 1 << 3;
    public static final int TWO_MAGVIEWS = 1 << 4;
    public static final int RAND_SHAPES = 1 << 5;
    public static final int RAND_SPLINES = RAND_SHAPES | SPLINES_CMD;
    public static final int RAND_LINE = RAND_SHAPES | LINE_CMD;
    public static final int RAND_SELECT = RAND_SHAPES | SELECT_CMD;
    public static final int RECORD = 1 << 6;
    public static final int RECORD_SPLINES = RECORD | SPLINES_CMD;
    public static final int RECORD_LINE = RECORD | LINE_CMD;
    public static final int RECORD_SPLINES_RAND = RECORD | SPLINES_CMD | RAND_SHAPES;
    public static final int RECORD_LINE_RAND = RECORD | LINE_CMD | RAND_SHAPES;
    public static final int HAS_BACKDRAWABLE = 1 << 7;
    public static final int CMD_PARAMETER = 1 << 8;

    public static final int OPAQUE_VIEW = 0x1000;
    public static final int LARGE_VIEW = 0x10000;
    public static final int LARGE_SURFACEVIEW = 0x20000;
    public static final int INSCROLLVIEW = 0xF0000;

    // see TestDoubleViews
    public static final int MODEL_SURFACE = 0x100000;
    public static final int MAIN_SFVIEW = 0x01;
    public static final int MAIN_MODELSFVIEW = MAIN_SFVIEW | MODEL_SURFACE;
    public static final int MAIN_STDVIEW = 0x02;
    public static final int MAIN_CACHEDVIEW = 0x04;
    public static final int DYN_STDVIEW = 0x10;
    public static final int DYN_SFVIEW = 0x20;

    private TestFlags() {
    }
}
