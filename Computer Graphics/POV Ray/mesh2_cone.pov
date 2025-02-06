#declare R = 2; 
#declare a = 72*pi/180; // cone com 5 triangulos  360/5 = 72º ou PI  (convertendo para radianos)
#declare h = 2;  // altura do cone

#declare v0x = 0;
#declare v1x = R*sin(0);
#declare v2x = R*sin(0+a);
#declare v3x = R*sin(0+a+a);
#declare v4x = R*sin(0+a+a+a);
#declare v5x = R*sin(0+a+a+a+a);

#declare v0y = 0;
#declare v1y = R*cos(0);
#declare v2y = R*cos(0+a);
#declare v3y = R*cos(0+a+a);
#declare v4y = R*cos(0+a+a+a);
#declare v5y = R*cos(0+a+a+a+a);

#declare v0z = h;
#declare v1z = 0;   // base do cone em z=0 (plano xy)
#declare v2z = 0;
#declare v3z = 0;
#declare v4z = 0;
#declare v5z = 0;

camera { location <0, 0, -10> look_at  <0, 0,  0> } 

light_source { <1, 1, -10> color <1,1,1>*0.5 } 
light_source { <0, 0, -10> color <1,1,1>*0.5 }

mesh2 {
   vertex_vectors {
      15, 
      <v0x,v0y,v0z>, <v1x,v1y,v1z>, <v2x,v2y,v2z>,
      <v0x,v0y,v0z>, <v2x,v2y,v2z>, <v3x,v3y,v3z>, 
      <v0x,v0y,v0z>, <v3x,v3y,v3z>, <v4x,v4y,v4z>,
      <v0x,v0y,v0z>, <v4x,v4y,v4z>, <v5x,v5y,v5z>,
      <v0x,v0y,v0z>, <v5x,v5y,v5z>, <v1x,v1y,v1z>,
   }
   face_indices {
      5, 
      <0,1,2>, 
      <3,4,5>,
      <6,7,8,>,
      <9,10,11>
      <12,13,14>    
   }
   pigment {rgb 1} 
   rotate<0,clock,0>
}

