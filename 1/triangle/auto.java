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

    carpet();
  }

  	public static boolean inCarpet(long x, long y) {
    		while (x!=0 && y!=0) {
        		if (x % 3 == 1 && y % 3 == 1)
            		return false;
        		x /= 3;
        		y /= 3;
    		}
    		return true;
	}

  	public void carpet() {
      	pix[0] = 1;
    	for(int i = 0; i < h; i++) {
        	for(int j = 0; j < w; j++) {
        		int p = i * w + j;
            		boolean bool =  inCarpet(i,j);
            		if(bool == true){
            			pix[p] = 1;
           		 }else{
            			pix[p] = 0;
            		}
           	}	
        	
        	System.out.println();
    		}
  	}

  public void paint(Graphics g) {
    MemoryImageSource mimg = new MemoryImageSource(w,h,cm,pix,0,w);
    img = createImage(mimg);

    g.drawImage(img,0,0,this);
  }
}
