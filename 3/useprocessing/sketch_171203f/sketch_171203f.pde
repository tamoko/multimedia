PImage im, gray; 
 
void setup(){ 
  im = loadImage("bwdar.jpg");    // 入力画像の読み込み 
  surface.setResizable(true);
  surface.setSize(im.width*2, im.height);   // 画面サイズ
} 
 
void draw(){
  gray = createImage(im.width, im.height, RGB);  // 出力画像用の画像配列(gray)を用意(サイズは入力画像と同じ) 
  im.filter(GRAY);      // グレースケール変換 
  float sum,sumtwo,col;
  // 平均化フィルタ
  for(int y = 1; y < gray.height-1; y++){ 
    for(int x = 1; x < gray.width-1; x++){ 
      sum = (red(im.get(x-1,y-1))*-1) + red(im.get(x+1,y-1))   //横方向の計算
          + (red(im.get(x-1,y  ))*-2) + (red(im.get(x+1,y  ))*2) 
          + (red(im.get(x-1,y+1))*-1) + red(im.get(x+1,y+1)); 
      sumtwo = red(im.get(x-1,y-1)) + (red(im.get(x,y-1))*2) + red(im.get(x+1,y-1))   //縦方向の計算
           + (red(im.get(x-1,y+1))*-1) + (red(im.get(x,y+1))*-2) + (red(im.get(x+1,y-1))*-1);
      col = sqrt(sq(sum) + sq(sumtwo));    //横方向の2乗と縦方向の2乗を足して1/2乗
      gray.set(x, y, color(col));                // 9つの画素値の平均値を設定 
    } 
  }
  image(im, 0, 0);      // 入力画像を画面左に貼る 
  image(gray, im.width, 0);    // 出力画像を画面右に貼る   
}