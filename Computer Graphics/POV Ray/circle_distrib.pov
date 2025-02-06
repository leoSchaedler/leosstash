//  distribuição circular de objetos
//  a partir da equação paramétrica da circunferência 


#include "colors.inc"

#declare MyPrettyWhite = rgb <1,1,1>;
#declare MyRadius = 2  ;
#declare XPosition = 0  ; 
#declare ZPosition = 1  ;     
#declare Raio = 50;  
#declare N_Esferas = 1000   ;
#declare WhiteBall = sphere { < XPosition, MyRadius, ZPosition > MyRadius pigment {MyPrettyWhite} } 
camera { location <0, 150, -1000> direction <1, 10, 10> up <0, 1, 0> right <1.25, 0, 0> look_at <0, 0, 90>} 

//Key Light 
light_source {<50, 100, -80> color MyPrettyWhite}

//Back Light 
light_source {<100, 50, 80> color rgb <0,0,1>} 

object { plane {y, -5} pigment {MyPrettyWhite}}   


#declare alfa = 0;


#while (alfa < 2*pi) 
	#declare XPosition = Raio*sin(alfa)  ;
	#declare ZPosition = Raio*cos(alfa)  ;
	#declare WhiteBall = sphere { < XPosition, MyRadius, ZPosition > MyRadius pigment {Blue}}   ;
	#declare alfa = alfa + 6.2830/N_Esferas   ;          
	object {WhiteBall}
#end
           
		             