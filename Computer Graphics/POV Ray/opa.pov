#include "colors.inc"
#include "textures.inc"
#include "glass.inc"
#include "metals.inc"
#include "golds.inc"
#include "stones.inc"
#include "woods.inc"
#include "shapes.inc"
#include "shapes2.inc"
#include "functions.inc"
#include "math.inc"
#include "transforms.inc"



#declare Car_Body_Texture = 
         texture{ pigment{ color rgb<0,0,1>*1.2}
                //normal { radial sine_wave frequency 30 scale 0.25 }
                  finish { phong 1}
                 }
#declare Car_Top_Texture = 
         texture { pigment{ color rgb< 1, 0.95, 0.95>*1.1  } // very light red  
                // normal { bumps 0.5 scale 0.05 }
                   finish { diffuse 0.9 phong 1 reflection 0.00}
                 } // end of texture 
#declare Car_Seat_Texture = 
          texture { pigment{ color rgb< 1, 0.90, 0.85>*1.2  }// very light brown  
                   normal { bumps 0.15 scale 0.05 }
                   finish { phong 1 reflection 0.00}
                 } // end of texture 
#declare Car_Inside_Texture=
          texture { pigment{ color rgb< 1, 0.87, 0.75>  }// very light brown  
                // normal { bumps 0.5 scale 0.05 }
                   finish { phong 1 reflection 0.00}
                 } // end of texture 

#declare Wheel_Rubber_Texture = 
         texture { pigment{ color rgb< 1, 1, 1>*0.15 } //  color Gray25
                   normal { bumps 0.5 scale 0.008 }
                   finish { phong 0.2 reflection 0.00}
                 } // end of texture 
#declare Wheel_Metal_Texture = 
         texture { Chrome_Metal
                   // pigment{ color rgb<1,0,0>} 
                    normal { bumps 0.5 scale 0.005 } 
                    finish { phong 1}
                 } // end of texture 
#declare Bumper_Metal_Texture = 
         texture { Polished_Chrome
                   // pigment{ color rgb<1,0,0>} 
                   finish {   phong 1}
                 } // end of texture 
#declare Chassis_Inside_Texture = 
         texture { pigment{ color rgb< 1, 1, 1>*0.45 } //  color Gray55
                // normal { bumps 0.5 scale 0.05 }
                   finish { phong 1 reflection 0.00}
                 } // end of texture                    
                 
                 
                 
                 
                 
                 
                 #include "Car_01.inc" 
//-------------------------------------------------------------------------------------// 
object{ Car_01( 0, //Front_Wheel_Angle, // 
                0, // Coupe_ON:  0= off(hard top); 1= coupé

                Car_Body_Texture,      //
                Car_Top_Texture,       //
                Car_Seat_Texture,      //
                Car_Inside_Texture,    //
                Wheel_Rubber_Texture,  //
                Wheel_Metal_Texture,   //
                Bumper_Metal_Texture,  //
                Chassis_Inside_Texture //
              ) //-------------------------------------------------- 

        scale<1.5,1.5,1.5>
        rotate<0,-90,0>
        translate<2.3,0,5>} 
//-------------------------------------------------------------------------------------// 
//-------------------------------------------------------------------------------------// 

light_source{ <15,20,30> color Orange
}                    


plane {y,0
 pigment {Green}
}


 camera {
    location <0, 8, -15>
    look_at <0, 0, 30>
  }             
  
  
  //background { color rgb <0.2, 0.2, 1> }         
  sky_sphere {
    pigment {
      gradient y
      color_map {
        [ 0.5  color Orange ]
        [ 1.0  color Blue ]
      }
      scale 2
      translate -1
    }
  }
  
sphere
{ 
0,100
pigment { rgbt 1 } hollow
interior
        { media                
              { emission 0.1
              density
              { spherical density_map
              { [0 rgb 0]
                [60 Orange]
                [80 Red]
                [100 Yellow]
              }
              scale 100
              }
              }
        }
} 
  
  
  
  
box { <5.0, -5.0, -5.0> <-5.0, 0.1, 500.0> pigment { color Gray} }

#declare contador = 0;
#while (contador<2000)
    box {  <-0.2, 0.0, -5.0+contador> <0.2, 0.11, -5.0+0.5+contador> pigment {color Yellow}  }
    #declare contador = contador + 1;
#end
  

                
         
        
        
#declare contador = 0;
#while (contador <200)
    sphere { <-8,3,contador>, 1.9  pigment{Green} texture { pigment{ color rgb<0.35,0.65,0.0>*0.9}                                      
                    normal { bumps 1.75 scale 0.05} 
                    finish { phong 0.5 reflection{ 0.00 metallic 0.00} } 
                    }
            scale<1,1,1>  rotate<0,0,0>  translate<0,1,0>   }
       
    cylinder{<-8,0,contador>,<-8,4,contador> 0.8 pigment {Brown} texture {T_Wood19}    }
    
    
    sphere { <8,3,contador>, 1.9  pigment{Green} texture { pigment{ color rgb<0.35,0.65,0.0>*0.9}                                      
                    normal { bumps 1.75 scale 0.05} 
                    finish { phong 0.5 reflection{ 0.00 metallic 0.00} } 
                    }
            scale<1,1,1>  rotate<0,0,0>  translate<0,1,0>   }
       
    cylinder{<8,0,contador>,<8,4,contador> 0.8 pigment {Brown} texture {T_Wood19}    }
    
    
    #declare contador = contador + 8;
#end
      
                             
                             
              
              