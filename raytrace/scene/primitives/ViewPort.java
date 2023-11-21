package raytrace.scene.primitives;

public class ViewPort {

    public ViewPort(
            final float near,
            final float top,
            final float bottom,
            final float left,
            final float right){
        mNear = near;
        mTop = top;
        mBottom = bottom;
        mLeft = left;
        mRight = right;
    }

    ViewPort(){
        this(1, 1, -1, -1, 1);
    }

    final float mNear;
    final float mTop;
    final float mBottom;
    final float mLeft;
    final float mRight;

    public float width() {
        return Math.abs(mRight) + Math.abs(mLeft);
    }

    public float height() {
        return Math.abs(mTop) + Math.abs(mBottom);
    }
}
