import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator
{
    static final int MAX_ITERATIONS = 2000;

    public void getInitialRange(Rectangle2D.Double range)
    {
        recenterAndZoomRange(range, -2, -1.5, 3);
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

        return iterations;
    }

    public static void main(String[] args)
    {
        Mandelbrot man = new Mandelbrot();

        System.out.println(man.numIterations(1, 0));
    }
}