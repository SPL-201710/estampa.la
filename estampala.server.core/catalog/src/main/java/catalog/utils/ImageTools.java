package catalog.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;


public class ImageTools {

  public static byte[] renderImage(BufferedImage img, String color) throws IOException {
    BufferedImage bi = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TRANSLUCENT);
    Graphics2D g2d = bi.createGraphics();

    BufferedImage subImg = img.getSubimage(0, 0, bi.getWidth(), bi.getHeight());
    g2d.drawImage(subImg, 0, 0, bi.getWidth(), bi.getHeight(), null);

    for (int x = 0; x < bi.getWidth(); x++) {
      for (int y = 0; y < bi.getHeight(); y++) {
        if (bi.getRGB(x, y) == Color.WHITE.getRGB() && !isTransparent(bi, x, y)) {
          bi.setRGB(x, y, hex2Rgb(color).getRGB());
        }
      }
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(bi, "png", baos);

    return baos.toByteArray();
  }

  private static Color hex2Rgb(String colorStr) {
    return new Color(
      Integer.valueOf( colorStr.substring(1, 3), 16),
      Integer.valueOf( colorStr.substring(3, 5), 16),
      Integer.valueOf( colorStr.substring(5, 7), 16) );
  }

  private static boolean isTransparent(BufferedImage img, int x, int y ) {
    int pixel = img.getRGB(x,y);
    if( (pixel>>24) == 0x00 ) {
      return true;
    }
    return false;
  }
}
