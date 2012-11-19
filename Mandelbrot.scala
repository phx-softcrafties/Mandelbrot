object Mandelbrot {

    def iterate(z:Complex, c:Complex) = (z times z) plus c

    def isBig(z:Complex) = (z abs) > 2.0

    def diverges(c:Complex, maxIterations:Int) = {
        var z = new Complex(0.0, 0.0)
        var count = 0
        while (!isBig(z) && count < maxIterations) {
            z = iterate(z, c); count = count + 1
        }
        (isBig(z), count)
    }
}

