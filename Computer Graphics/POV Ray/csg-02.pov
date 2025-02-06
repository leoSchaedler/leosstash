#include "colors.inc"  
  
  camera {
    location <0, 1, -10>
    look_at 0
    angle 36
  } 
  
  light_source { <50, 50, -200> White }   
  
  plane { y, -1.5 pigment { checker Green White }  }
  
 
  difference{
    sphere { <0, 0, 0>, 1
      translate -0.5*x  
      pigment { White }
    }
    sphere { <0, 0, 0>, 1
      translate 0.5*x
      pigment { Red }
    }
    rotate <30, 20, 45> 
      finish {
          ambient  0.1
          diffuse  0.5
          specular 0.9  }
  }  
 
/*     intersection {
    sphere { <0, 0, 0>, 1
      translate -0.5*x
    }
    sphere { <0, 0, 0>, 1
      translate 0.5*x
    }
    pigment { Red }
  }

   difference {
    intersection {
      sphere { <0, 0, 0>, 1
        translate -0.5*x
      }
      sphere { <0, 0, 0>, 1
        translate 0.5*x
      }
      pigment { Red }
      rotate 90*y
    }
    cylinder { <0, 0, -1> <0, 0, 1>, .35
      pigment { Blue }
    }
  }
*/