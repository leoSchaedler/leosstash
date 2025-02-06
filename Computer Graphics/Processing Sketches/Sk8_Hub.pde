// Sk8Hub: Um player de vídeo focado em skate. By: Leonardo Ribeiro Schaedler


  // Bibliotecas
  
import controlP5.*;
import processing.video.*;
import processing.sound.*;


  //Variáveis
  
String title = "Home 4 the BEST skate videos...";
int var;
ControlP5 cp5;
Movie haslam, devilMickey, rodney, Max80s;
PImage haslamthumb, devilMickeythumb, Sk8HubLogo, rodneythumb, Max80sthumb, enjoi, soundIcon, soundMute;
SoundFile pushYourWood;



void setup() {
  
   // Background
    size (1280 , 720);
    background(0);
    fill(#F0A502);
    
  // Title
    rect(5,5, 1270, 50, 8);
    textSize(30);
    fill(#FFFFFF);
    text(title,440, 50);
    
  // Logo/Icons
    Sk8HubLogo = loadImage("Sk8HubLogo.png");
    image(Sk8HubLogo, 1050, 600,200, 100);
    
    enjoi = loadImage("enjoi.jpg");
    image(enjoi, 395, 120, 500, 500);
    
    soundIcon = loadImage("soundIcon.png");
    image(soundIcon, 20, 600, 100, 100);
    
    soundMute = loadImage("soundMute.png");
    
  // Videos    
    haslamthumb = loadImage("haslamthumb.png");
    image(haslamthumb, 150, 140,300, 170);
    haslam = new Movie(this, "haslam.mov"); 
    haslam.noLoop();
    
    
    devilMickeythumb = loadImage("devilMickeythumb.png");
    image(devilMickeythumb, 830, 140,300, 170);
    devilMickey = new Movie(this, "devilMickey.mov");
    devilMickey.noLoop();
    
    
    rodneythumb = loadImage("rodneythumb.png");
    image(rodneythumb, 150, 380,300, 170);
    rodney = new Movie(this, "rodney.mp4");
    rodney.noLoop();
    
    
    Max80sthumb = loadImage("Max80sthumb.png");
    image(Max80sthumb, 830, 380,300, 170);
    Max80s = new Movie(this, "Max80s.mp4");
    Max80s.noLoop();
    
  //Sound
  pushYourWood = new SoundFile(this, "pushYourWood.mp3");
  pushYourWood.loop();
  

}



void movieEvent(Movie m) {
  m.read();
  
}

 
void togglePause() {
  var = 99;
  haslam.stop();
  devilMickey.stop();
  rodney.stop();
  Max80s.stop();
  pushYourWood.stop();
  
}



void mousePressed(){
 if((mouseX>1050) && (mouseX<1250) && (mouseY>600) && (mouseY<700)) var=1; else
   if ((mouseX>150) && (mouseX<450) && (mouseY>140) && (mouseY<310)) var=2; else
     if((mouseX>830) && (mouseX<1130) && (mouseY>140) && (mouseY<310)) var=3; else
       if((mouseX>150) && (mouseX<450) && (mouseY>380) && (mouseY<550)) var=4; else 
         if((mouseX>830) && (mouseX<1130) && (mouseY>380) && (mouseY<550)) var=5; else var=0;
           if((mouseX>20) && (mouseX<120) && (mouseY>600) && (mouseY<700)) {image(soundMute, 20, 600, 100, 100); pushYourWood.stop();}
     
}



void draw() {
   if (var==1) {tint(255, 255);togglePause(); setup();}
     if (var==2) {pushYourWood.pause(); devilMickey.stop(); rodney.stop(); Max80s.stop(); haslam.play(); image(haslam,0 , 0, width, height); tint(255, 127); image(Sk8HubLogo, 1050, 600,200, 100);}
       if (var==3) {pushYourWood.pause(); haslam.stop(); rodney.stop(); Max80s.stop(); devilMickey.play(); image(devilMickey,0 , 0, width, height); tint(255, 127);image(Sk8HubLogo, 1050, 600,200, 100);}
         if (var==4) {pushYourWood.pause();devilMickey.stop(); haslam.stop(); Max80s.stop();rodney.play(); image(rodney,0 , 0, width, height); tint(255, 127);image(Sk8HubLogo, 1050, 600,200, 100);}
           if (var==5) {pushYourWood.pause();devilMickey.stop(); rodney.stop(); haslam.stop();Max80s.play(); image(Max80s,0 , 0, width, height); tint(255, 127);image(Sk8HubLogo, 1050, 600,200, 100);}

}

// END




/////// Tentativas de colocar uma função de Pause:



// char keychar = '0';




//void togglePlay() {
 // var=1;
 
//}


//void keyPressed() {
 //if (keyPressed) {
    //while ((key == 'b') || (key=='B') && (var==99)) {
      //togglePause();
      
    //}
 //}
 
 
 //if (keyPressed) {
    //while ((key == 'n') || (key=='N')) {
     //togglePlay();
     
    //}
  //}
//}
  


// Por alguma limitação/bug da biblioteca, ou própria ferramenta algumas funções não foram implementadas. 
// O comando "pause();" do processing.video apresentou problemas de execução, e não fez sua função, por esse e outros motivos decidi que seria melhor deixar sem essa função, do que implementá-la de um jeito mal-acabado e bugado.
