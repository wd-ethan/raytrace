package com.tracer.scene.decoders;

import com.tracer.scene.SceneBuilder;

import java.util.StringTokenizer;

public class AmbientDecoder extends AbstractDecoder
{
    @Override
    public SceneBuilder decode(StringTokenizer parts, SceneBuilder sceneBuilder) {
        return sceneBuilder.withAmbient(parseVector(parts, 3));
    }
}
