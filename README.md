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
│            ├── RayTracer.java : Main class
│            ├── primitives
│                   └── Contains Ray tracing primitive objects
│            └── scene
│                ├── decoders
│                       └── Decoders which convert <input> into primitive objects
│                ├── objects
│                       └── Objects currently supported for tracing
│                ├── primitives
│                       └── Primitive objects
│                └── util
│                       └── Utility functions
```