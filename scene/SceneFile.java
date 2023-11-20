package scene;

import scene.decoders.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.StringTokenizer;

public class SceneFile {

    private static final Map<String, IDecoder> DECODER_MAP = Map.ofEntries(
            Map.entry("SPHERE", new SphereDecoder()),
            Map.entry("NEAR", new NearPlaneDecoder()),
            Map.entry("LEFT", new LeftPlaneDecoder()),
            Map.entry("RIGHT", new RightPlaneDecoder()),
            Map.entry("BOTTOM", new BottomPlaneDecoder()),
            Map.entry("TOP", new TopPlaneDecoder()),
            Map.entry("RES", new ResolutionDecoder()),
            Map.entry("LIGHT", new LightDecoder()),
            Map.entry("BACK", new BackgroundDecoder()),
            Map.entry("AMBIENT", new BackgroundDecoder()),
            Map.entry("OUTPUT", new NameDecoder())
    );

    public SceneFile(final String filepath) {
        mFilepath = filepath;
    }

    private final String mFilepath;

    public SceneBuilder decode() throws FileNotFoundException {
        SceneBuilder scene = new SceneBuilder();

        try {
            final String file = Files.readString(Paths.get(mFilepath));
            final StringTokenizer tokenizer = new StringTokenizer(file, " \n\t");

            while (tokenizer.hasMoreTokens()) {
                final IDecoder decoder = DECODER_MAP.get(tokenizer.nextToken());
                scene = decoder.decode(tokenizer, scene);
            }

        } catch (IOException|InvalidPathException e) {
            throw new FileNotFoundException("Seems the file you passed is no good.");
        }

        return scene;
    }
}
