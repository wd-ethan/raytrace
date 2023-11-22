package raytrace.tracer;

import Jama.Matrix;
import raytrace.scene.Scene;
import raytrace.scene.SceneBuilder;
import raytrace.scene.SceneFile;
import raytrace.scene.primitives.*;
import raytrace.scene.primitives.Image;

import java.awt.*;
import java.io.FileNotFoundException;

public class RayTracer {

    public RayTracer(final Resolution resolution, final ViewPort view) {
        mResolution = resolution;
        mView = view;
    }

    final Resolution mResolution;
    final ViewPort mView;

    public Image trace(final Scene scene) {
        final Matrix eye = new Matrix(new double[][] {{0}, {0}, {0}, {1}});
        final Image image =  new Image(mResolution);

        for (final Pixel pixel : mResolution) {
            final Ray ray = pixel.intersect(eye, mView);
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

        final Image image = tracer.trace(scene);
        final OutputFile file = new OutputFile("./here.ppm", image);
        file.write();
    }
}