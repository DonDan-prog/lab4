import java.awt.geom.Rectangle2D;

import java.awt.*;
import javax.swing.*;

public class FractalExplorer 
{
    /** Size x of display in pixels */
    private int width;    

    /** Size y of display in pixels */
    private int height;

    /** Actual display for Mandelbrot */
    private JImageDisplay imageDisplay;

    /** Mandelbrot generator */
    private FractalGenerator fractalGenerator;

    /** Range of fractal */
    private Rectangle2D.Double rect;

    FractalExplorer(Rectangle2D.Double rect)
    {
        this.rect = rect;

        width = (int)this.rect.width;
        height = (int)this.rect.height;

        imageDisplay = new JImageDisplay(width, height);
        fractalGenerator = new Mandelbrot();
    }

    public void start()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
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
        

        contentPane.add(imageDisplay, BorderLayout.NORTH);
        contentPane.add(resetButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main(String[] args)
    {
        Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, 250, 250);
        FractalExplorer fr = new FractalExplorer(rect);
        fr.start();
    }
}
