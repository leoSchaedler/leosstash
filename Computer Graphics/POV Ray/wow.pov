
#include "colors.inc" 
declare Radius = 1;  

/////////////////////

camera
{  
    location <20, 20, -20>
    look_at <0, 0, 0>
    
}



light_source { <2, 4, -3> color White}                                                                                     


                                                                                     

//////////////////// RED

sphere {<0, 0, 0>, 1
    texture{
        pigment{ color Yellow }
            }
         }
        


cylinder{<0, 0, 0> <5*Radius, 0, 0> Radius/2
    texture                                                                                                        
    {
        pigment{ color Red } 
        }
    }
    
cone{<5*Radius, 0, 0> 1 <7*Radius, 0, 0> 0
    texture
    {
        pigment {color Red}
        }
    }  
    
    
//////////////////// Blue



cylinder{<0, 0, 0> <0, 0, 5*Radius> Radius/2
    texture                                                                                                        
    {
        pigment{ color Blue } 
        }
    }
    
cone{<0, 0, 5*Radius> 1 <0, 0, 7*Radius> 0
    texture
    {
        pigment {color Blue}
        }
    }
    
    
//////////////////// Green


        

cylinder{<0, 0, 0> <0, 5*Radius, 0> Radius/2
    texture                                                                                                        
    {
        pigment{ color Green } 
        }
    }
    
cone{<0, 5*Radius, 0> 1 <0, 7*Radius, 0> 0
    texture
    {
        pigment {color Green}
        }
    }