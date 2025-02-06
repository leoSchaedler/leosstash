#include <Servo.h>

int valor_output_1 = 0;
int valor_sensor_1 = 0;
int valor_sensor_2 = 0;
int const sensor_de_gas = A1;
int const LDR = A0;
int limite = 400;

long lerDistanciaUltrassonica(int entradaGatilho, int entradaEcho)
{
  pinMode(entradaGatilho, OUTPUT);  // Limpa o gatilho
  digitalWrite(entradaGatilho, LOW);
  delayMicroseconds(2);
// Coloca o gatilho para o estado HIGH por 10 microsegundos
  digitalWrite(entradaGatilho, HIGH);
  delayMicroseconds(10);
  digitalWrite(entradaGatilho, LOW);
  pinMode(entradaEcho, INPUT);
  // Lê a entrada echo e retorna a viagem da onda de som em microsegundos
  return pulseIn(entradaEcho, HIGH);
}

Servo servo_7;

void setup()
{
   Serial.begin(9600);		// inicializa a comunicação serial
  pinMode(A0, INPUT);		//LDR
  pinMode(A1,INPUT);      	//sensor de gás
  pinMode(13, OUTPUT);		// conecta ao relay
  servo_7.attach(7, 500, 2500); // motor servo

  pinMode(8,OUTPUT);     	// manda o sinal para o alarme de piezo
  pinMode(9, INPUT);		// manda o sinal para o PIR	
  pinMode(10, OUTPUT);		// manda o sinal para o npn como um switch
  pinMode(4, OUTPUT);		// LED vermelho
  pinMode(3, OUTPUT);		// LED verde
 
}

void loop()
{
  
     //------controle de intensidade de luz------//

    int val1 = analogRead(LDR);
  if (val1 > 500) 
  	{
    	digitalWrite(13, LOW);
    Serial.print("Lâmpada ON = ");
    Serial.print(val1);
  	} 
  else 
  	{
    	digitalWrite(13, HIGH);
     Serial.print("Lâmpada OFF = ");
    Serial.print(val1);
  	}

        //------ controle de luz e fan --------// 

  valor_sensor_2 = digitalRead(9);
  if (valor_sensor_2 == 0) 
  	{
    	digitalWrite(10, LOW); // npn como switch OFF
    	digitalWrite(4, HIGH); // LED vermelho ON, sem movimentção
    	digitalWrite(3, LOW); // LED verde OFF, sem movimentção detectada
    Serial.print("     || Movimentação não detectada    " );
  	}
 
  if (valor_sensor_2 == 1) 
  	{
    	digitalWrite(10, HIGH);//npn como switch ON
    delay(3000);
    	digitalWrite(4, LOW); // LED vermelho OFF 
    	digitalWrite(3, HIGH);// LED verde ON , indicando movimentação detectada
     Serial.print(" 	   || Movimentação detectada!      " );
  	}
  delay(300);
  

       // ------- Gas Sensor --------//

int val = analogRead(sensor_de_gas);      // lê o valor do sensor
  Serial.print("|| Valor do sensor do gás = ");
  Serial.print(val);				   // imprimir no monitor serial
//val = map(val, 300, 750, 0, 100); 
  if (val > limite)
  	{
    	tone(8, 650);
  	}
 	delay(300);
 	noTone(8);

      //-------  servo motor  ---------//

  valor_sensor_1 = 0.01723 * lerDistanciaUltrassonica(6, 6);

  if (valor_sensor_1 < 100) 
  	{
    	servo_7.write(90);
    Serial.print(" 	  || Porta aberta!  ; Distância = ");
    Serial.print(valor_sensor_1);
   Serial.print("\n");
 
  	} 
  else 
  	{
    	servo_7.write(0);
    Serial.print(" 	  || Porta fechada! ; Distância =  ");
    Serial.print(valor_sensor_1);
    Serial.print("\n");
  }
  delay(10); // Delay para aumentar a performance da simulação
}