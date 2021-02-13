import javax.swing.JComponent;
import java.awt.image.*;
import java.awt.*;

public class JImageDisplay extends JComponent
{
    /** Image buffer for holding picture */
    private BufferedImage image;

    /** Constructor of image */
    JImageDisplay(int w, int h)
    {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Dimension size = new Dimension(w, h);
        setPreferredSize(size);
    }

    /** Function to draw the image */
    public void paintComponent(Graphics g)
    {
        paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    /** Function to clearing image */
    public void clearImage()
    {
        for(int x = 0; x < image.getWidth(); ++x)
        {
            for(int y = 0; y < image.getHeight(); ++y)
            {
                image.setRGB(x, y, 0); // 0 - is black, as i think
            }
        }
    }

    /** Function to setting the pixel */
    public void drawPixel(int x, int y, int rgbColor)
    {
        image.setRGB(x, y, rgbColor);
    }
}