//alpha.java
//
// appletviewer alpha.html で動く？

import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.Math;

public class alpha extends Applet{
  Image img1; //1つ目の元のイメージ用オブジェクトを用意
  Image img2; //2つ目の元のイメージ用オブジェクトを用意
  Image new_img; //変換後のイメージ用オブジェクトを用意
  
  int w = 0;
  int h = 0;
  
  int pix[];
  int pix1[]; //1つ目の元になるイメージを格納するための配列
  int pix2[]; //2つ目の元になるイメージを格納するための配列
  int new_pix[]; //変換後のイメージを格納するための配列
  int red1[],green1[],blue1[],red2[],green2[],blue2[];
  int new_red[],new_green[],new_blue[];
  
  public void init() {
    
    MediaTracker mt = new MediaTracker(this);

    img1 = getImage(getCodeBase(),"cosmo.jpg");    
    img2 = getImage(getCodeBase(),"dog.jpg");
    
    mt.addImage(img1, 0);
    mt.addImage(img2, 1);
    
    try {
      mt.waitForAll();
    } catch (InterruptedException e) {
    }
    
    
    w = img1.getWidth(this); //同じ画像サイズのものを使うことを前提にする
    h = img1.getHeight(this);
    
    setPix();
  }
  
  
  void setPix(){
  
    pix = new int[w*h];
    pix1 = new int[w*h];
    pix2 = new int[w*h];
    new_pix = new int[w*h];
    
    red1 = new int[w*h];
    green1 = new int[w*h];
    blue1 = new int[w*h];
   		
    red2 = new int[w*h];
    green2 = new int[w*h];
    blue2 = new int[w*h];
   
    new_red = new int[w*h];
    new_green = new int[w*h];
    new_blue = new int[w*h];
    
    PixelGrabber pg1 = new PixelGrabber(img1,0,0,w,h,pix1,0,w);
    PixelGrabber pg2 = new PixelGrabber(img2,0,0,w,h,pix2,0,w);
    try{
      pg1.grabPixels();
      pg2.grabPixels();
    } catch (InterruptedException e) {
    }
    
    
    
    //処理
    for(int i=0;i < w*h; i++){
    	red1[i] = (pix1[i] >> 16) & 0xff;
     	green1[i] = (pix1[i] >> 8) & 0xff;
     	blue1[i] = (pix1[i] >> 0) & 0xff;
    			
  	red2[i] = (pix2[i] >> 16) & 0xff;
     	green2[i] = (pix2[i] >> 8) & 0xff;
     	blue2[i] = (pix2[i] >> 0) & 0xff;
     		
     	new_red[i] = 255-((red1[i] + red2[i]) / 2);
     	new_green[i] = 255-((green2[i] + green2[i]) / 2);
     	new_blue[i] = 255-((blue1[i] + blue2[i]) / 2);
     	//System.out.println(new_red[i]);
        
        //new_pix[i-1] = (pix1[i-1] + pix2[i-1])/2;
    }
    
    for(int y=0; y<h; y++){
		for(int x=0; x<w; x++){
      			new_pix[x+y*w] = 0x00ff0000 & pix[x+y*w] | new_red[x+y*w]<<16 | new_green[x+y*w]<<8 | new_blue[x+y*w];
      			new_pix[x+y*w] = new_pix[x+y*w] * -1;
      			//System.out.println(new_pix[x+y*w]);
     		}
   	}
    
    
    MemoryImageSource ming = new MemoryImageSource(w,h,new_pix,0,w);
    new_img = createImage(ming);
  }
  
  public void paint(Graphics g) {
    
    g.drawImage(new_img,0,0,this);
  } 
}