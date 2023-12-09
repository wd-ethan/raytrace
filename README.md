# CSC 305 Assignment #3 - Java Raytracer

This repo contains a full raytracer implementation which supports sphere objects
and a depth of three reflections. It does not currently support refracted light rays.
It is written with future extension in mind.

Author: Ethan Wright

## Requirements

This project requires the following dependencies:

- [JDK 20](https://jdk.java.net/21/) (or newer)
- [JAMA 1.0.2](https://math.nist.gov/javanumerics/jama/) (included in this repo)

## Build and Run

### Jar

There is a pre-compiled jar of this project to simplify the run process.

     java -jar RayTracer.jar <input.txt>

### Compiled

Alternatively you can compile and run this program locally.

First run,

     javac -cp .:lib/Jama-1.0.2.jar RayTracer.java

followed by,

     java -cp .:lib/Jama-1.0.2.jar RayTracer <input.txt>

In both cases, `<input.txt>` is a valid file path which contains input as per the
assignment description. 

Ex) `./assets/testCases/testSample.txt`

## Project Structure

Note: there are pre-compiled `ppm` images in `./assets/preProcessed/`.

Below is a project overview which outlines some important files:

```
├── README.md -- You are here.
├── RayTracer.jar -- Pre-compiled jar of this project.
├── RayTracer.java -- Class which contains the main method
├── assets
│   ├── preProcessed
│   │   └── A collection of pre-processe test cases.
│   └── testCases
│       └── The test case input files.
├── com
│   └── tracer
│       ├── Tracer.java -- Contains multi-threaded pixel colour calculations.
│       ├── primitives
│       │   └── Classes which are primitive to ray tracing.
│       └── scene
│           ├── Scene.java -- Contains main logic of testing intersections and shadow rays.
│           ├── decoders
│           │   └── Decoding infastructure for parsing file.
│           ├── objects
│           │   └── Objects that can live in a scene (ex. Sphere).
│           ├── primitives
│           │   └── Classes which are primitive to a scene.
│           └── util
│               └── Static utility methods.
└── lib
    └── Jama-1.0.2.jar -- Jar which is used for matrix math.
```

## Attributions

I used the [JAMA 1.0.2](https://math.nist.gov/javanumerics/jama/) library in this project. All other code is written by me.
