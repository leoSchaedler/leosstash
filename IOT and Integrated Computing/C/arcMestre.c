//  BIBLIOTECAS
#include <Adafruit_NeoPixel.h>
#include <SoftwareSerial.h>
#include <Wire.h>
#include <LiquidCrystal.h>

// COMUNICAÇÃO
  //temperatura
    #define rxTMP 9
    #define txTMP 8
    SoftwareSerial masterTMP = SoftwareSerial(rxTMP,txTMP);
  //luz
    #define rxLuz 7
    #define txLuz 6
    SoftwareSerial masterLuz = SoftwareSerial(rxLuz,txLuz);
  //atuadores
    #define rxAt 4
    #define txAt 5
    SoftwareSerial slaveAt = SoftwareSerial(rxAt,txAt);

//  LCD
const int db7 = 8;
const int db6 = 9;
const int db5 = 10;
const int db4 = 11;
const int e = 12;
const int rs = 13;
//  Instância LCD
LiquidCrystal lcd(rs, e, db4, db5, db6, db7);

//  BOTOES
const int botMais = 0;
const int botMenos = 1;

//  POTENCIOMETRO
const int portaPot = A0;

// GLOBAIS
  char rec[4] = {'a','a','a','a'};
  char txt[4] = {'a','a','a','a'};
  int k = 0;
  int estado = 0;
  //temperatura
  int tempAtual = 26;
  int tempSet = 26;
  bool estadoTemp = false;
  //luz
  int lightLimit = (255 * 254)/2;
  int lightAtual = 0;
  int lightSet = 0;
  bool estadoLight = false;

enum
{
  INICIALIZANDO,
  RECEBE_TEMPERATURA,
  RECEBE_LUZ,
  LEITURA,
  CONTROLE,
  ENVIA,
  SINCRONIA
} mode = INICIALIZANDO;

void setup(){
  // Iniciando comunicacao
    //luz
      pinMode(rxLuz, INPUT);
      pinMode(txLuz, OUTPUT);
      masterLuz.begin(9600);
    //temperatura
      pinMode(rxTMP, INPUT);
      pinMode(txTMP, OUTPUT);
      masterTMP.begin(9600);
    //atuadores
      pinMode(rxAt, INPUT);
      pinMode(txAt, OUTPUT);
      slaveAt.begin(9600);
  // Iniciando o terminal
    Serial.begin(9600);
  // Iniciando botões
    attachInterrupt(botMais, higtTemp, RISING);
    attachInterrupt(botMenos, lowTemp, RISING);
  // Iniciando potenciometro
    pinMode(portaPot, INPUT);
  // Iniciando LCD
    lcd.begin(16,2);
}

// Funcoes operacionais //

void lcdBegin(){
  int wait = 1500;
  lcd.setCursor(0,0); lcd.print("Bernardo Tinti");
  lcd.setCursor(0,1); lcd.print("Felipe Gomes");
  delay(wait); lcd.clear();
  lcd.setCursor(0,0); lcd.print("Joao Krol");
  lcd.setCursor(0,1); lcd.print("Leonardo Ribeiro");
  delay(wait); lcd.clear();
  lcd.setCursor(5,0); lcd.print("Casa");
  lcd.setCursor(2,1); lcd.print("Inteligente");
  delay(wait);
}

void lcdPrint(){
  lcd.clear();
  lcd.setCursor(0,0); lcd.print("Temp.Atual:");
  lcd.setCursor(12,0); lcd.print(tempAtual);
  lcd.setCursor(15,0); lcd.print("C");

  lcd.setCursor(0,1); lcd.print("Temp. Set.:");
  lcd.setCursor(12,1); lcd.print(tempSet);
  lcd.setCursor(15,1); lcd.print("C");
}

void higtTemp(){
  tempSet++;
  if (tempSet > 125) { tempSet = 125; }
  if (tempSet < -40) { tempSet = -40; }
  lcdPrint();
}

void lowTemp(){
  tempSet--;
  if (tempSet > 125) { tempSet = 125; }
  if (tempSet < -40) { tempSet = -40; }
  lcdPrint();
}

void controlLight(){
  lightSet = map(analogRead(portaPot), 0, 1023, 0, 255);
}

void setLight(){
  int luz = lightAtual * lightSet;
  if (luz >= lightLimit) { estadoLight = false; }
  if (luz < lightLimit) { estadoLight = true; }
}

void setTemp(){
  if (tempAtual > tempSet) { estadoTemp = true; }
  if (tempAtual <= tempSet) { estadoTemp = false; }
}

void recebeLight()
{
  Serial.println("RECEBE");
  while (!(masterLuz.available()) > 0) {}
  delay(1000);
  int cont = 0;
  while (cont < 4) {
    cont++;
    char c = masterLuz.read();
    Serial.print('[');
    Serial.print(c);
    Serial.print(']');
   Serial.print(" (ainda disponivel: ");
   Serial.print(masterLuz.available());
   Serial.println(')');
 } cont = 0;
  Serial.println("The Number: ");
  while (cont < 4) {
    char c = masterLuz.read();
    Serial.print('[');
    Serial.print(c);
    Serial.print(']');
    Serial.print(" (ainda disponivel: ");
    Serial.print(masterLuz.available());
    Serial.println(')');
    rec[cont] = c;
    cont++;
  }
}

void recebeTemp()
{
  Serial.println("RECEBE");
  while (!(masterTMP.available()) > 0) {}
  delay(1000);
  int cont = 0;
  while (cont < 4) {
    cont++;
    char c = masterTMP.read();
    Serial.print('[');
    Serial.print(c);
    Serial.print(']');
   Serial.print(" (ainda disponivel: ");
   Serial.print(masterTMP.available());
   Serial.println(')');
 } cont = 0;
  Serial.println("The Number: ");
  while (cont < 4) {
    char c = masterTMP.read();
    Serial.print('[');
    Serial.print(c);
    Serial.print(']');
    Serial.print(" (ainda disponivel: ");
    Serial.print(masterTMP.available());
    Serial.println(')');
    rec[cont] = c;
    cont++;
  }
}

void envia(){
  Serial.print("Enviando: ");
  slaveAt.write("lixo");
  slaveAt.write(estado);
  Serial.print(estado);
  delay(1000);
  Serial.println("");
}

char intTOchar(int a){
  switch (a){
      case 1:
          return '1';

      case 2:
          return '2';

      case 3:
          return '3';

      case 4:
          return '4';

      case 5:
          return '5';

      case 6:
          return '6';

      case 7:
          return '7';

      case 8:
          return '8';

      case 9:
          return '9';

      case 0:
          return '0';
  }
}

int charTOint(char a){
    switch (a){
        case '1':
            return 1;

        case '2':
            return 2;

        case '3':
            return 3;

        case '4':
            return 4;

        case '5':
            return 5;

        case '6':
            return 6;

        case '7':
            return 7;

        case '8':
            return 8;

        case '9':
            return 9;

        case '0':
            return 0;
    }
}

void destratarTemp(){
  	tempAtual = 0;
    tempAtual = (tempAtual * 10) + charTOint(rec[1]);
    tempAtual = (tempAtual * 10) + charTOint(rec[2]);
    tempAtual = (tempAtual * 10) + charTOint(rec[3]);
    if (rec[0] == '0') { tempAtual = tempAtual * 1; }
    else if (rec[0] == '1') { tempAtual = tempAtual * -1; }
}

void destratarLight(){
  	lightAtual = 0;
    lightAtual = (lightAtual * 10) + charTOint(rec[1]);
    lightAtual = (lightAtual * 10) + charTOint(rec[2]);
    lightAtual = (lightAtual * 10) + charTOint(rec[3]);
    if (rec[0] == '0') { lightAtual = lightAtual * 1; }
    else if (rec[0] == '1') { lightAtual = lightAtual * -1; }
}

// Funcoes de estados //

void begin(){
  Serial.println("INICIALIZANDO");
  lcdBegin();
  lcdPrint();
  mode = RECEBE_TEMPERATURA;
}

void recebeTemperatura(){
  Serial.println("RECEBE_TEMPERATURA");
  recebeTemp();
  destratarTemp();
  Serial.println(tempAtual);
  mode = RECEBE_LUZ;
}

void recebeLuz(){
  Serial.println("RECEBE_LUZ");
  recebeLight();
  destratarTemp();
  Serial.println(lightAtual);
  mode = LEITURA;
}

void leitura(){
  Serial.println("LEITURA");
    lcdPrint();
    controlLight();
  mode = CONTROLE;
}

void controle(){
  Serial.println("CONTROLE");
  setLight();
  setTemp();
  if (!estadoTemp && !estadoLight) { estado = 0; } //TMP: OFF|| LUZ = OFF
  if (!estadoTemp && estadoLight) {  estado = 2; } //TMP: OFF|| LUZ = ON
  if (estadoTemp && !estadoLight) {  estado = 3; } //TMP: ON || LUZ = OFF
  if (estadoTemp && estadoLight) {   estado = 4; } //TMP: ON || LUZ = ON
  sincronia();
  mode = ENVIA;
}

void enviando(){
  Serial.println("ENVIA");
  envia();
  mode = RECEBE_TEMPERATURA;
}

void sincronia(){
  delay(0);
}

void loop()
{
  switch (mode)
  {
  case INICIALIZANDO:
    begin();
    break;
  case RECEBE_TEMPERATURA:
    recebeTemperatura();
    break;
  case RECEBE_LUZ:
    recebeLuz();
    break;
  case LEITURA:
    leitura();
    break;
  case CONTROLE:
    controle();
    break;
  case ENVIA:
    enviando();
    break;
  }
}
