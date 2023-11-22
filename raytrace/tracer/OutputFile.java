package raytrace.tracer;

import raytrace.scene.primitives.Image;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile {

    public OutputFile(final String file) {
        mFilename = file;
    }

    private final String mFilename;

    public void write(final Image image) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mFilename))) {
            writer.write(image.asPpm());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
