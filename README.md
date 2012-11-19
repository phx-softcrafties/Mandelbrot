Mandelbrot
==========

Sample implementations of Mandelbrot set calculations.

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

[1]: http://en.wikipedia.org/wiki/Mandelbrot_set
