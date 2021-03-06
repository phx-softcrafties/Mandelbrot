Mandelbrot
==========

Sample implementations of Mandelbrot set calculations.

![Mandelbrot set image][image]

The [Mandelbrot set][1] is defined by a set of parameterized functions,

f<sub>c</sub>(z) = z<sup>2</sup> + c

where *z* and *c* are complex numbers. For any complex number *c*, we can then
ask whether the repeated application of f<sub>c</sub> starting from *z* = (0, 0) 
stays bound or diverges. The points *c* on the complex plane for which repeated
applications of the corresponding map f<sub>c</sub> remain bound make up the
Mandelbrot set.

Scala implentation
------------------

As a programming exercise, I am attempting to code the Mandelbrot set in Scala.
I want to see how efficient this is, both in lines of code and in runtime speed.
I also want to see if it makes sense to program in a purely functional style.

Further, this gives an opportuntity to use Scala to call some Java graphics
commands to render the Mandelbrot set as an image.

Actors
------

This calculation is often used as an MPI demo, since the calculation of each
point is decoupled, hence an embarassingly parallel algorithm is possible.
This should easy to implement using Actors, and it might be a fun benchmark
for testing diffent sizes for the Actor pool.

Actors have not been implemented yet.

Build and Compile
-----------------

Build with 
```bash
scalac -cp scalatest_2.9.0-1.8.jar MandelbrotTest.scala Mandelbrot.scala
```

Run unit tests with
```bash
scala -cp scalatest_2.9.0-1.8.jar org.scalatest.run MandelbrotSuite
```

Run code to generate 1280x800 image.png using
```bash
scala -cp . MandelbrotArtist
```


[1]: http://en.wikipedia.org/wiki/Mandelbrot_set
[image]: https://raw.github.com/phx-softcrafties/Mandelbrot/master/sample.png
[wikiimage]: http://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Mandel_zoom_00_mandelbrot_set.jpg/640px-Mandel_zoom_00_mandelbrot_set.jpg

