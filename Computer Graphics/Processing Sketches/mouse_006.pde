void setup() {
  size(400, 400);
  smooth();
}

void draw() {
  background(204);
  if (mousePressed == true) {cursor(HAND);} 
    else { cursor(MOVE); }
  line(mouseX, 0, mouseX, height);
  line(0, mouseY, height, mouseY);
}

/// opções de cursor
/// ARROW, CROSS, HAND, MOVE, TEXT, or WAIT
