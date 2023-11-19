import scene.Scene;
import scene.SceneFile;

import java.io.FileNotFoundException;

public class RayTracer {

    RayTracer(final Scene scene) {
        mScene = scene;
    }

    final Scene mScene;

    public void trace() {
        // main loop goes here.
    }

    public static void main(String[] args) throws FileNotFoundException {
        final SceneFile sceneFile = new SceneFile(args[0]);

        final Scene scene = sceneFile.decode();
    }
}