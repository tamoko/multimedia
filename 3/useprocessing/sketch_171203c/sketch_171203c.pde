PImage im, gray; 
 
void setup(){ 
  im = loadImage("FullSizeRender.jpg");    // 入力画像の読み込み 
  surface.setResizable(true);
  surface.setSize(im.width*2, im.height);   // 画面サイズ
} 
 
void draw(){
  gray = createImage(im.width, im.height, RGB);  // 出力画像用の画像配列(gray)を用意(サイズは入力画像と同じ) 
  im.filter(GRAY);      // グレースケール変換 
  float sum;
  // 平均化フィルタ
  for(int y = 1; y < gray.height-1; y++){ 
    for(int x = 1; x < gray.width-1; x++){ 
      //平均化フィルタのコードに重み付けの計算を追加
      sum = red(im.get(x-2,y-2))     + (red(im.get(x-1,y-2))*4)  + (red(im.get(x  ,y-2))*6)  + (red(im.get(x+1,y-2))*4)  + red(im.get(x+2,y-2))     +
            (red(im.get(x-2,y-1))*4) + (red(im.get(x-1,y-1))*16) + (red(im.get(x  ,y-1))*24) + (red(im.get(x+1,y-1))*16) + (red(im.get(x+2,y-1))*4) +
            (red(im.get(x-2,y  ))*6) + (red(im.get(x-1,y  ))*24) + (red(im.get(x  ,y  ))*36) + (red(im.get(x+1,y  ))*24) + (red(im.get(x+2,y  ))*6) +
            (red(im.get(x-2,y+1))*4) + (red(im.get(x-1,y+1))*16) + (red(im.get(x  ,y+1))*24) + (red(im.get(x+1,y+1))*16) + (red(im.get(x+2,y+1))*4) +
            red(im.get(x-2,y+2))     + (red(im.get(x-1,y+2))*4)  + (red(im.get(x  ,y+2))*6)  + (red(im.get(x+1,y+2))*4)  + red(im.get(x+2,y+2));
      gray.set(x, y, color(sum/256));                // 256個の画素値の平均値を設定 
    } 
  }
  image(im, 0, 0);      // 入力画像を画面左に貼る 
  image(gray, im.width, 0);    // 出力画像を画面右に貼る   
}