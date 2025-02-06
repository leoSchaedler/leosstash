
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
    
sphere{<5*Radius, 0, 0> 1 
    texture
    {
        pigment {color Red}
        }
    } 
 
 
cylinder{<5*Radius, 0, 0> <5*Radius, 0, 5*Radius> Radius/2
    texture                                                                                                        
    {
        pigment{ color Red } 
        }
    }
    
sphere{<5*Radius, 0, 0> 1 
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
    
sphere{<0, 0, 5*Radius> 1 
    texture
    {
        pigment {color Blue}
        }
    } 
    
    
cylinder{<5*Radius, 0, 5*Radius> <0, 0, 5*Radius> Radius/2
    texture                                                                                                        
    {
        pigment{ color Blue } 
        }
    }
    
sphere{<5*Radius, 0, 5*Radius> 1 
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
    
sphere{<0, 5*Radius, 0> 1 
    texture
    {
        pigment {color Green}
        }
    }