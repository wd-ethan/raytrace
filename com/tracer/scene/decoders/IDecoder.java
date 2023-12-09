package com.tracer.scene.decoders;

import com.tracer.scene.SceneBuilder;

import java.util.StringTokenizer;

/**
 * Knows how to parse input text into objects.
 */
public interface IDecoder {

    SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder);
}
