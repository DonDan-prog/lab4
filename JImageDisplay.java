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

        clearImage();
    }

    /** Function to draw the image */
    protected void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    /** Function to clearing image */
    public void clearImage()
    {
        for(int y = 0; y < image.getHeight(); ++y)
        {
            for(int x = 0; x < image.getWidth(); ++x)
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