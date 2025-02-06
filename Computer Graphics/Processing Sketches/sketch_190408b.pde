  int intervalo = 50;
  color amarelo = #E2E531, vermelho = #B72E2E, verde = #427C1A, azul = #223F86,
  vidro = #A2FFF6, laranja = #E38E39;
  int xB1 = 150, xB2 = 150, xB3 = 150, xB4 = 150;
  float yB;
  PImage imagem;
  
void setup() {
  size(500, 500);
  imagem =loadImage("imagem.jpg");
  
  //noLoop();

    
}
void carro1(int xB, float yB){
 noStroke(); 
 fill(0); 
 ellipse(xB+15,yB+15,30,20);
 fill(0);
 ellipse(xB+65,yB+15,30,20);
 fill(vidro);
 ellipse(xB+40,yB-22,40,30);
 fill(vermelho);
 rect(xB,yB-20,80,30);
 fill(laranja);
 ellipse(xB+4,yB-16,12,12);
}

void carro2(int xB, float yB){
 noStroke(); 
 fill(0); 
 ellipse(xB+18,yB+10,30,20);
 fill(0);
 ellipse(xB+52,yB+10,30,20);
 fill(vidro);
 ellipse(xB+50,yB-15,40,30);
 fill(amarelo);
 rect(xB,yB-20,70,30);
}

void truck(int xB, float yB){
 noStroke(); 
 
 
 fill(0); 
 ellipse(xB-35,yB+25,30,30);
 ellipse(xB,yB+25,30,30);
 ellipse(xB+80,yB+25,30,30);
 ellipse(xB+115,yB+25,30,30);
 ellipse(xB+165,yB+25,30,30);
 ellipse(xB+200,yB+25,30,30);
 
 
 fill(azul);
 rect(xB-65,yB-40,210,60);
 rect(xB+150,yB-35,40,20);
 rect(xB+150,yB-20,70,40);
}

void tanque(int xB, float yB){
 noStroke(); 
 fill(0); 
 ellipse(xB+85,yB+30,30,20);
 fill(0); 
 ellipse(xB+60,yB+30,30,20);
fill(0); 
 ellipse(xB+35,yB+30,30,20);
 fill(0); 
 ellipse(xB+10,yB+30,30,20);
 fill(verde);
 ellipse(xB+78,yB-30,50,50);
 fill(verde);
 rect(xB-5,yB-20,107,50);
 fill(verde);
 rect(xB-20,yB-40,120,10);
}

void draw() {
  background(0,0,255);
  image(imagem, 0, 0);
  
  carro1(xB1, 320);
  xB1= xB1 + 2;
  
  carro2(xB2, 375); 
  xB2 = xB2 -3;
  
  truck(xB3, 230);
  xB3= xB3 + 1;
  
  tanque(xB4, 455);
  xB4 = xB4 - 1;
  


 if(xB1> width+25) {xB1 = -80; yB = random(0, height);}
 if(xB3> width+100) {xB3 = -250; yB = random(0, height);}
 if(xB2< -50) {xB2 = width + 100; yB = random(0, height);}
 if(xB4< -110) {xB4 = width +100; yB = random(0, height);}
 

  
}
