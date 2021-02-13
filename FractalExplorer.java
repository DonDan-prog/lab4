import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class FractalExplorer 
{
    public static void main(String[] args)
    {
        Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, 800, 800);
        FractalExplorer fr = new FractalExplorer(rect);
        fr.start();
    }

    /** Size of display */
    private int displaySize;

    /** Actual display for Mandelbrot */
    private JImageDisplay imageDisplay;

    /** Abstract fractal generator */
    private FractalGenerator fractalGenerator;

    /** Range of fractal */
    private Rectangle2D.Double range;

    /** Class for handle mouse actions */
    private class ClickHandler implements MouseListener
    {
        public void mousePressed(MouseEvent e)
        {
            /** Get the click position and transform them into fractal position */
            Point click = e.getPoint();
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, click.x);
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, click.y);

            /** When click on left button - zoom in, right - zoom out */
            if(e.getButton() == e.BUTTON1)
            {
                fractalGenerator.recenterAndZoomRange(range, xCoord,  yCoord, 0.5);
                System.out.println(String.format("Center spot: x=%f, y=%f", xCoord, yCoord));
                drawFractal();
            }
            else if(e.getButton() == e.BUTTON3)
            {
                fractalGenerator.recenterAndZoomRange(range, xCoord,  yCoord, 2);
                System.out.println(String.format("Center spot: x=%f, y=%f", xCoord, yCoord));
                drawFractal();
            }
        }

        /** These functions not need in implementation */
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
    }

    /** Default constructor */
    FractalExplorer(Rectangle2D.Double rect)
    {
        this.range = rect;
        displaySize = (int)rect.width;

        imageDisplay = new JImageDisplay(displaySize, displaySize);
        fractalGenerator = new Mandelbrot();

        fractalGenerator.getInitialRange(range);
    }

    /** App launch function */
    public void start()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                drawFractal();
            }
        });
    }

    /** Gui construction function */
    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Mandelbrot Set");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        imageDisplay.addMouseListener(new ClickHandler());

        JButton resetButton = new JButton("Reset display");
        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                imageDisplay.clearImage();
                fractalGenerator.getInitialRange(range);
                drawFractal();
            }
        });

        contentPane.add(imageDisplay, BorderLayout.NORTH);
        contentPane.add(resetButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /** Fractal draw function */
    public void drawFractal()
    {
        for(int y = 0; y < displaySize; ++y)
        {
            for(int x = 0; x < displaySize; ++x)
            {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);

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
        imageDisplay.repaint();
    }
}
