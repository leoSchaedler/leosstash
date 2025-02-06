char c = '?';

void setup()
{
  Serial.begin(9600);
}

void loop()
{
  Serial.println("Digite um carater: ");
  while (Serial.available() == 0) {} // loop de espera
  c = Serial.read(); // lê um byte
  Serial.print("Carater digitado: ");
  Serial.println(c);
}
