// Copyright (c) 2014, https://github.com/rhcad/touchvg

package touchvg.demo1;

import rhcad.touchvg.IGraphView;
import rhcad.touchvg.ViewFactory;
import rhcad.touchvg.core.CmdObserverDefault;
import rhcad.touchvg.core.GiColor;
import rhcad.touchvg.core.GiContext;
import rhcad.touchvg.core.MgBaseShape;
import rhcad.touchvg.core.MgMotion;
import rhcad.touchvg.core.MgShape;

// Testing Activity using CmdObserver to change polygon shape's color.
public class DragObserverActivity extends ExampleActivitySF {

    @Override
    public void onFirstRegen(IGraphView view) {
        super.onFirstRegen(view);
        hlp.setCommand("rect");
        ViewFactory.registerCmdObserver(hlp, new MyCmdObserver());
    }

    private static class MyCmdObserver extends CmdObserverDefault {

        @Override
        public boolean onShapeWillAdded(MgMotion sender, MgShape sp) {
            checkPolygon(sender, sp);
            return true;
        }

        @Override
        public boolean onShapeWillChanged(MgMotion sender, MgShape sp, MgShape oldsp) {
            checkPolygon(sender, sp);
            return true;
        }

        private void checkPolygon(MgMotion sender, MgShape sp) {
            if (sp.shape().isClosed() && !sp.shape().isCurve()) {
                // && sp.isKindOf(MgLines.Type())) { MgLines polygon = (MgLines) sp.shape(); ...
                if (isRegularPolygon(sp.shape(), sender.displayMmToModel(1.f))) {
                    sp.setContext(new GiContext(0, new GiColor(0, 255, 0)), GiContext.kLineRGB);
                } else {
                    sp.setContext(new GiContext(0, new GiColor(0, 0, 255)), GiContext.kLineRGB);
                }
            }
        }

        private boolean isRegularPolygon(MgBaseShape sp, float tol) {
            final float[] lens = new float[sp.getPointCount() - 1];

            for (int i = 0; i < lens.length; i++) {
                lens[i] = sp.getHandlePoint(i).distanceTo(sp.getHandlePoint(i + 1));
                if (i > 0 && !equals(lens[i - 1], lens[i], tol)) {
                    return false;
                }
            }
            return equals(lens[lens.length - 1], lens[0], tol);
        }

        private boolean equals(float a, float b, float tol) {
            return Math.abs(a - b) < tol;
        }
    }
}
