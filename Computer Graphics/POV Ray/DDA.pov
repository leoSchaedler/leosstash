// implementação do algoritmo DDA para o desenho de linhas ou
// distribuição de objetos ao longo de uma linha
// x1, y1 ponto inicial da linha e x2,y2 final
////////////////////////////////////////////////////////
#declare MyPrettyWhite = rgbf <1,1,1,0.5>;
#declare MyRadius = 0.5  ;
#declare XPosition = 0  ;
#declare ZPosition = 1  ;     
#declare WhiteBall = sphere { < XPosition, MyRadius, ZPosition > MyRadius pigment {MyPrettyWhite} }  
//////////////////////////////////////////////////////// 

camera { location <0,10, -10> look_at <10, 0, 10>} 
//Key Light 
light_source {<50, 100, -80> color MyPrettyWhite}
//Back Light 
light_source {<100, 50, 80> color rgb <0,0,1>} 
object { plane {y, 0} pigment {MyPrettyWhite}}

////////////////////////////////////////////////////////                 
#declare x1 = 0;
#declare z1 = 0;	             
#declare x2 = 50;
#declare z2 = 50; 
//////////////////////////////////////////////////////// 
#declare Dx = x2 - x1;
#declare Dz = z2 - z1;
//////////////////////////////////////////////////////// 
#if (Dx>=Dz) 
        #declare length = Dx; 
#end
//////////////////////////////////////////////////////// 
#if (Dz>Dx)
        #declare length = Dz;
#end
////////////////////////////////////////////////////////  
#declare x_increment = (x2-x1) / length;
#declare z_increment = (z2-z1) / length;
//////////////////////////////////////////////////////// 

#declare XPosition = x1  ;
#declare ZPosition = z1  ; 

#declare BallCount = 1;         

#while (BallCount < length)
        #declare XPosition = XPosition + x_increment;
        #declare ZPosition = ZPosition + z_increment;
        #declare WhiteBall = sphere { < XPosition, MyRadius, ZPosition > MyRadius pigment {MyPrettyWhite}};
        #declare BallCount = BallCount + 1   ; 
        object {WhiteBall}
#end 
//////////////////////////////////////////////////////// 

