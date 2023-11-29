package com.tracer.scene.decoders;

import com.tracer.scene.SceneBuilder;
import com.tracer.scene.objects.Sphere;
import com.tracer.scene.primitives.Vector;

import java.awt.*;
import java.util.StringTokenizer;

public class SphereDecoder extends AbstractDecoder {

    @Override
    public SceneBuilder decode(final StringTokenizer parts, final SceneBuilder sceneBuilder) {
        final String name = parts.nextToken();
        Vector position = parseVector(parts, 3);
        position = new Vector(position.x(), position.y(), position.z(), 1);
        final Vector scale = parseVector(parts, 3);
        final Color color = parseColor(parts);
        final Vector constants = parseVector(parts, 4);
        final float specular = parseFloat(parts);

        return sceneBuilder.withSphere(new Sphere(name, position, scale, color, constants, specular));
    }
}
