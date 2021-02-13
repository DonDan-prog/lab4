import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator
{
    static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.height = 3;
        range.width = 3;
    }

    public int numIterations(double x, double y)
    {
        MandelbrotComplex z0 = new MandelbrotComplex(x, y);
        MandelbrotComplex z = z0;
        int iterations = 0;

        while(iterations < MAX_ITERATIONS && z.square_abs() < 4)
        {
            z = z.square(z).add(z0);
            iterations++;
        }

        if(iterations == MAX_ITERATIONS)
            return -1;
        return iterations;
    }
}