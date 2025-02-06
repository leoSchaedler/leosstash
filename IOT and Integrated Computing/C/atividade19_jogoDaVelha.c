#include <Keypad.h>
#include <Adafruit_NeoPixel.h>
#include <SoftwareSerial.h>

#define PIN 12
#define NUMPIXELS 12
#define rxPin 3
#define txPin 2

//  KEYPAD
const byte ROWS = 4;
const byte COLS = 4;
char keys[ROWS][COLS] = {
        {'1','2','3','A'},
        {'4','5','6','B'},
        {'7','8','9','C'},
        {'*','0','#','D'}
};
byte rowPins[ROWS] = {11, 10, 9, 8};
byte colPins[COLS] = {7, 6, 5, 4};
Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS );


char recData;


//  TRANFERENCIA
SoftwareSerial mySerial = SoftwareSerial(rxPin,txPin);

//  FITA LEDs
Adafruit_NeoPixel pixels = Adafruit_NeoPixel(NUMPIXELS, PIN, NEO_GRB + NEO_KHZ800);

//  GLOBAIS
int fita[12];
int vez = 0;    //  vez 1 = jogador 1   |   vez = 2 = jogador 2
const int PLAYER = 1;   //VEZ DO JOGADOR

void setup(){
    pixels.begin();
    pinMode(rxPin, INPUT);
    pinMode(txPin, OUTPUT);
    mySerial.begin(9600);
    Serial.begin(9600);
}

void changeVez (){
    if (vez == 0) { vez = 1;   if(PLAYER == vez){ liga_led(11); } } //DEVE SER UM RANDOM
    if (vez == 1) { vez = 2;   if(PLAYER == vez){ liga_led(11); } }
    if (vez == 2) { vez = 1;   if(PLAYER == vez){ liga_led(11); } }
}

void liga_led(int porta){
    if(vez == 1){ pixels.setPixelColor(porta,pixels.Color(255,0,0)); }
    else if(vez == 2){ pixels.setPixelColor(porta,pixels.Color(0,0,255)); }
    pixels.show();
}

void play(char key){
    if (key != ' ')
    {
        jogar(key);
        changeVez();
    }
    //capturar mensagens de fora
        //jogar
    //capturar mensgens internas
        //jogar
}

void jogar(char casa){
    if (vez == PLAYER){
        if(vencer() == 1){ // JOGADOR n1 GANHOU
            pixels.setPixelColor(3,pixels.Color(255,0,0));
            pixels.show();
        }
        else if(vencer() == 2){ // JOGADOR n2 GANHOU
            pixels.setPixelColor(3,pixels.Color(0,0,255));
            pixels.show();
        }
        else if(vencer() == 3){ //VELHA
            pixels.setPixelColor(3,pixels.Color(0,255,0));
            pixels.show();
        }
        else{
            switch (casa){
                case '1':
                    if(fita[0] == 0){ liga_led(0);    fita[0] = vez; }
                    break;                    
                case '2':
                    if(fita[1] == 0){ liga_led(1);    fita[1] = vez; }
                    break;
                case '3':
                    if(fita[2] == 0){ liga_led(2);    fita[2] = vez; }
                    break;
                case '4':
                    if(fita[3] == 0){ liga_led(4);    fita[3] = vez; }
                    break;
                case '5':
                    if(fita[4] == 0){ liga_led(5);    fita[4] = vez; }
                    break;
                case '6':
                    if(fita[5] == 0){ liga_led(6);    fita[5] = vez; }
                    break;
                case '7':
                    if(fita[6] == 0){ liga_led(8);    fita[6] = vez; }
                    break;
                case '8':
                    if(fita[7] == 0){ liga_led(9);    fita[7] = vez; }
                    break;
                case '9':
                    if(fita[8] == 0){ liga_led(10);   fita[8] = vez; }
                    break;
                }
            
        }
    }
}

int vencer(){
    int result = 0;
    if(fita[0] == 1 && fita[1] == 1 && fita[2] == 1){
        result = 1;
    }
    else if (fita[3] == 1 && fita[4] == 1 && fita[5] == 1){
        result = 1;
    }
    else if (fita[6] == 1 && fita[7] == 1 && fita[8] == 1){
        result = 1;
    }
    else if (fita[0] == 1 && fita[3] == 1 && fita[6] == 1){
        result = 1;
    }
    else if (fita[1] == 1 && fita[4] == 1 && fita[7] == 1){
        result = 1;
    }
    else if (fita[2] == 1 && fita[5] == 1 && fita[8] == 1){
        result = 1;
    }
    else if (fita[0] == 1 && fita[4] == 1 && fita[8] == 1){
        result = 1;
    }
    else if (fita[2] == 1 && fita[4] == 1 && fita[6] == 1){
        result = 1;
    }
    else if(fita[0] == 2 && fita[1] == 2 && fita[2] == 2){
        result = 2;
    }
    else if (fita[3] == 2 && fita[4] == 2 && fita[5] == 2){
        result = 2;
    }
    else if (fita[6] == 2 && fita[7] == 2 && fita[8] == 2){
        result = 2;
    }
    else if (fita[0] == 2 && fita[3] == 2 && fita[6] == 2){
        result = 2;
    }
    else if (fita[1] == 2 && fita[4] == 2 && fita[7] == 2){
        result = 2;
    }
    else if (fita[2] == 2 && fita[5] == 2 && fita[8] == 2){
        result = 2;
    }
    else if (fita[0] == 2 && fita[4] == 2 && fita[8] == 2){
        result = 2;
    }
    else if (fita[2] == 2 && fita[4] == 2 && fita[6] == 2){
        result = 2;
    }
    if(cheio() == 1){
        result = 3;
    }
    return result;
}

int cheio(){
    int cont = 0;
    for(int i = 0; i < 9; i++){ if(fita[i] != 0){ cont++; } }
    if(cont == 9){ return 1; }
    return 0;
}

 void loop(){ 
    char key = keypad.getKey();
    if (mySerial.available() >0){
        Serial.println('R');
        Serial.println("Recebido: " + mySerial.read());
    }
    if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9'){
                Serial.println('E');  
      Serial.println("Enviado: " + key);
        mySerial.println(key);
  }
    //delay(1000);
 }




