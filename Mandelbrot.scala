class MandelbrotPoint(cc: Complex, zc: Complex, agec: Int) {
    val c = cc
    val z = zc
    val age = agec 

    def this(zc: Complex, cc: Complex) = this(cc, zc, 0)
    def this(cc: Complex) = this(cc, new Complex(0.0, 0.0), 0)

    def big() = (z abs) > 2.0

    def iterate = new MandelbrotPoint(c, (z times z) plus c, age + 1)

    def diverges(maxIterations:Int) : (Boolean, Int) = {
        val newPoint = iterate
        if (newPoint doneIterating(maxIterations)) {
            (newPoint big, newPoint age)
        } else {
            newPoint diverges(maxIterations)
        }
    }

    def doneIterating(maxIterations: Int) = maxIterations == age || big
}
