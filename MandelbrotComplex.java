public class MandelbrotComplex 
{
    /** Real part of complex number */
    private double real;

    /** Imagine part of complex number */
    private double image;

    /** Constant which shows the approximate equality */
    private final double epsilon = 0.000001;

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

    /** Constructor for copy Complex */
    MandelbrotComplex(MandelbrotComplex other)
    {
        this(other.real, other.image);
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

    public double abs()
    {
        return Math.hypot(this.real, this.image);
    }

    public void print()
    {
        System.out.println(String.format("%f%s%fi", real, image > 0 ? "+" : "", image));
    }
}
