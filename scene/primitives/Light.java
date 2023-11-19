package scene.primitives;

import scene.primitives.Vector;

public class Light {

    public Light(final String name, final Vector position, final Vector intensity){
        mName = name;
        mPosition = position;
        mIntenstity = intensity;
    }

    private final String mName;
    private final Vector mPosition;
    private final Vector mIntenstity;
}
