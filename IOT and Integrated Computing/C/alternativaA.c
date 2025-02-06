#include <Keypad.h>
#include <Wire.h> 
#include <LiquidCrystal.h>

// DECLARACOES ----------------------------------------------------------------------

//  Plugs LCD
const int db7 = 8;
const int db6 = 9;
const int db5 = 10;
const int db4 = 11;
const int e = 12;
const int rs = 13;
//  Instância LCD
LiquidCrystal lcd(rs, e, db4, db5, db6, db7);

//  Keyboard
char customKey;
const byte ROWS = 4;
const byte COLS = 4;
char keys[ROWS][COLS] = {
  {'1','2','3','+'},
  {'4','5','6','-'},
  {'7','8','9','*'},
  {'C','0','=','/'}
};
byte rowPins[ROWS] = {7,6,5,4}; //connect to the row pinouts of the keypad
byte colPins[COLS] = {3,A3,1,0}; //connect to the column pinouts of the keypad
//  Instância Keyboard
Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS); 
//Keypad customKeypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS); 

//  GLOBAIS

int modos = 0;      // modo da operacao

long first = 0;     // primeiro numero decimal
long second = 0;    // segundo numero Decimal
double total = 0;   // total calculadora decimal


long decimal = 0; 

int cont1 = 0;       //contador Binario 1
int cont2 = 0;       //contador Binario 2
int b1 [3] = { };   //Binario 1
int b2 [3] = { };   //Binario 2

String num = ""; 
String n1; 
String num2 = ""; 
String num3 = ""; 
String num4 = ""; 


int protecao = 0;

//  POSICOES
//Posicao Numero 1 (X,Y)
const int PNumreo1X = 0;    const int PNumreo1Y = 1;
//Posicao Operador 1 (X,Y)
const int POperador1X = 3;  const int POperador1Y = 1;
//Posicao Nunemro 2 (X,Y)
const int PNumreo2X = 5;    const int PNumreo2Y = 1;
//Posicao Operador 2 (=) (X,Y)
const int POperador2X = 8;  const int POperador2Y = 1;
//Posicao Resultado (X,Y)
const int PResultadoX = 10; const int PResultadoY = 1;

//  1100011 -> 99.00
//  ________________
//  0123456789012345
const int PS = 6;
const int PN1 = 0;
const int PN2 = 9;

const int PN1B = 0;
const int PSB = 8;
const int PN2B = 11;

// MODE && SETUP --------------------------------------------------------------------

void mode(){
  modos = modos + 1;
  if (modos > 7) { modos = 0; }
  clear();
}

void setup(){ 
  attachInterrupt(0, mode, RISING); // iniciando o botao
  lcd.begin(16, 2);                 // iniciando o lcd
  // print inicial LCD
  lcd.setCursor(1,0);
    lcd.print("Bernardo Tinti");
  lcd.setCursor(2,1);
    lcd.print("Trabalho  14");
  delay(5000);
  lcd.clear();
  lcd.setCursor(0, 0);
}

//  Calculadora Decimal ---------------------------------------------------------------

void calcDec(){
  lcd.setCursor(0,0); lcd.print("Calculadora Dec");

  char key = keypad.getKey();
  switch(key) 
  {
  case '0' ... '9':
    if(key >= '0' && key <= '9' && protecao == 0)
    {
      first = first * 10 + (key - '0');
    }
      lcd.setCursor(PNumreo1X,PNumreo1Y);
      lcd.print(first);
    if ( first > 1000 ){ 
      lcd.clear(); 
      lcd.setCursor(0,0); lcd.print("ERROR"); 
      lcd.setCursor(0,1); lcd.print("Num > 999");
      delay(1500);
      clear();
    }
    break;

  case '+':
  protecao++;
    // printar operador
    lcd.setCursor(POperador1X,POperador1Y); lcd.print("+");
    first = (total != 0 ? total : first);   // primeiro numero
    second = segNumDec();        // segundo numero
    // printar operador de igualdade
    lcd.setCursor(POperador2X,POperador2Y); lcd.print("=");
    total = first + second;         // total
    // printar resultado
    lcd.setCursor(PResultadoX,PResultadoY); lcd.print(total);
    first = 0, second = 0; // reset values back to zero for next use
    break;

  case '-':
  protecao++;
    // printar operador
    lcd.setCursor(POperador1X,POperador1Y); lcd.print("-");
    first = (total != 0 ? total : first);   // primeiro numero
    second = segNumDec();        // segundo numero
    // printar operador de igualdade
    lcd.setCursor(POperador2X,POperador2Y); lcd.print("=");
    total = first - second;         // total
    // printar resultado
    lcd.setCursor(PResultadoX,PResultadoY); lcd.print(total);
    first = 0, second = 0; // reset values back to zero for next use
    break;

  case '*':
  protecao++;
    // printar operador
    lcd.setCursor(POperador1X,POperador1Y); lcd.print("*");
    first = (total != 0 ? total : first);   // primeiro numero
    second = segNumDec();        // segundo numero
    // printar operador de igualdade
    lcd.setCursor(POperador2X,POperador2Y); lcd.print("=");
    total = first * second;         // total
    // printar resultado
    lcd.setCursor(PResultadoX,PResultadoY); lcd.print(total);
    first = 0, second = 0; // reset values back to zero for next use
    break;

  case '/':
  protecao++;
    // printar operador
    lcd.setCursor(POperador1X,POperador1Y); lcd.print("/");
    first = (total != 0 ? total : first); // primeiro numero
    second = segNumDec();        // segundo numero
    second == 0 ? Invalid() : total = (float)first / (float)second;
    // printar operador de igualdade
    lcd.setCursor(POperador2X,POperador2Y); lcd.print("=");
    total = first / second;         // total
    // printar resultado
    lcd.setCursor(PResultadoX,PResultadoY); lcd.print(total);
    first = 0, second = 0; // reset values back to zero for next use
    break;

  case 'C':
  protecao = 0;
    first = 0;  second = 0; total = 0;
    clear();
    calcDec();
    break;
  }
}
long segNumDec(){
  while( 1 )
  {
    customKey = keypad.getKey();
    if(customKey >= '0' && customKey <= '9')
    {
        second = second * 10 + (customKey - '0');
      // printar segundo numero
    lcd.setCursor(PNumreo2X,PNumreo2Y);   
    lcd.print(second);
    }
    if ( second > 1000 ){ 
      lcd.clear(); 
      lcd.setCursor(0,0); lcd.print("ERROR"); 
      lcd.setCursor(0,1); lcd.print("Num > 999");
      delay(1500);  clear();  break;
    }
    if(customKey == '=') break;
    if (customKey == 'C'){ 
      first = 0;  second = 0; total = 0;
      clear();  calcDec(); break;
    }
  }
 return second; 
}

long Invalid(){
  protecao = 0;
  first = 0;  second = 0; total = 0;
  lcd.clear();
  lcd.setCursor(0,0); lcd.print("Operacao");
  lcd.setCursor(0,1); lcd.print("Invalida");
  delay(1500);
  clear();
  calcDec();
}

//  Calculadora Binaria --------------------------------------------------------------

int bin1 = 0;
int bin2 = 0;

int val1 = 0;
int val2 = 0;

int t = 0;

String s1 = "";
String s2 = "";

void calcBin(){
  lcd.setCursor(0,0); lcd.print("Calculadora Bin");

  char key = keypad.getKey();
  switch(key){

    case '0':
      if (cont1 < 4){
        b1[cont1] = 0;
        lcd.setCursor(PNumreo1X+cont1,PNumreo1Y);
        lcd.print(bin1);
        cont1++;
      }
      break;

    case '1':
      if (cont1 < 4){
        b1[cont1] = 1;
        lcd.setCursor(PNumreo1X+cont1,PNumreo1Y);
        lcd.print(bin1);
        cont1++;
      }
    break;  

    case '+':
      //printa o operador
      lcd.setCursor(POperador1X+1,POperador1Y);   lcd.print("+");
      //segundo numero binario
      segBin();
      //printa o operador de iguladade
      lcd.setCursor(POperador2X+1,POperador2Y);   lcd.print('=');
      //operacao

      //printar o resultado
      lcd.setCursor(PResultadoX+1, PResultadoY); lcd.print(total);
      //zerar as variaveis utiliadas
      val1 = 0; val2 = 0;
      bin1 = 0; bin2 = 0;
      t = 0;
    break;

    case '-':
      //printa o operador
      lcd.setCursor(POperador1X+1,POperador1Y);   lcd.print("-");
      //segundo numero binario
      segBin();
      //printa operador de iguladade
      lcd.setCursor(POperador2X+1,POperador2Y);   lcd.print('=');
      //printar o resultado
      lcd.setCursor(PResultadoX+1, PResultadoY);  
      //zerar as variaveis utiliadas
      cont1 = 0;  cont2 = 0;
      b1[0] = 0; b1[1] = 0; b1[2] = 0; b1[3] = 0;
      b2[0] = 0; b2[1] = 0; b2[2] = 0; b2[3] = 0; 
    break;

    case 'C':
      cont1 = 0;  cont2 = 0;
      b1[0] = 0; b1[1] = 0; b1[2] = 0; b1[3] = 0;
      b2[0] = 0; b2[1] = 0; b2[2] = 0; b2[3] = 0; 
      clear();
      calcBin();
    break;

  }
}

void segBin(){

  while(1){
    char key = keypad.getKey();

    if (key == '0' && cont2 < 4)
        {
          s2 = s2 + '0';
          bin2 = bin2 * 10 + 0;
          b2[cont2] = 0;
          lcd.setCursor(PNumreo2X+cont2,PNumreo2Y);
          lcd.print(bin2);
          cont2++;
        }
    if (key == '1' && cont2 < 4)
        {
          bin2 = bin2 * 10 + 1;
          s2 = s2 + '1';
          b2[cont2] = 1;
          lcd.setCursor(PNumreo2X+cont2,PNumreo2Y);
          lcd.print(bin2);
          cont2++;
        }
    if (key == '=') break;
    if (key == 'C')
    {
      cont1 = 0;  cont2 = 0;
      b1[0] = 0; b1[1] = 0; b1[2] = 0; b1[3] = 0;
      b2[0] = 0; b2[1] = 0; b2[2] = 0; b2[3] = 0; 
      clear();
      calcBin();
    }
  }
}


//  Conversor Decimal para Binario ---------------------------------------------------

int convDecBin = 0;

int contD = 0;
int dec = 0;

void decToBin(){
  lcd.setCursor(0,0); lcd.print("Conv. Dec p/ Bina");

  char key = keypad.getKey();
  switch(key){
  case '0' ... '9':
    if(key >= '0' && key <= '9' && contD < 4 && protecao == 0)
    {
      dec = dec * 10 + (key - '0');
      contD++;
    }
      lcd.setCursor(PN1,1);
      lcd.print(dec);
  break;

  case '=':
    protecao++;
    // printar simbolo
    lcd.setCursor(PS - 3, 1);   lcd.print("->");
    // operacao
    convDecToBin();
    // printar resultado
    //lcd.setCursor(PN2 - 3, 1);  lcd.print(String(dec,BIN));
    lcd.setCursor(PN2 - 3, 1);  lcd.print(dec);
    contD = 0; dec = 0;
  break;

  case 'C':
    protecao = 0;
    convDecBin =
    contD = 0; dec = 0;
    clear();
    decToBin();
    break;
  }
}

void convDecToBin(){
  int q = dec;
  while (dec != 0){
    q = dec;
    if (q%2 == 0) convDecBin = convDecBin*10;
    if (q%2 == 1) convDecBin = convDecBin*10 + 1;
    dec = q / 2;
  }
}

//  Conversor Decimal para Octadecimal ------------------------------------------------

void decToOct(){
  lcd.setCursor(0,0); lcd.print("Conv. Dec p/ Octa");

  char key = keypad.getKey();
  switch(key){
  case '0' ... '9':
    if(key >= '0' && key <= '9' && contD < 4 && protecao == 0)
    {
      dec = dec * 10 + (key - '0');
      contD++;
    }
      lcd.setCursor(PN1,1);
      lcd.print(dec);
  break;

  case '=':
  protecao++;
    lcd.setCursor(PS, 1);   lcd.print("->");
    lcd.setCursor(PN2, 1);  lcd.print(String(dec,OCT));
    contD = 0; dec = 0;
  break;

  case 'C':
    protecao = 0;
    contD = 0; dec = 0;
    clear();
    decToOct();
    break;
  }

}

//  Conversor Decimal para Hexadecimal ------------------------------------------------

void decToHex(){
  lcd.setCursor(0,0); lcd.print("Conv. Dec p/ Hexa");

  char key = keypad.getKey();
  switch(key){
  case '0' ... '9':
    if(key >= '0' && key <= '9' && contD < 4 && protecao == 0)
    {
      dec = dec * 10 + (key - '0');
      contD++;
    }
      lcd.setCursor(PN1,1);
      lcd.print(dec);
  break;

  case '=':
  protecao++;
    lcd.setCursor(PS, 1);   lcd.print("->");
    lcd.setCursor(PN2, 1);  lcd.print(String(dec,HEX));
    contD = 0; dec = 0;
  break;

  case 'C':
    protecao = 0;
    contD = 0; dec = 0;
    clear();
    decToHex();
    break;
  }
}

//  Conversor Binario para Decimal ----------------------------------------------------

int contB = 0;

int maxbD = 7;
int bD [7] = { };

double convBinDec = 0;

void binToDec(){
  lcd.setCursor(0,0); lcd.print("Conv. Bin p/ Dec.");

  char key = keypad.getKey();
  switch(key){
  case '0':
    if (contB < maxbD && protecao == 0);
    {
      bD[contB] = 0;
      lcd.setCursor(PN1+contB, 1); lcd.print(bD[contB]);
      contB++;
    }
  break;

  case '1':
    if (contB < maxbD && protecao == 0);
    {
      bD[contB] = 1;
      lcd.setCursor(PN1+contB, 1); lcd.print(bD[contB]);
      contB++;
    }
  break;

  case '=':
    protecao++;
    lcd.setCursor(PSB, 1);   lcd.print("->");
    convBinToDec();
    lcd.setCursor(PN2B, 1);  lcd.print(convBinDec);
    contB = 0; 
  break;

  case 'C':
    protecao = 0;
    bD[0] = 0;  bD[1] = 0;  bD[2] = 0;  bD[3] = 0;
    bD[4] = 0;  bD[5] = 0;  bD[6] = 0;
    contD = 0;  convBinDec = 0;
    clear();
    binToDec();
    break;
  }
}

void convBinToDec(){
  convBinDec = 0;
  for (int i = 0; i < contB; ++i)
  {
    convBinDec += bD[contB - i - 1] * pow(2,i);
  }
}

//  Conversor Binario para Octadecimal ------------------------------------------------

int bO [7] = { };
double convBinOct = 0;
int kbO = 0;


void binToOct(){
  lcd.setCursor(0,0); lcd.print("Conv. Bin p/ Oct");

  char key = keypad.getKey();
  switch(key){
  case '0':
    if (contB < maxbD && protecao == 0);
    {
      bO[contB] = 0;
      lcd.setCursor(PN1+contB, 1); lcd.print(bO[contB]);
      contB++;
    }
  break;

  case '1':
    if (contB < maxbD && protecao == 0);
    {
      bO[contB] = 1;
      lcd.setCursor(PN1+contB, 1); lcd.print(bO[contB]);
      contB++;
    }
  break;

  case '=':
    protecao++;
    lcd.setCursor(PSB, 1);   lcd.print("->");
    convBinToOct();
    kbO = convBinOct;
    lcd.setCursor(PN2B, 1);  lcd.print(String(kbO+1,OCT));
    //lcd.setCursor(PN2B, 1);  lcd.print(convBinOct);
    contB = 0; 
  break;

  case 'C':
    protecao = 0;
    bO[0] = 0;  bO[1] = 0;  bO[2] = 0;  bO[3] = 0;
    bO[4] = 0;  bO[5] = 0;  bO[6] = 0;
    contB = 0;  convBinOct = 0;
    clear();
    binToOct();
    break;
  }
}

void convBinToOct(){
  convBinOct = 0;
  for (int i = 0; i < contB; ++i)
  {
    convBinOct += bO[contB - i - 1] * pow(2,i);
  }
}

//  Conversor Binario para Hexadecimal ------------------------------------------------

int bH [7] = { };
double convBinHex = 0;
int kbH = 0;

void binToHex(){
  lcd.setCursor(0,0); lcd.print("Conv. Bin p/ Hex");

  char key = keypad.getKey();
  switch(key){
  case '0':
    if (contB < maxbD && protecao == 0);
    {
      bH[contB] = 0;
      lcd.setCursor(PN1+contB, 1); lcd.print(bH[contB]);
      contB++;
    }
  break;

  case '1':
    if (contB < maxbD && protecao == 0);
    {
      bH[contB] = 1;
      lcd.setCursor(PN1+contB, 1); lcd.print(bH[contB],OCT);
      contB++;
    }
  break;

  case '=':
    protecao++;
    lcd.setCursor(PS, 1);   lcd.print("->");
    convBinToHex();
    kbH = convBinHex + 1;
    lcd.setCursor(PN2, 1);  lcd.print(String(kbH,HEX));
    contB = 0; 
  break;

  case 'C':
    protecao = 0;
    bH[0] = 0;  bH[1] = 0;  bH[2] = 0;  bH[3] = 0;
    bH[4] = 0;  bH[5] = 0;  bH[6] = 0;
    contB = 0;  convBinHex = 0;
    clear();
    binToHex();
    break;
  }
}

void convBinToHex(){
  convBinHex = 0;
  for (int i = 0; i < contB; ++i)
  {
    convBinHex += bH[contB - i - 1] * pow(2,i);
  }
}

// CLEAR && LOOP ----------------------------------------------------------------------

void clear(){
    lcd.clear();
    lcd.setCursor(0,0); lcd.print("Cleaning ...");
    delay(500);
    lcd.clear();
}


void loop()
{
  switch(modos){
    case 0: calcDec();  break;
    case 1: calcBin();  break;
    case 2: decToBin(); break;
    case 3: decToOct(); break;
    case 4: decToHex(); break;
    case 5: binToDec(); break;
    case 6: binToOct(); break;
    case 7: binToHex();
  }
}

