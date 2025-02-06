







// construção de um cilindro oco a partir de esferas
// a partir da equação para métrica da circunferência
#declare MyPrettyWhite = rgb <1,1,1>;
#declare MyRadius = 5  ;
#declare XPosition = 0  ; 
#declare YPosition = MyRadius  ;
#declare ZPosition = 1  ;     
#declare Raio = 50;  
#declare N_Esferas = 65   ;
#declare WhiteBall = sphere { < XPosition, YPosition, ZPosition > MyRadius 
pigment {MyPrettyWhite} } 
camera { location <0, 1500, -2000> direction <0, 0, 10> up <0, 1, 0> right <1.25, 0, 0> look_at <0, 0, 90>} 

//Key Light 
light_source {<50, 200, -80> color MyPrettyWhite}

//Back Light 
light_source {<100, -10, 80> color rgb <0,0,1>} 

object { plane {y,-50} pigment {MyPrettyWhite}finish { reflection {1.0} ambient 0.2 diffuse 0.1 }}   

// base
#while (Raio > 0)
   #declare alfa = 0;
   #while (alfa < 12) 	  
	#declare XPosition = Raio*sin(alfa)  ;
	#declare ZPosition = Raio*cos(alfa)  ;
	#declare WhiteBall = sphere { < XPosition, YPosition, ZPosition > MyRadius pigment {MyPrettyWhite}}   ;
	#declare alfa = alfa + 2*pi/N_Esferas   ;          
	object {WhiteBall}
   #end
#declare N_Esferas = N_Esferas - 1;
#declare Raio = Raio - 4;    
#end
// lateral
#declare N_Esferas = 65   ;
#declare Raio = 50;  
#declare YCount =0;
#declare alfa = 0;
#while (YCount < 50)
  #while (alfa < 2*pi) 
	#declare XPosition = Raio*sin(alfa)  ;
	#declare ZPosition = Raio*cos(alfa)  ;
	#declare WhiteBall = sphere { < XPosition, YPosition, ZPosition > MyRadius pigment {MyPrettyWhite}}   ;
	#declare alfa = alfa + 2*pi/N_Esferas   ;          
	object {WhiteBall}
  #end
  #declare YCount = YCount + 1;
  #declare alfa = 0;
  #declare YPosition = YPosition + 3;
#end                  
// topo                  
#while (Raio > 0)
   #declare alfa = 0;
   #while (alfa < 12) 	  
	#declare XPosition = Raio*sin(alfa)  ;
	#declare ZPosition = Raio*cos(alfa)  ;
	#declare WhiteBall = sphere { < XPosition, YPosition, ZPosition > MyRadius pigment {MyPrettyWhite}}   ;
	#declare alfa = alfa + 2*pi/N_Esferas   ;          
	object {WhiteBall}
   #end
#declare N_Esferas = N_Esferas - 1;
#declare Raio = Raio - 4;    
#end		             