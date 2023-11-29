# CSC 305 Assignment #3 - Java Raytracer

This repo contains a full raytracer implementation which supports sphere objects
and a depth of three reflections. It is written with future extension in mind.

## Requirements

This project requires the following dependencies:

- [JDK 20](https://jdk.java.net/21/) (or newer)

## Build and Run

### Jar

There is a pre-compiled jar of this project to simplify the run process.

     java -jar raytrace.jar <input.txt>

### Compiled

Alternatively you can compile and run this program locally.

First run,

     javac -cp .:lib/Jama-1.0.2.jar com/tracer/RayTracer.java

followed by,

     java -cp .:lib/Jama-1.0.2.jar com.tracer.RayTracer <input.txt>

## Project Structure

```
├── com
│        └── tracer
│            ├── OutputFile.java
│            ├── RayTracer.java
│            ├── primitives
│            └── scene
│           ├── Scene.class
│           ├── Scene.java
│           ├── SceneBuilder.class
│           ├── SceneBuilder.java
│           ├── SceneFile.class
│           ├── SceneFile.java
│           ├── decoders
│           │   ├── AbstractDecoder.class
│           │   ├── AbstractDecoder.java
│           │   ├── AmbientDecoder.class
│           │   ├── AmbientDecoder.java
│           │   ├── BackgroundDecoder.class
│           │   ├── BackgroundDecoder.java
│           │   ├── BottomPlaneDecoder.class
│           │   ├── BottomPlaneDecoder.java
│           │   ├── IDecoder.class
│           │   ├── IDecoder.java
│           │   ├── LeftPlaneDecoder.class
│           │   ├── LeftPlaneDecoder.java
│           │   ├── LightDecoder.class
│           │   ├── LightDecoder.java
│           │   ├── NameDecoder.class
│           │   ├── NameDecoder.java
│           │   ├── NearPlaneDecoder.class
│           │   ├── NearPlaneDecoder.java
│           │   ├── ResolutionDecoder.class
│           │   ├── ResolutionDecoder.java
│           │   ├── RightPlaneDecoder.class
│           │   ├── RightPlaneDecoder.java
│           │   ├── SphereDecoder.class
│           │   ├── SphereDecoder.java
│           │   ├── TopPlaneDecoder.class
│           │   └── TopPlaneDecoder.java
│           ├── objects
│           │   ├── AbstractSceneObject.class
│           │   ├── AbstractSceneObject.java
│           │   ├── ISceneObject.class
│           │   ├── ISceneObject.java
│           │   ├── Sphere.class
│           │   └── Sphere.java
│           ├── primitives
│           │   ├── Image.class
│           │   ├── Image.java
│           │   ├── Intersection.class
│           │   ├── Intersection.java
│           │   ├── Light.class
│           │   ├── Light.java
│           │   ├── Pixel.class
│           │   ├── Pixel.java
│           │   ├── Ray.class
│           │   ├── Ray.java
│           │   ├── Resolution$1.class
│           │   ├── Resolution.class
│           │   ├── Resolution.java
│           │   ├── Vector.class
│           │   ├── Vector.java
│           │   ├── ViewPort.class
│           │   └── ViewPort.java
│           └── util
│               ├── MyUtils.class
│               └── MyUtils.java
```