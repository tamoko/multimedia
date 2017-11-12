//auto.java
//
import java.applet.*;
import java.awt.*;
import java.awt.image.*;

public class auto extends Applet {

  Image img;

  int w = 1280;
  int h = 960;
  
  int pix[] = new int[w*h];
  IndexColorModel cm;

  public void init() {
    byte r[] = {(byte)0x99, (byte)0x00};
    byte g[] = {(byte)0x99, (byte)0x00};
    byte b[] = {(byte)0x99, (byte)0xff};

    cm = new IndexColorModel(8,2,r,g,b);

    circle();
  }

  public void circle() {
  	int width = 1280, height = 960, max = 1000;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int black = 0x000000, white = 0xFFFFFF;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
            	 int p = row * width + col;
                double c_re = (col - width/2)*4.0/width;
                double c_im = (row - height/2)*4.0/width;
                double x = 0, y = 0;
                int iterations = 0;
                while (x*x+y*y < 4 && iterations < max) {
                    double x_new = x*x-y*y+c_re;
                    y = 2*x*y+c_im;
                    x = x_new;
                    iterations++;
                } 
                if (iterations < max) pix[p] = 0;
                else pix[p] = 1;
            }
        }
    }

  public void paint(Graphics g) {
    MemoryImageSource mimg = new MemoryImageSource(w,h,cm,pix,0,w);
    img = createImage(mimg);

    g.drawImage(img,0,0,this);
  }
}
