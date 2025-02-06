void setup() {
  size(400, 400);
  noStroke();
  smooth();
}

void draw() {
  float x = mouseX;
  float y = mouseY;
  float ix = width - mouseX; // Inverse X
  float iy = mouseY - height; // Inverse Y
  background(126);
  fill(255, 150, 132, 222);
  ellipse(x, height/2, y, y);
  fill(0, 159, 255, 111);
  ellipse(ix, height/2, iy, iy);
}
