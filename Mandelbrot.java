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

    public int numIterations(double a, double b)
    {
        MandelbrotComplex z0 = new MandelbrotComplex(a, b);
        MandelbrotComplex z = new MandelbrotComplex();
        int iterations = 0;

        while(iterations < MAX_ITERATIONS && z.mod_squared() < 4)
        {
            z.square();
            z.add(z0);
            iterations++;
        }

        if(iterations >= MAX_ITERATIONS)
            return -1;
        return iterations;
    }
}