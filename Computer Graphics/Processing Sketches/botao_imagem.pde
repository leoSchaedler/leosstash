PImage img1, img2, img1a, img2a, jade100, jade300, kitana100, kitana300;
int var = 0;

void setup() {
  size(500,500);
  noStroke();
  img1 = loadImage("img1.jpg");
  img2 = loadImage("img2.jpg");
  img1a = loadImage("img1a.jpg");
  img2a = loadImage("img2a.jpg");
  jade100 = loadImage("jade100.jpg");
  jade300 = loadImage("jade300.jpg");
  kitana100 = loadImage("kitana100.jpg");
  kitana300 = loadImage("kitana300.jpg");
  
}

void mousePressed(){
 if((mouseX<100) && (mouseY<100)) var=1; else
   if((mouseX>400) && (mouseY<100)) var=2; else 
     if((mouseX<100) && (mouseY>400)) var=3; else 
       if ((mouseX>400) && (mouseY>400)) var=4; else var=0;
}

void draw() {
 // botão 1
 image(img1a,0,0);
 // botão 2
 image(img2a, 400,0);
 //botao 3
 image(jade100, 0, 400);
 //botao 4
 image(kitana100, 400, 400);

 // teste qual imagem 
 if (var==1)  image(img1, 100,100); else 
   if (var==2)  image(img2, 100,100); else 
     if (var==3) {image(jade300, 100, 100);  
       print ("(.)(.)"); }  else
     
       if (var==4) image(kitana300, 100, 100); else
       
   rect(100,100,300,300);
;
}
