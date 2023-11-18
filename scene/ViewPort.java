package scene;

import scene.Resolution;

public class ViewPort {

    ViewPort(
            final Resolution resolution,
            final float near,
            final float top,
            final float bottom,
            final float left,
            final float right){
        mResolution = resolution;
        mNear = near;
        mTop = top;
        mBottom = bottom;
        mLeft = left;
        mRight = right;
    }

    ViewPort(){
        this(new Resolution(), 1, 1, -1, -1, 1);
    }

    private final Resolution mResolution;
    private final float mNear;
    private final float mTop;
    private final float mBottom;
    private final float mLeft;
    private final float mRight;
}
