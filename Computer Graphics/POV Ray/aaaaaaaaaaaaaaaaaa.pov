camera {location <0,2,-5> look_at <0,0,0>}

light_source {<2,4,-3> color <1,1,1>}
#declare NbrPoints = 24;
#declare Radius = 2;
#declare Varray = array[NbrPoints + 1];
#declare a =  72 * pi / 180;
#declare Varray[0] = <0, 0, 3>;       

#for (i, 1, NbrPoints+1, 1) 
  #local j = (i-1)*a;
  Varray[i] = <Radius * sin(j), Radius * cos(j), 0>
#end
mesh2 {
    vertex_vectors{
        NbrPoints + 1,
        Varray      
    }
    face_indices {
    #for(i, 1,n*3, 1)
      i, i+1, 0
    #end
    }
    
}      
camera {
    translate<-10, 2, -10>
    look_at<0, 0, 0>
}