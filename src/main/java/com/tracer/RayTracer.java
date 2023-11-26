package src.main.java.com.tracer;

import Jama.Matrix;
import src.main.java.com.tracer.scene.Scene;
import src.main.java.com.tracer.scene.SceneBuilder;
import src.main.java.com.tracer.scene.SceneFile;
import src.main.java.com.tracer.scene.primitives.*;
import src.main.java.com.tracer.scene.primitives.Image;

import java.awt.*;
import java.io.FileNotFoundException;

public class RayTracer {

    private static final Matrix ORIGIN = new Matrix(new double[][] {{0}, {0}, {0}, {1}});

    public RayTracer(final Resolution resolution, final ViewPort view) {
        mResolution = resolution;
        mView = view;
    }

    final Resolution mResolution;
    final ViewPort mView;

    public Image trace(final Scene scene) {
        final Image image = new Image(mResolution);

        for (final Pixel pixel : mResolution) {
            final Ray ray = pixel.intersect(ORIGIN, mView);
            final Color color = scene.trace(ray);

            pixel.setColor(color);
            image.write(pixel);
        }

        return image;
    }

    public static void main(String[] args) throws FileNotFoundException {
        final SceneFile sceneFile = new SceneFile(args[0]);
        final SceneBuilder builder = sceneFile.decode();

        final Scene scene = builder.buildScene();
        final RayTracer tracer = builder.buildRayTracer();
        final OutputFile output = builder.buildOutputFile();

        final Image image = tracer.trace(scene);
        output.write(image);
    }
}