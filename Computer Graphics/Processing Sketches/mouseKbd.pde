void setup() {
  size(200, 200);
  background(128);
  fill(255);
  noStroke();
}

void draw() {
  if(keyPressed)
  {
  if (key=='v' || key=='V') 
  { 
    fill(255,0,0);
  }
  if (key=='a' || key=='A') 
  { 
    fill(0,0,255);
  }
  } 
}

void mousePressed() {
  ellipse(mouseX, mouseY, 20,20);
}
