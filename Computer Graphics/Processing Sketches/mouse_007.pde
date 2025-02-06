void setup() {
  size(400, 400);
  smooth();
}

int X1, X2, Y1, Y2;

void draw() {
  line(X1,Y1,X2,Y2);
}

void mousePressed() {
  X1 = mouseX;
  Y1 = mouseY;
}

void mouseReleased() {
  X2 = mouseX;
  Y2 = mouseY;
} 
