// Copyright (c) 2013, https://github.com/rhcad/touchvg

package touchvg.demo1;

import rhcad.touchvg.Const;
import rhcad.touchvg.IGraphView;
import rhcad.touchvg.IViewHelper;
import rhcad.touchvg.ViewFactory;
import rhcad.touchvg.core.MgShape;
import rhcad.touchvg.core.MgShapeBit;
import rhcad.touchvg.core.Vector2d;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

// Testing Activity using StdGraphView
// Upload testing file: adb push YourTestFile.vg mnt/sdcard/TouchVG/demo.vg
//
public class ExampleActivity1 extends Activity implements IGraphView.OnFirstRegenListener {
    protected IViewHelper hlp = ViewFactory.createHelper();
    protected static final String PATH = "mnt/sdcard/TouchVG/";
    private static final String VGFILE = "demo.vg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.createGraphView(savedInstanceState);
        this.initButtons();

        if (hlp.getGraphView() != null) {
            afterViewCreated(hlp);
            if (savedInstanceState == null) {
                hlp.getGraphView().setOnFirstRegenListener(this);
            }
        }
    }

    protected void afterViewCreated(IViewHelper hlp) {
        hlp.setCommand("@draw");
        hlp.setStrokeWidth(5);
    }

    protected void createGraphView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_example1);
        final ViewGroup layout = (ViewGroup) this.findViewById(R.id.frame);
        hlp.createGraphView(this, layout, savedInstanceState);
        hlp.getView().setBackgroundColor(Color.GRAY);
    }

    protected String getFileName() {
        return VGFILE;
    }

    @Override
    public void onDestroy() {
        hlp.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        hlp.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        hlp.onResume();
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        hlp.onSaveInstanceState(outState, PATH);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        hlp.onRestoreInstanceState(savedInstanceState);
    }

    public void onFirstRegen(IGraphView view) {
        hlp.startUndoRecord(PATH + "undo");
    }

    private void initCommandButton(int id, final String name) {
        this.findViewById(id).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hlp.setCommand(name);
            }
        });
    }

    private void initColorButton(int id, final int argb) {
        this.findViewById(id).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hlp.setLineColor(argb);
            }
        });
    }

    protected void initButtons() {
        initCommandButton(R.id.selectBtn, "select");
        initCommandButton(R.id.splinesBtn, "splines");
        initCommandButton(R.id.lineBtn, "line");
        initColorButton(R.id.redPen, Color.RED);
        initColorButton(R.id.bluePen, Color.BLUE);
        initRedoSaveButton();

        this.findViewById(R.id.lineStyleBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hlp.setLineStyle((hlp.getLineStyle() + 1) % Const.MAX_LINESTYLE);
            }
        });
        this.findViewById(R.id.addSVGFile).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                lockShape(hlp.insertSVGFromResource(R.raw.text));
            }
        });
        this.findViewById(R.id.lockGesture).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchGestureEnabled();
            }
        });
    }

    private void initRedoSaveButton() {
        this.findViewById(R.id.saveBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hlp.saveToFile(PATH + VGFILE);
            }
        });
        this.findViewById(R.id.loadBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hlp.loadFromFile(PATH + VGFILE);
            }
        });
        this.findViewById(R.id.undoBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hlp.undo();
            }
        });
        this.findViewById(R.id.redoBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hlp.redo();
            }
        });
    }

    protected void switchGestureEnabled() {
        hlp.setGestureEnabled(!hlp.getGestureEnabled());
    }

    protected void lockShape(int sid) {
        final MgShape sp = hlp.cmdView().shapes().cloneShape(sid);
        if (sp != null) {
            // Not show context buttons | Can't select or change it | Offset for test
            sp.shape().setFlag(MgShapeBit.kMgNoAction, true);
            sp.shape().setFlag(MgShapeBit.kMgShapeLocked, true);
            sp.shape().offset(new Vector2d(50, 50), -1);
            sp.getParent().updateShape(sp);
            hlp.cmdView().regenAll(true);
        }
    }
}
