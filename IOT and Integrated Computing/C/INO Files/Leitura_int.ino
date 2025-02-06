int k = 0;

void setup()
{
  Serial.begin(9600);
}

void loop()
{
  Serial.println("Digite um inteiro: ");
  while (Serial.available() == 0) {} // loop de espera
  k = Serial.parseInt();
  Serial.print("Inteiro digitado: ");
  Serial.println(k);
}
