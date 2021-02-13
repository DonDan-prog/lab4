public class MandelbrotComplex 
{
    /** Real part of complex number */
    private double real;

    /** Imagine part of complex number */
    private double image;

    /** Constructor for spot */
    MandelbrotComplex(double r, double i)
    {
        this.real = r;
        this.image = i;
    }

    /** Default constructor */
    MandelbrotComplex()
    {
        this(0, 0);
    }

    /** Square and addition for little optimization just for Mandelbrot's fractal */
    public void squareAndAdd(MandelbrotComplex number)
    {
        double new_real = this.real * this.real - this.image * this.image + number.real;
        double new_imag = 2 * this.real * this.image + number.image;

        this.real = new_real;
        this.image = new_imag;
    }

    /** Returns squared module for optimiztion purposes */
    public double squareAbs()
    {
        return this.real * this.real + this.image * this.image;
    }

    /** Print function for complex number */
    public void print()
    {
        System.out.println(String.format("%f%s%fi", real, image > 0 ? "+" : "", image));
    }
}
