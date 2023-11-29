package com.tracer;

import Jama.Matrix;
import com.tracer.primitives.*;
import com.tracer.primitives.Image;
import com.tracer.scene.SceneBuilder;
import com.tracer.scene.SceneFile;
import com.tracer.scene.Scene;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.tracer.RayTracer.ORIGIN;

public class RayTracer {

    public static final Matrix ORIGIN = new Matrix(new double[][] {{0}, {0}, {0}, {1}});

    public RayTracer(final Resolution resolution, final ViewPort view) {
        mResolution = resolution;
        mView = view;
    }

    private final Resolution mResolution;
    private final ViewPort mView;

    public Image trace(final Scene scene) {
        final Image image = new Image(mResolution);
        final ExecutorService executor = Executors.newFixedThreadPool(16);

        for (final Pixel pixel : mResolution) {
            Runnable task = new TraceTask(scene, pixel, mView, image);
            executor.execute(task);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            throw new RuntimeException("Scene generation exceeded timeout.", e);

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

class TraceTask implements Runnable {

    TraceTask(final Scene scene, final Pixel pixel, final ViewPort view, final Image image) {
        mScene = scene;
        mPixel = pixel;
        mView = view;
        mImage = image;
    }

    private final Scene mScene;
    private final Pixel mPixel;
    private final ViewPort mView;
    private final Image mImage;

    @Override
    public void run() {
        final Ray ray = mPixel.intersect(ORIGIN, mView);
        final Color color = mScene.trace(ray);

        mPixel.setColor(color);
        mImage.write(mPixel);
    }
}