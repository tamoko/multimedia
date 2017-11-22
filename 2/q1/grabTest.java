import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.Math;

public class grabTest extends Applet{
  Image img,new_img;
  double ganma = 4.30;
  
  int w,h,i,x,y;
  int pix[],new_pix[];
  int red[],green[],blue[];
  int new_red[],new_green[],new_blue[];
  
  public void init(){
  img = getImage(getCodeBase(),"FullSizeRender.jpg"); 
  MediaTracker mt = new MediaTracker(this);
  mt.addImage(img, 0);
  
  try{
  	mt.waitForID(0);
  } catch (InterruptedException e){}
   w = img.getWidth(this);
   h = img.getHeight(this);
   pix = new int[w*h];
   new_pix = new int[w*h];
   red = new int[w*h];
   green = new int[w*h];
   blue = new int[w*h];

   new_red = new int[w*h];
   new_green = new int[w*h];
   new_blue = new int[w*h];
   setPix();
 }
   void setPix(){
   try {
    PixelGrabber pg = new PixelGrabber(img,0,0,w,h,pix,0,w);
    pg.grabPixels();
   } catch (InterruptedException e) {}
    for(i=0; i<h*w; i++){
     red[i] = (pix[i] >> 16) & 0xff;
     green[i] = (pix[i] >> 8) & 0xff;
     blue[i] = (pix[i] >> 0) & 0xff;
    }
    for(y=0; y<h; y++){
     for(x=0; x<w; x++){
      new_red[x+y*w] = GanmaTrance(red[y*w+x],ganma);
      new_green[x+y*w] = GanmaTrance(green[y*w+x],ganma);
      new_blue[x+y*w] = GanmaTrance(blue[y*w+x],ganma);
      new_pix[x+y*w] = 0xff000000 & pix[x+y*w] | new_red[x+y*w]<<16 |
      new_green[x+y*w]<<8 | new_blue[x+y*w];
      System.out.println(new_pix[x+y*w]);
     }
    }
    MemoryImageSource mimg = new MemoryImageSource(w,h,new_pix,0,w);
    new_img = createImage(mimg);
  }
  public int GanmaTrance(int color,double ganma){
   double newcolor = (double)color;
   newcolor = 255*(Math.pow((newcolor/255),(1/ganma)));
   color = (int)newcolor;
   return color;
 }
   public void paint(Graphics g){
    g.drawImage(img, 0, 0, this);
    g.drawImage(new_img, w+5, 0, this);
    }
}