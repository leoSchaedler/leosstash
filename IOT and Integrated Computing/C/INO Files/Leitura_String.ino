String s = "?";

void setup()
{
  Serial.begin(9600);
}

void loop()
{
  Serial.println("Digite uma string: ");
  while (Serial.available() == 0) {} // loop de espera
  s = Serial.readString();
  Serial.print("String digitado: ");
  Serial.println(s);
}
