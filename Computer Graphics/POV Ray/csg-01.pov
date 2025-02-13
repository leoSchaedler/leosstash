  #include "colors.inc"
  
  camera {
    location <0, 1, -10>
    look_at 0
    angle 36
  } 
  
  light_source { <500, 500, -1000> White }   
  
  plane { y, -1.5 pigment { checker Green White }  }
  
 /*
  union{
    sphere { <0, 0, 0>, 1
      translate -0.5*x
    }
    sphere { <0, 0, 0>, 1
      translate 0.5*x
    }
    pigment { Red }
    scale <1, .25, 1>
    rotate <30, 0, 45>
  }
 
 intersection {
    sphere { <0, 0, 0>, 1
      translate -0.5*x
    }
    sphere { <0, 0, 0>, 1
      translate 0.5*x
    }
    pigment { Red }
  }
 */
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
