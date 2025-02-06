#declare R = 2; 
#declare a = 72*pi/180; // cone com 5 triangulos  360/5 = 72º ou PI  (convertendo para radianos)
#declare h = 2;  // altura do cone
#declare n = 6;  // cone com 5 triangulos 

#declare VXarray = array[n];
#declare VYarray = array[n];
#declare VZarray = array[n];

#declare VXarray[0]=0;
#declare VYarray[0]=0;
#declare VZarray[0]=h;  

#for (i, 1, n-1, 1)
    #declare VXarray[i]=R*sin((i-1)*a);
    #declare VYarray[i]=R*cos((i-1)*a); 
    #declare VZarray[i]=0;
#end

camera { location <0, 0, -10> look_at  <0, 0,  0> } 

light_source { <1, 1, -10> color <1,1,1>*0.5 } 
light_source { <0, 0, -10> color <1,1,1>*0.5 }

mesh2 {
   vertex_vectors {
      15, 
      <VXarray[0],VYarray[0],VZarray[0]>, <VXarray[1],VYarray[1],VZarray[1]>, <VXarray[2],VYarray[2],VZarray[2]>,
      <VXarray[0],VYarray[0],VZarray[0]>, <VXarray[2],VYarray[2],VZarray[2]>, <VXarray[3],VYarray[3],VZarray[3]>, 
      <VXarray[0],VYarray[0],VZarray[0]>, <VXarray[3],VYarray[3],VZarray[3]>, <VXarray[4],VYarray[4],VZarray[4]>,
      <VXarray[0],VYarray[0],VZarray[0]>, <VXarray[4],VYarray[4],VZarray[4]>, <VXarray[5],VYarray[5],VZarray[5]>,
      <VXarray[0],VYarray[0],VZarray[0]>, <VXarray[5],VYarray[5],VZarray[5]>, <VXarray[1],VYarray[1],VZarray[1]>,
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

