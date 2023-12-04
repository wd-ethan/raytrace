# CSC 305 Assignment #3 - Java Raytracer

This repo contains a full raytracer implementation which supports sphere objects
and a depth of three reflections. It does not currently support refracted light rays.
It is written with future extension in mind.

## Requirements

This project requires the following dependencies:

- [JDK 20](https://jdk.java.net/21/) (or newer)
- [JAMA 1.0.2](https://math.nist.gov/javanumerics/jama/)

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

## Project Structure
