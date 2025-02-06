int xPos=250, yPos=250;

void setup() {
  size(500, 500);
  fill(255);
  noStroke();
  textSize(16);
}

void draw() {
  background(128);
  ellipse(xPos,height-yPos,10,10);
  text(xPos+"-"+yPos, 10,20); 
}

void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP)    yPos++;
    if (keyCode == DOWN)  yPos--;
    if (keyCode == RIGHT) xPos++;
    if (keyCode == LEFT)  xPos--;
  }
}
