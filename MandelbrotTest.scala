import org.scalatest.{FunSuite, BeforeAndAfterEach}
import org.scalatest.matchers.ShouldMatchers._

class MandelbrotSuite extends FunSuite with BeforeAndAfterEach {

    val zero = new Complex(0.0, 0.0) 
    val epsilon = 1e-12
    val maxIterations = 100

    def shouldBeEqual(z1:  Complex, z2: Complex) = {
        (z1.re) should be (z2.re plusOrMinus epsilon)
        (z1.im) should be (z2.im plusOrMinus epsilon)
    }
 
    test("first iteration of 1.0 map is 1.0") {
        val c = new Complex(1.0, 0.0)
        val result = Mandelbrot.iterate(zero, c)
        val expect = new Complex(1.0, 0.0)
        shouldBeEqual(result, expect) 
    }
 
    test("second iteration of 1.0 map is 2.0") {
        val c = new Complex(1.0, 0.0)
        val result = Mandelbrot.iterate(
                         Mandelbrot.iterate(zero, c), c)
        val expect = new Complex(2.0, 0.0)
        shouldBeEqual(result, expect) 
    }

    test("second iteration of -1.0 map is 0.0") {
        val c = new Complex(-1.0, 0.0)
        val result = Mandelbrot.iterate(
                         Mandelbrot.iterate(zero, c), c)
        val expect = new Complex(0.0, 0.0)
        shouldBeEqual(result, expect) 
    }

    test("second iteration of 1.0i map is -1.0 + 1.0i") {
        val c = new Complex(0.0, 1.0)
        val result = Mandelbrot.iterate(
                         Mandelbrot.iterate(zero, c), c)
        val expect = new Complex(-1.0, 1.0)
        shouldBeEqual(result, expect) 
    }

    test("the number -1.5 + 1.5i is big") {
        val z = new Complex(-1.5, 1.5)
        assert(Mandelbrot.isBig(z))
    }

    test("the number -1.0 + 1.0i is not big") {
        val z = new Complex(-1.0, 1.0)
        assert(!Mandelbrot.isBig(z))
    }

    test("map 1.1 gets big in two iterations") {
        val c = new Complex(1.1, 0.0)
        val result = Mandelbrot.diverges(c, maxIterations)
        result should be (true, 2)
    }

    test("map 0.0 never gets big") {
        val c = new Complex(0.0, 0.0)
        val result = Mandelbrot.diverges(c, maxIterations)
        result should be (false, maxIterations)
    }

    test("map -0.6 + 0.5i gets big in 12 iterations") {
        val c = new Complex(-0.6, 0.5)
        val result = Mandelbrot.diverges(c, maxIterations)
        result should be (true, 12)
    }
}
