package tracer;

import scene.Scene;
import scene.SceneBuilder;
import scene.SceneFile;
import scene.primitives.Resolution;
import scene.primitives.ViewPort;

import java.io.FileNotFoundException;

public class RayTracer {

    public RayTracer(final Resolution resolution, final ViewPort view) {
        mResolution = resolution;
        mView = view;
    }

    final Resolution mResolution;
    final ViewPort mView;

    public void trace(final Scene scene) {
        // main loop goes here.
    }

    public void write(final OutputFile file) {

    }

    public static void main(String[] args) throws FileNotFoundException {
        final SceneFile sceneFile = new SceneFile(args[0]);
        final SceneBuilder builder = sceneFile.decode();

        final Scene scene = builder.buildScene();
        final RayTracer tracer = builder.buildRayTracer();
        
        tracer.trace(scene);
        tracer.write(new OutputFile());
    }
}