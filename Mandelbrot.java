import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator
{
    static final int MAX_ITERATIONS = 2000;

    /** Sets the initial values for Mandelbrot set */
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.height = 3;
        range.width = 3;
    }

    /** Fractal function that actually counts the num of iterations to check if (x,y) in Mandelbrot's set */
    public int numIterations(double x, double y)
    {
        MandelbrotComplex z0 = new MandelbrotComplex(x, y);
        MandelbrotComplex z = new MandelbrotComplex();
        int iterations = 0;

        while(iterations < MAX_ITERATIONS && z.squareAbs() < 4)
        {
            z.squareAndAdd(z0);
            iterations++;
        }

        if(iterations == MAX_ITERATIONS)
            return -1;
        return iterations;
    }
}