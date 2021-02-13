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

    /** Addition operation */
    public void add(MandelbrotComplex other)
    {
        this.real += other.real;
        this.image += other.image;
    }

    /** Square just for Mandelbrot's fractal */
    public void square()
    {
        this.real = this.real * this.real - this.image * this.image;
        this.image = 2 * this.real * this.image;
    }

    /** Java-style equality function */
    public boolean equals(MandelbrotComplex other)
    {
        return (Math.abs(real - other.real) <= epsilon) && (Math.abs(image - other.image) <= epsilon);
    }

    public double mod_squared()
    {
        return real * real + image * image;
    }

    public void print()
    {
        System.out.println(String.format("%f%s%fi", real, image > 0 ? "+" : "", image));
    }
}
