float k = 0.0;
String texto = "0.0";

void setup()
{
  Serial.begin(9600);
}

void loop()
{
  Serial.println("Digite um numero real: ");
  while (Serial.available() == 0) {} // loop de espera
  texto = Serial.readString();
  k = texto.toFloat();
  Serial.print("Numero real digitado: ");
  Serial.println(k);
}
