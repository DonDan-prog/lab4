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

    /** Square just for Mandelbrot's fractal */
    public MandelbrotComplex square(MandelbrotComplex number)
    {
        double newReal = number.real * number.real - number.image * number.image;
        double newImaginary = 2 * number.real * number.image;

        MandelbrotComplex newComplexNumber = new MandelbrotComplex(newReal, newImaginary);
        return newComplexNumber;
    }

    public MandelbrotComplex add(MandelbrotComplex number)
    {
        return new MandelbrotComplex(this.real+number.real, this.image+number.image);
    }

    public double square_abs()
    {
        return this.real * this.real + this.image * this.image;
    }

    public void print()
    {
        System.out.println(String.format("%f%s%fi", real, image > 0 ? "+" : "", image));
    }
}
