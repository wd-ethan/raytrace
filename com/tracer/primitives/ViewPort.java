package com.tracer.primitives;

import Jama.Matrix;

import java.lang.management.MemoryNotificationInfo;

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

    private final float mNear;
    private final float mTop;
    private final float mBottom;
    private final float mLeft;
    private final float mRight;

    public float width() {
        return Math.abs(mRight) + Math.abs(mLeft);
    }

    public float height() {
        return Math.abs(mTop) + Math.abs(mBottom);
    }

    public float nearPlane() {
        return mNear;
    }

    public static double minDepth(final Ray ray) {
        return - 1 / ray.asVector().get(2, 0);
    }
}
