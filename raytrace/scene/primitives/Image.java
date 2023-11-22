package raytrace.scene.primitives;

import java.util.Stack;

public class Image {

    public Image(final Resolution resolution) {
        mResolution = resolution;
    }

    // TODO : flip pixel orders
    private final Stack<Pixel> mPixels = new Stack<>();
    private final Resolution mResolution;

    public void write(final Pixel pixel) {
        mPixels.add(pixel);
    }

    public String asPpm() {
        final StringBuilder builder = new StringBuilder();

        builder.append("P3\n");
        builder.append(mResolution.width()).append(" ").append(mResolution.height()).append("\n");
        builder.append("255").append("\n");

        while (!mPixels.isEmpty()) {
            final Pixel next = mPixels.pop();

            builder.append(next.asRgb()).append(" ");
            if (next.mCol == mResolution.width() - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }
}
