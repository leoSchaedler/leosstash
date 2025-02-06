void setup() {
  size(400, 400);
}

int value = 0; 
 
void draw() { 
  fill(value); 
  rect(25, 25, 150, 150); 
} 
 
void mouseDragged() 
{ 
  value = value + 5; 
  if (value > 255) { 
    value = 0; 
  } 
} 
