// Tutorial PovRay Pavão


//Partes inclusas

#include "colors.inc" 
#include "textures.inc"



// Camera (Window) posicionada em x=0, y=2, z=-5 voltada para 
// direção apontada pelo vetor x=0, y=1, z=2.

camera {
    location <4, 7, -5>
    look_at <0, 1, 2>
    
}



// Background Color 

background {color Black}      
                         
                         
// Fonte de Luz Branca posicionada em x=20, y=4 e z=-13

light_source { <20, 4, -13> color White} 


// Plano com textura em xadrez como "chao" da cena

plane {<0, 1, 0>, -1
    pigment {
       checker color Red, color Black
        }
      }

// Esfera em x=0, y=1, z=2 com raio=2 

sphere {
    <0, 2, 6>, 2
    texture{
        pigment{
            White_Marble
            scale 1
           } 
           finish { Shiny }
         }
       }
 
sphere {
    <15, 1, 30>, 2
    texture {
        pigment {Red}
           finish { Shiny }
    }
  }      

 
difference {
    intersection {
        sphere {<0,0,0>, 1
            translate -0.5*x
       }
        sphere {<0,0,0>, 1
            translate 0.5*x 
        }
        pigment {Blue}
        rotate-90*y
        finish {Shiny}
        }   
    cylinder {<0,0,-1> <0,0,1>, .35
        pigment {Yellow}
    }
  }
         
          
// Exemplo da implementaçao do calculo de radiancia por POV-Ray 
// Efeito de nevoa iniciando-se a 20 unidades da camera com cor cinza 70% 
// e transparencia 50% (definida em RGBA - POV-Ray chama de RGBT 

fog {
    distance 20
    color rgbt <0.7 0.7 0.7 0.5>
  }         
    
    
// Poligon Mesh (Malha de Poligonos) 
// Simplesmente o texto POV 
// Observe que o "furo" no "P" eh automatico 
// Para entender desenhe os pontos definindo o poligo que descreve P 
polygon { 
    30, <-0.8, 0.0>, <-0.8, 1.0>, // Letter "P" 
    <-0.3, 1.0>, <-0.3, 0.5>, // outer shape 
    <-0.7, 0.5>, <-0.7, 0.0>, <-0.8, 0.0>, <-0.7, 0.6>, <-0.7, 0.9>, // hole 
    <-0.4, 0.9>, <-0.4, 0.6>, <-0.7, 0.6> <-0.25, 0.0>, <-0.25, 1.0>, // Letter "O" 
    < 0.25, 1.0>, < 0.25, 0.0>, // outer shape 
    <-0.25, 0.0>, <-0.15, 0.1>, <-0.15, 0.9>, // hole 
    < 0.15, 0.9>, < 0.15, 0.1>, <-0.15, 0.1>, <0.45, 0.0>, <0.30, 1.0>, // Letter "V"
    <0.40, 1.0>, <0.55, 0.1>, 
    <0.70, 1.0>, <0.80, 1.0>, 
    <0.65, 0.0>, <0.45, 0.0> 
    pigment { color rgb <1, 0, 0> } 
    
    translate x*2 
  }    
    
                                      