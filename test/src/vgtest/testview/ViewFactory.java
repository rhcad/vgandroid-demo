//! \file ViewFactory.java
//! \brief 基于普通View类的绘图测试视图类
// Copyright (c) 2012-2015, https://github.com/rhcad/vgandroid-demo, BSD license

package vgtest.testview;

import java.util.ArrayList;
import java.util.List;

import rhcad.touchvg.core.TestCanvas;

//! 测试视图的构造列表类
public class ViewFactory {

    public static class DummyItem {

        private String id;
        private int flags;
        private String title;

        public DummyItem(String id, int flags, String title) {
            this.id = id;
            this.flags = flags;
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }

        public int getFlags() {
            return flags;
        }

        public String getId() {
            return id;
        }
    }

    private static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    private static final String SFGRAPHVIEW1 = "vgtest.testview.view.SFGraphView1";
    private static final String GRAPHVIEW1 = "vgtest.testview.view.StdGraphView1";
    private static final String MAGNIFIER1 = "vgtest.testview.view.TestMagnifier1";
    private static final String LARGEVIEW1 = "vgtest.testview.view.LargeView1";
    private static final String DBLVIEWS = "vgtest.testview.view.TestDoubleViews";
    private static final String LARGEVIEW2 = "vgtest.testview.view.LargeView2";
    private static final String CANVASVIEW1 = "vgtest.testview.canvas.GraphView1";
    private static final String CANVASSFVIEW1 = "vgtest.testview.canvas.SurfaceView1";
    private static final String CANVASSFVIEW2 = "vgtest.testview.canvas.SurfaceView2";
    private static final String CANVASSFVIEW3 = "vgtest.testview.canvas.SurfaceView3";
    private static final String LARGEVIEW3 = "vgtest.testview.canvas.LargeView1";

    static {
        testShapeViews();
        testSurfaceViews();
        testStdViews();

        testTwoLayerViews();
        testCanvasViews();
        testLargeView3();
    }

    private ViewFactory() {
    }

    public static final List<DummyItem> items() {
        return ITEMS;
    }

    static void testShapeViews() {
        addItem("vgtest.testview.shape.ViewSinShape", 0, "ViewSinShape");
        addItem("vgtest.testview.shape.TestDragView", 0, "TestDragView");
        addItem("vgtest.testview.shape.TestInsertSVG", 0, "TestInsertSVG");
    }

    static void testSurfaceViews() {
        addItem(SFGRAPHVIEW1, TestFlags.SPLINES_CMD, "SFGraphView splines");
        addItem(SFGRAPHVIEW1, TestFlags.RAND_SPLINES, "SFGraphView randShapes splines");
        addItem(SFGRAPHVIEW1, TestFlags.RAND_LINE, "SFGraphView randShapes line");
        addItem(SFGRAPHVIEW1, TestFlags.LINE_CMD, "SFGraphView line");
        addItem(SFGRAPHVIEW1, TestFlags.LINES_CMD, "SFGraphView lines");
        addItem(SFGRAPHVIEW1, TestFlags.SWITCH_CMD, "SFGraphView switch command");
        addItem(SFGRAPHVIEW1, TestFlags.SWITCH_CMD | TestFlags.HITTEST_CMD,
                "SFGraphView switch hittest in democmds");
        addItem(SFGRAPHVIEW1, TestFlags.RECORD_SPLINES, "SFGraphView record splines");
        addItem(SFGRAPHVIEW1, TestFlags.RECORD_LINE, "SFGraphView record line");
        addItem(SFGRAPHVIEW1, TestFlags.RECORD_SPLINES_RAND,
                "SFGraphView record randShapes splines");
        addItem(SFGRAPHVIEW1, TestFlags.RECORD_LINE_RAND, "SFGraphView record randShapes line");
        addItem(SFGRAPHVIEW1, TestFlags.RECORD | TestFlags.RAND_SHAPES,
                "SFGraphView randShapes play");
        addItem(SFGRAPHVIEW1, TestFlags.RAND_SELECT, "SFGraphView select");
        addItem(SFGRAPHVIEW1, TestFlags.HAS_BACKDRAWABLE | TestFlags.RAND_SELECT,
                "SFGraphView select transparent");
        addItem(SFGRAPHVIEW1, TestFlags.HAS_BACKDRAWABLE | TestFlags.RAND_SPLINES,
                "SFGraphView draw transparent");
        addItem(SFGRAPHVIEW1, TestFlags.CMD_PARAMETER | TestFlags.SELECT_CMD,
                "Command with parameter");
    }

    static void testStdViews() {
        addItem(GRAPHVIEW1, TestFlags.SPLINES_CMD, "StdGraphView splines");
        addItem(GRAPHVIEW1, TestFlags.RAND_SPLINES, "StdGraphView randShapes splines");
        addItem(GRAPHVIEW1, TestFlags.RAND_LINE, "StdGraphView randShapes line");
        addItem(GRAPHVIEW1, TestFlags.LINE_CMD, "StdGraphView line");
        addItem(GRAPHVIEW1, TestFlags.LINES_CMD, "StdGraphView lines");
        addItem(GRAPHVIEW1, TestFlags.RECORD_SPLINES, "StdGraphView record splines");
        addItem(GRAPHVIEW1, TestFlags.RECORD_LINE, "StdGraphView record line");
        addItem(GRAPHVIEW1, TestFlags.RECORD_SPLINES_RAND, "StdGraphView record randShapes splines");
        addItem(GRAPHVIEW1, TestFlags.RECORD_LINE_RAND, "StdGraphView record randShapes line");
        addItem(GRAPHVIEW1, TestFlags.RECORD | TestFlags.RAND_SHAPES,
                "StdGraphView randShapes play");
        addItem(GRAPHVIEW1, TestFlags.SWITCH_CMD, "StdGraphView switch command");
        addItem(GRAPHVIEW1, TestFlags.SWITCH_CMD | TestFlags.HITTEST_CMD,
                "StdGraphView switch hittest in democmds");
        addItem(GRAPHVIEW1, TestFlags.RAND_SELECT, "StdGraphView select");

        addItem(MAGNIFIER1, TestFlags.SPLINES_CMD, "TestMagnifier");
        addItem(MAGNIFIER1, TestFlags.TWO_MAGVIEWS | TestFlags.SPLINES_CMD,
                "TestMagnifier, 2 views");
        addItem(LARGEVIEW1, TestFlags.SPLINES_CMD, "Scroll GraphView splines");
        addItem(LARGEVIEW1, TestFlags.LINE_CMD, "Scroll GraphView line");
        addItem(LARGEVIEW1, TestFlags.RAND_SELECT, "Scroll GraphView select");
    }

    static void testTwoLayerViews() {
        addItem("vgtest.testview.view.GraphView", 0, "TestOneView");
        addItem(DBLVIEWS, TestFlags.MAIN_SFVIEW, "TestOneSurfaceView");
        addItem(DBLVIEWS, TestFlags.MAIN_MODELSFVIEW, "TestOneSurfaceView, back");
        addItem("vgtest.testview.view.GraphViewCached", 0, "GraphViewCached");

        addItem(DBLVIEWS, TestFlags.MAIN_STDVIEW | TestFlags.DYN_STDVIEW,
                "Test2Views, std view+view");
        addItem(DBLVIEWS, TestFlags.MAIN_SFVIEW | TestFlags.DYN_STDVIEW,
                "Test2Views, top surface+view");
        addItem(DBLVIEWS, TestFlags.MAIN_STDVIEW | TestFlags.DYN_SFVIEW,
                "Test2Views, std view+surface");
        addItem(DBLVIEWS, TestFlags.MAIN_SFVIEW | TestFlags.DYN_SFVIEW,
                "Test2Views, top surface+surface");
        addItem(DBLVIEWS, TestFlags.MAIN_MODELSFVIEW | TestFlags.DYN_STDVIEW,
                "Test2Views, back surface+view");
        addItem(DBLVIEWS, TestFlags.MAIN_MODELSFVIEW | TestFlags.DYN_SFVIEW,
                "Test2Views, back surface+surface");
        addItem(DBLVIEWS, TestFlags.MAIN_CACHEDVIEW | TestFlags.DYN_SFVIEW,
                "Test2Views, cachedview+surface");
        addItem(DBLVIEWS, TestFlags.MAIN_CACHEDVIEW | TestFlags.DYN_STDVIEW,
                "Test2Views, cachedview+view");

        addItem(LARGEVIEW2, TestFlags.MAIN_STDVIEW | TestFlags.DYN_STDVIEW,
                "Test2Views, scroll view+view");
        addItem(LARGEVIEW2, TestFlags.MAIN_SFVIEW | TestFlags.DYN_STDVIEW,
                "Test2Views, scroll surface+view");
        addItem(LARGEVIEW2, TestFlags.MAIN_STDVIEW | TestFlags.DYN_SFVIEW,
                "Test2Views, scroll view+surface");
        addItem(LARGEVIEW2, TestFlags.MAIN_SFVIEW | TestFlags.DYN_SFVIEW,
                "Test2Views, scroll surface+surface");
        addItem(LARGEVIEW2, TestFlags.MAIN_STDVIEW, "TestOneView, scroll");
        addItem(LARGEVIEW2, TestFlags.MAIN_SFVIEW, "TestOneSurfaceView, scroll");
        addItem(DBLVIEWS, TestFlags.MAIN_MODELSFVIEW | TestFlags.DYN_STDVIEW,
                "Test2Views, scroll back surface+view");
        addItem(DBLVIEWS, TestFlags.MAIN_MODELSFVIEW | TestFlags.DYN_SFVIEW,
                "Test2Views, scroll back surface+surface");
        addItem(LARGEVIEW2, TestFlags.MAIN_CACHEDVIEW | TestFlags.DYN_SFVIEW,
                "Test2Views, scroll cachedview+surface");
        addItem(LARGEVIEW2, TestFlags.MAIN_CACHEDVIEW | TestFlags.DYN_STDVIEW,
                "Test2Views, scroll cachedview+view");
    }

    static void testCanvasViews() {
        addItem(CANVASVIEW1, TestCanvas.kRect, "testRect");
        addItem(CANVASVIEW1, TestCanvas.kLine, "testLine");
        addItem(CANVASVIEW1, TestCanvas.kTextAt, "testTextAt");
        addItem(CANVASVIEW1, TestCanvas.kRotateText, "testRotateText");
        addItem(CANVASVIEW1, TestCanvas.kEllipse, "testEllipse");
        addItem(CANVASVIEW1, TestCanvas.kQuadBezier, "testQuadBezier");
        addItem(CANVASVIEW1, TestCanvas.kCubicBezier, "testCubicBezier");
        addItem(CANVASVIEW1, TestCanvas.kPolygon, "testPolygon");
        addItem(CANVASVIEW1, TestCanvas.kClearRect | TestCanvas.kTextAt | TestCanvas.kLine,
                "testClearRect");
        addItem(CANVASVIEW1, TestCanvas.kClipPath, "testClipPath");
        addItem(CANVASVIEW1, TestCanvas.kHandle, "testHandle");
        addItem("vgtest.testview.canvas.GraphView2", TestCanvas.kDynCurves, "testDynCurves");

        addItem(CANVASSFVIEW1, TestCanvas.kCubicBezier, "testCubicBezier in SurfaceView");
        addItem(CANVASSFVIEW1, TestCanvas.kClearPolygon, "testClearRect in SurfaceView");
        addItem(CANVASSFVIEW2, TestCanvas.kCubicBezier,
                "testCubicBezier in SurfaceView with thread");
        addItem(CANVASSFVIEW2, TestCanvas.kLine, "testLine in SurfaceView with thread");
        addItem(CANVASSFVIEW2, TestCanvas.kDynCurves, "testDynCurves in SurfaceView with touch");
        addItem(CANVASSFVIEW3, TestCanvas.kDynCurves, "testDynCurves in SurfaceView with thread");
        addItem(CANVASSFVIEW3, TestCanvas.kDynCurves | TestFlags.OPAQUE_VIEW,
                "testDynCurves(OPAQUE) with thread");
    }

    static void testLargeView3() {
        addItem(LARGEVIEW3, TestCanvas.kTextAt | TestFlags.LARGE_VIEW, "testTextAt in large view");
        addItem(LARGEVIEW3, TestCanvas.kTextAt | TestFlags.LARGE_SURFACEVIEW,
                "testTextAt in large surface view");
        addItem(LARGEVIEW3, TestCanvas.kRotateText | TestFlags.LARGE_VIEW, "testRotateText in large view");
        addItem(LARGEVIEW3, TestCanvas.kRotateText | TestFlags.LARGE_SURFACEVIEW,
                "testRotateText in large surface view");
        addItem(LARGEVIEW3, TestCanvas.kCubicBezier | TestFlags.LARGE_VIEW,
                "testCubicBezier in large view");
        addItem(LARGEVIEW3, TestCanvas.kCubicBezier | TestFlags.LARGE_SURFACEVIEW,
                "testCubicBezier in large surface view");
        addItem(LARGEVIEW3, TestCanvas.kHandle | TestFlags.LARGE_VIEW, "testHandle in large view");
        addItem(LARGEVIEW3, TestCanvas.kHandle | TestFlags.LARGE_SURFACEVIEW,
                "testHandle in large surface view");
        addItem(LARGEVIEW3, TestCanvas.kDynCurves | TestFlags.LARGE_VIEW,
                "testDynCurves in large view");
        addItem(LARGEVIEW3, TestCanvas.kDynCurves | TestFlags.LARGE_SURFACEVIEW,
                "testDynCurves in large surface view");
    }

    private static void addItem(String id, int flags, String title) {
        ITEMS.add(new DummyItem(id, flags, title));
    }
}
