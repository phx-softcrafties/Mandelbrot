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

    test("new point has age 0") {
        val point = new MandelbrotPoint(zero, zero, 0)
        (point.age) should be (0)
    }

    test("a twice-iterated point has age 2") {
        val point = new MandelbrotPoint(zero, zero, 0)
        (point.iterate.iterate.age) should be (2)
    }
 
 
    test("first iteration of 1.0 map is 1.0") {
        val c = new Complex(1.0, 0.0)
        val point = new MandelbrotPoint(c)
        val expect = new Complex(1.0, 0.0)
        shouldBeEqual(point.iterate.z, expect) 
    }
 
    test("second iteration of 1.0 map is 2.0") {
        val c = new Complex(1.0, 0.0)
        val point = new MandelbrotPoint(c)
        val expect = new Complex(2.0, 0.0)
        shouldBeEqual(point.iterate.iterate.z, expect) 
    }

    test("second iteration of -1.0 map is 0.0") {
        val c = new Complex(-1.0, 0.0)
        val point = new MandelbrotPoint(c)
        val expect = new Complex(0.0, 0.0)
        shouldBeEqual(point.iterate.iterate.z, expect) 
    }

    test("second iteration of 1.0i map is -1.0 + 1.0i") {
        val c = new Complex(0.0, 1.0)
        val point = new MandelbrotPoint(c)
        val expect = new Complex(-1.0, 1.0)
        shouldBeEqual(point.iterate.iterate.z, expect) 
    }

    test("a point at -1.5 + 1.5i is big") {
        val z = new Complex(-1.5, 1.5)
        val point = new MandelbrotPoint(z, zero)
        point should be ('big)
    }

    test("a point at -1.0 + 1.0i is not big") {
        val z = new Complex(-1.0, 1.0)
        val point = new MandelbrotPoint(z, zero)
        point should not be ('big)
    }
 
    test("map 1.1 gets big in two iterations") {
        val c = new Complex(1.1, 0.0)
        val point = new MandelbrotPoint(c)
        val result = point diverges(maxIterations)
        result should be (true, 2)
    }

    test("map 0.0 never gets big") {
        val point = new MandelbrotPoint(zero)
        val result = point diverges(maxIterations)
        result should be (false, maxIterations)
    }

    test("map -0.6 + 0.5i gets big in 12 iterations") {
        val c = new Complex(-0.6, 0.5)
        val point = new MandelbrotPoint(c)
        val result = point diverges(maxIterations)
        result should be (true, 12)
    }
}
