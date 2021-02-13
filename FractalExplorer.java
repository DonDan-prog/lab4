import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class FractalExplorer 
{
    public static void main(String[] args)
    {
        Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, 500, 500);
        FractalExplorer fr = new FractalExplorer(rect);
        fr.start();
    }

    /** Size x of display in pixels */
    private int width;    

    /** Size y of display in pixels */
    private int height;

    /** Actual display for Mandelbrot */
    private JImageDisplay imageDisplay;

    /** Mandelbrot generator */
    private Mandelbrot fractalGenerator;

    /** Range of fractal */
    private Rectangle2D.Double range;

    

    FractalExplorer(Rectangle2D.Double rect)
    {
        this.range = rect;

        width = (int)this.range.width;
        height = (int)this.range.height;

        imageDisplay = new JImageDisplay(width, height);
        fractalGenerator = new Mandelbrot();

        fractalGenerator.getInitialRange(range);
    }

    public void start()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                drawFractal();
            }
        });
    }

    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Mandelbrot Set");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JButton resetButton = new JButton("Reset display");
        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                imageDisplay.clearImage();
                imageDisplay.repaint();
            }
        });
        

        contentPane.add(imageDisplay, BorderLayout.NORTH);
        contentPane.add(resetButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void drawFractal()
    {
        for(int y = 0; y < height; ++y)
        {
            for(int x = 0; x < width; ++x)
            {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, width, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, height, y);

                int iterations = fractalGenerator.numIterations(xCoord, yCoord);

                if(iterations == -1)
                {
                    imageDisplay.drawPixel(x, y, 0);
                }
                else
                {
                    float hue = 7.0f + (float)iterations/200.f;
                    int rgbColor = Color.HSBtoRGB(hue, 1.f, 1.f);
                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
    }
}
