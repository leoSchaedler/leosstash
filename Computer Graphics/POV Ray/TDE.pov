


// Inclusões

#include "colors.inc"
#include "textures.inc"


// Câmera

camera {
    location <0, 25, 80*clock>
    look_at <0, 5, 80*clock>
    
}


// Fonte de luz e Background 
 
light_source{< 0,100,100> color White}
 
background {color White}

                           

// Plano da Mesa

plane { <0,1,0>, 0 
        texture {
    pigment {
        wood  turbulence 0.04
        octaves 3
        scale <0.05, .05, 1>
        color_map { 
            [0.1 rgb <0.88, 0.60, 0.4>]
            [0.9 rgb <0.60, 0.40, 0.3>]
        }
    }
    finish { 
        specular 0.25
        roughness 0.05
        ambient 0.45 
        diffuse 0.33
        reflection 0.15
    }
}
texture {
    pigment {
        wood  turbulence <0.1, 0.5, 1> 
        octaves 5
        lambda 3.25
        scale <0.15, .5, 1>
        color_map { 
            [0.0 rgbt <0.7, 0.6, 0.4, 0.100>]
            [0.1 rgbt <0.8, 0.6, 0.3, 0.500>]
            [0.1 rgbt <0.8, 0.6, 0.3, 0.650>]
            [0.9 rgbt <0.6, 0.4, 0.2, 0.975>]
            [1.0 rgbt <0.6, 0.4, 0.2, 1.000>]
        }
    rotate <5, 10, 5>
    translate -x*2
    }
    finish { 
        specular 0.25 
        roughness 0.0005
        ambient .1 
        diffuse 0.33
    } 
  }  
}



// Bolinha Metálica

sphere {
    <0, 2, 80*clock>, 2
    texture {
    pigment { rgb <0.2, 0.2, 0.2> }
    finish {
        ambient 0.1
        diffuse 0.7
        brilliance 6.0
        reflection 0.6
        phong 0.8
        phong_size 120
    }
  }
  translate <0, 0, clock>
}


// Fileiras de "Doces"

#declare contador = 0;
#while (contador <200)
    sphere { <-8,3,contador>, 1.9  texture { 
    NBglass
    pigment { rgbf <0.9, 0.1, 0.2, 0.8> }
}
            scale<1,1,1>  rotate<0,0,0>  translate<0,1,0>   }
       
    cylinder{<-8,0,contador>,<-8,4,contador> 0.6 texture { 
    pigment {
    gradient x+y
    color_map {
        [0.25 rgb <1,0,0>]
        [0.25 rgb <1,1,1>]
        [0.75 rgb <1,1,1>]
        [0.75 rgb <1,0,0>]
      }
    }  
  }
}
    
    
    sphere { <8,3,contador>, 1.9  texture { 
    NBglass
    pigment { rgbf <0.9, 0.1, 0.2, 0.8> }
}
            scale<1,1,1>  rotate<0,0,0>  translate<0,1,0>   }
       
    cylinder{<8,0,contador>,<8,4,contador> 0.6 texture { 
    pigment {
    gradient x+y
    color_map {
        [0.25 rgb <1,0,0>]
        [0.25 rgb <1,1,1>]
        [0.75 rgb <1,1,1>]
        [0.75 rgb <1,0,0>]
      }
    }  
  }
}
    
    
    #declare contador = contador + 8;
#end
       
 
//Fin

     
    
    
   
    