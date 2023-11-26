package src.main.java.com.tracer.scene.decoders;

import Jama.Matrix;
import src.main.java.com.tracer.scene.primitives.Vector;

import java.awt.*;
import java.util.StringTokenizer;

public abstract class AbstractDecoder implements IDecoder {

    Vector parseVector(final StringTokenizer tokenizer, final int d) {
        return new Vector(
            new Matrix(new double[][]{
                    {Double.parseDouble(tokenizer.nextToken())},
                    {Double.parseDouble(tokenizer.nextToken())},
                    {d > 2 ? Double.parseDouble(tokenizer.nextToken()) : 0},
                    {d > 3 ? Double.parseDouble(tokenizer.nextToken()) : 0}
            }      
        )
        );
    }

    Color parseColor(final StringTokenizer tokenizer) {
        return new Color(
                Float.parseFloat(tokenizer.nextToken()),
                Float.parseFloat(tokenizer.nextToken()),
                Float.parseFloat(tokenizer.nextToken()));
    }

    float parseFloat(final StringTokenizer tokenizer) {
        return Float.parseFloat(tokenizer.nextToken());
    }

    int parseInt(final StringTokenizer tokenizer) {
        return Integer.parseInt(tokenizer.nextToken());
    }
}
