void setup() {
  size(200, 200);
}

void draw() { }

void mousePressed() {
  if(mouseButton==LEFT)  fill(124); else fill(10);
  ellipse(mouseX, mouseY, 20,20);
}
