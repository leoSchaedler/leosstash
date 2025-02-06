#include "colors.inc"

#declare MyRadius = .1;
#declare xPosition = 5;
#declare yPosition = 5;
#declare zPosition = 5;



#declare WhiteBall = sphere { < xPosition, yPosition, zPosition > MyRadius pigment {white} }

camera { location <0, 2, =10> look_at <0,0,0> }

light_source {<50, 100, -80> color White}

#declare BallCount = 1;
#while (BallCount < 10)
    object {whiteBall}
    #declare xPosition = xPosition + .2;
    #declare whiteBall = sphere { < xPosiotion, yPosiotion, zPosition > MyRadius pigment {white}};
    #declare BallCount = BallCOunt +1;
#end