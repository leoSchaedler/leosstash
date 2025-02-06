
//---------------------------------------
// Keypad Test
//---------------------------------------

#include <Keypad.h>

char key; 
const byte ROWS = 4; //four rows
const byte COLS = 4; //three columns
char keys[ROWS][COLS] = {
  {'1','2','3','A'},
  {'4','5','6','B'},
  {'7','8','9','C'},
  {'*','0','#','D'}
};
byte rowPins[ROWS] = {12, 11, 10, 9}; //connect to the row pinouts of the keypad
byte colPins[COLS] = {8, 7, 6, 5}; //connect to the column pinouts of the keypad


Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS );


// Led ports
int L1 = 14;
int L2 = 15;
int L3 = 16;
int L4 = 17;
int L5 = 18;
int L6 = 19;
int L7 = 2;
int L8 = 3;
int L9 = 4;

void setup()
{
  
  
  Serial.begin(9600);
  pinMode(L1, OUTPUT);
  pinMode(L2, OUTPUT);
  pinMode(L3, OUTPUT);
  pinMode(L4, OUTPUT);
  pinMode(L5, OUTPUT);
  pinMode(L6, OUTPUT);
  pinMode(L7, OUTPUT);
  pinMode(L8, OUTPUT);
  pinMode(L9, OUTPUT);
}


 void loop(){
   
  key = keypad.getKey();
  if (key != NO_KEY){
    Serial.println(key);
    
    switch (key) {
      case '1':
        digitalWrite(L1, HIGH);
        delay(1000);
        digitalWrite(L1, LOW);
        break;
      case '2':
        digitalWrite(L2, HIGH);
        delay(1000);
        digitalWrite(L2, LOW);
        break;
      case '3':
        digitalWrite(L3, HIGH);
        delay(1000);
        digitalWrite(L3, LOW);
        break;
      case '4':
        digitalWrite(L4, HIGH);
        delay(1000);
        digitalWrite(L4, LOW);
        break;
      case '5':
        digitalWrite(L5, HIGH);
        delay(1000);
        digitalWrite(L5, LOW);
        break;
      case '6':
        digitalWrite(L6, HIGH);
        delay(1000);
        digitalWrite(L6, LOW);
        break;
      case '7':
        digitalWrite(L7, HIGH);
        delay(1000);
        digitalWrite(L7, LOW);
        break;
      case '8':
        digitalWrite(L8, HIGH);
        delay(1000);
        digitalWrite(L8, LOW);
        break;
      case '9':
        digitalWrite(L9, HIGH);
        delay(1000);
        digitalWrite(L9, LOW);
        break;
      case '0':
        
        break;
      case '*':
        
        break;
      case '#':
        
        break;
      case 'A':
        
        break;
      case 'B':
        
        break;
      case 'C':
        
        break;
      case 'D':
        
        break;
    }
  }
}
