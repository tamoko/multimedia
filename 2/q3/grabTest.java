//grabTest.java
//
import java.applet.*;
import java.awt.*;
import java.awt.image.*;

public class grabTest extends Applet {
  
  Image img;
  Image new_img;

  int w = 0;
  int h = 0;

  int pix[];
  int new_pix[];

  public void init() {

    img = getImage(getCodeBase(),"accra.jpg");

    MediaTracker mt = new MediaTracker(this);
    mt.addImage(img,0);
    try{
      mt.waitForID(0);
    } catch (InterruptedException e){}

    w = img.getWidth(this);
    h = img.getHeight(this);

    pix = new int[w*h];
    new_pix = new int[w*h];

    setPix();
  }

  void setPix() {
    try{
      PixelGrabber pg = new PixelGrabber(img,0,0,w,h,pix,0,w);
      pg.grabPixels();
    }
    catch(InterruptedException e){}

    
    for(int y=0; y<h; y++) {
    	for(int x=0; x<w; x++) {
    		new_pix[y*w+w-1-x] = pix[y*w+x];
    	}
    }

    MemoryImageSource mimg = new MemoryImageSource(w,h,new_pix,0,w);
    new_img = createImage(mimg);
  }

  public void paint(Graphics g) {

    g.drawImage(img,0,0,this);
    g.drawImage(new_img,w+5,0,this);
  }
}
