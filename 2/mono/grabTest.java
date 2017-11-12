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

    img = getImage(getCodeBase(),"FullSizeRender.jpg");

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

    for(int i=1;i <= w*h;i++) {
      new_pix[i-1] = (int)(255*(Math.pow((double)(pix[i-1]/255),(1/3))));
    }

    MemoryImageSource mimg = new MemoryImageSource(w,h,new_pix,0,w);
    new_img = createImage(mimg);
  }

  public void paint(Graphics g) {

    g.drawImage(new_img,0,0,this);
  }
}
