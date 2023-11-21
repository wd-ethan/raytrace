package raytrace.tracer;

import raytrace.scene.primitives.Image;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile {

    public OutputFile(final String file, final Image image) {
        mImage = image;
        mFilename = file;
    }

    private final Image mImage;
    private final String mFilename;

    public void write() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mFilename))) {
            writer.write(mImage.asPpm());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
