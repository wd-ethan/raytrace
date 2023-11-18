package scene;

import scene.decoders.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class SceneFile {

    private static final Map<String, IDecoder> DECODER_MAP = Map.of(
            "SPHERE", new SphereDecoder(),
            "NEAR", new ViewPortDecoder(),
            "LIGHT", new LightDecoder(),
            "BACK", new ColorDecoder(),
            "AMBIENT", new ColorDecoder(),
            "OUTPUT", new NameDecoder()
    );

    SceneFile(final String filepath) {
        mFilepath = filepath;
    }

    private final String mFilepath;
    private final SceneBuilder mScene = new SceneBuilder();

    public Scene decode() throws FileNotFoundException {
        try {
            final List<String> lines  = Files.readAllLines(Paths.get(mFilepath));
            final FileInputStream s = new FileInputStream(mFilepath);

            for (final String line : lines) {
                final String[] parts = line.split(" ");
                final IDecoder decoder = DECODER_MAP.get(parts[0]);

                decoder.decode(parts, mScene);
            }

        } catch (IOException|InvalidPathException e) {
            throw new FileNotFoundException("Seems the file you passed is no good.");
        }

        return mScene.build();
    }

    private void decodeViewPort(final String[] parts) {}

    private void decodeLeft(final String[] parts) {}

    private void decodeRight(final String[] parts) {}

    private void decodeBottom(final String[] parts) {}

    private void decodeTop(final String[] parts) {}

    private void decodeResolution(final String[] parts) {}

    private void decodeSphere(final String[] parts) {}

    private void decodeLight(final String[] parts) {}

    private void decodeBack(final String[] parts) {}

    private void decodeAmbient(final String[] parts) {}

    private void decodeOutput(final String[] parts) {}
}
