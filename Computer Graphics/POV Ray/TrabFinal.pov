#declare R = 2; 
 // cone com 5 triangulos  360/5 = 72º ou PI  (convertendo para radianos)
#declare h = 10;  // altura do cone
#declare n = 8;  // cone com 5 triangulos 
#declare degreesToRadian = (360/(n-1))*pi/180;
#declare VXarray = array[n];
#declare VYarray = array[n];
#declare VZarray = array[n];

#declare VXarray[0]=0;
#declare VYarray[0]=0;
#declare VZarray[0]=h;  

#for (i, 1, n-1, 1)
    #local angleInRadian = (i-1)*degreesToRadian;
    #declare VXarray[i] = R * sin(angleInRadian);
    #declare VYarray[i] = R * cos(angleInRadian); 
    #declare VZarray[i] = 0;
#end

camera { location <0, 10, -10> look_at  <0, 0,  0> } 

light_source { <1, 1, -10> color <1,1,1>*0.5 } 
light_source { <0, 0, -10> color <1,1,1>*0.5 }


mesh2 {
   vertex_vectors {
      n, 
    #for (i, 0, n-1, 1)
        <VXarray[i], VYarray[i], VZarray[i]>,
    #end
   }
   
   face_indices {
      n, 
     
     #for (i, 0, n-2, 1)
        <i, i + 1, 0>,
     #end
     <n-1, 1, 0>
     
   }
   pigment {rgb 1} 
   rotate<0,clock,0>
}