package vgtest.testview;

public class TestFlags {
    public static final int SPLINES_CMD = 1;
    public static final int SELECT_CMD = 2;
    public static final int LINE_CMD = 4;
    public static final int LINES_CMD = 5;
    public static final int HITTEST_CMD = 6;
    public static final int CMD_MASK = 7;
    public static final int SWITCH_CMD = 8;
    public static final int TWO_MAGVIEWS = 16;
    public static final int RAND_SHAPES = 32;
    public static final int RAND_SPLINES = RAND_SHAPES | SPLINES_CMD;
    public static final int RAND_LINE = RAND_SHAPES | LINE_CMD;
    public static final int RAND_SELECT = RAND_SHAPES | SELECT_CMD;
    public static final int RECORD = 64;
    public static final int RECORD_SPLINES = RECORD | SPLINES_CMD;
    public static final int RECORD_LINE = RECORD | LINE_CMD;
    public static final int RECORD_SPLINES_RAND = RECORD | SPLINES_CMD | RAND_SHAPES;
    public static final int RECORD_LINE_RAND = RECORD | LINE_CMD | RAND_SHAPES;
    public static final int HAS_BACKDRAWABLE = 128;

    public static final int OPAQUE_VIEW = 0x1000;
    public static final int LARGE_VIEW = 0x10000;
    public static final int LARGE_SURFACEVIEW = 0x20000;

    public static final int MODEL_SURFACE = 0x100000;

    private TestFlags() {
    }
}
