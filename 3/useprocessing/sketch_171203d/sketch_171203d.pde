PImage im, gray; 
 
void setup(){ 
  im = loadImage("AveFullSizeRender.jpg");    // 入力画像の読み込み 
  surface.setResizable(true);
  surface.setSize(im.width*2, im.height);   // 画面サイズ
} 
 
void draw(){
  gray = createImage(im.width, im.height, RGB);  // 出力画像用の画像配列(gray)を用意(サイズは入力画像と同じ) 
  im.filter(GRAY);      // グレースケール変換 
  float sum;
  int k = 9;
  // 平均化フィルタ
  for(int y = 1; y < gray.height-1; y++){ 
    for(int x = 1; x < gray.width-1; x++){ 
      sum = red(im.get(x-1,y-1)) + red(im.get(x  ,y-1)) + red(im.get(x+1,y-1)) 
          + red(im.get(x-1,y  )) + red(im.get(x  ,y  )) + red(im.get(x+1,y  )) 
          + red(im.get(x-1,y+1)) + red(im.get(x  ,y+1)) + red(im.get(x+1,y+1)); 
      gray.set(x, y, color(sum/9));                // 9つの画素値の平均値を設定 
    } 
  }
  for(int i = 1; i < gray.height-1; i++){ 
    for(int j = 1; j < gray.width-1; j++){ 
      sum = red(im.get(j,i)) + ((red(im.get(j,i)) - red(gray.get(j,i)))*k);  //元画像から平均化した画像を引き、元画像に足す
      gray.set(j, i, color(sum));
    } 
  }
  image(im, 0, 0);      // 入力画像を画面左に貼る 
  image(gray, im.width, 0);    // 出力画像を画面右に貼る   
}