char c1 = '?';
char c2 = '?';

void setup()
{
  Serial.begin(9600);
}

void loop()
{
  Serial.println("Digite dois carateres: ");
  
  while (Serial.available() == 0) {} // loop de espera
  c1 = Serial.read(); // lê um byte
  
  while (Serial.available() == 0) {} // loop de espera
  c2 = Serial.read(); // lê um byte
  
  Serial.print("Carateres digitados: ");
  Serial.print(c1);
  Serial.println(c2);
}
