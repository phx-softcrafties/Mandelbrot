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


class ImageBuffer(width:Int, height:Int) {

    import java.awt.image.BufferedImage

    val buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    def rgb(red:Int, green:Int, blue:Int) = (((red << 8) + green) << 8) + blue

    def color(result:(Boolean, Int)) = {
        if (result._1) {
            colormap(result._2)
        } else {
            rgb(0, 0, 0)
        }
    }

    def colormap(age:Int) = {
        rgb(age * (100-age) / 15, 5 * age / 2,  130 - age)
    }

    def setPixel(x:Int, y:Int, rgbcolor:Int) {
        buffer.setRGB(x, y, rgbcolor)
    }

    def write(filename:String) {
        import java.io.File
        val outputfile = new File(filename)
        import javax.imageio.ImageIO
        ImageIO.write(buffer, "png", outputfile)
    }
}

object MandelbrotArtist {
    def main(args: Array[String]) = {
        println("\nMandlelbrot image generator\n")

        val width = 1280
        val height = 800
        val buffer = new ImageBuffer(width, height)
        val maxIterations = 100

        for (i <- 0 until width) {
            val x = -2.2 + i * 3.2 / width
            for (j <- 0 until height) {
                val y = -1.3 + j * 2.6 / height
                val c = new Complex(x, y)
                val point = new MandelbrotPoint(c)
                val result = point diverges(maxIterations)
                val color = buffer color(result)
                buffer.setPixel(i, j, color)
            }
        }

        buffer.write("image.png")
    }
}
