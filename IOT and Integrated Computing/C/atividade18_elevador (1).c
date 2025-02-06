/*
Logica a ser desenvolvida: quando um botão é apertado ele vai descidir desce ou sobe de acordo com a posicão atual do elevador,
o elevador vai estar andando dentro de uma lista, em cada index da lista vai estar claro com 1 ou 0 se o elevador deve subir ou descer após isso
durante a subida do elevador vai ter um momento recioso dele representando a troca de andares.
*/

#include <Adafruit_NeoPixel.h>

//		PORTAS 
//	LED
int ledOperante = 12;
int ledPorta = 13;
int ledEmergencia = 11;

//	FITA LED
int numeroLED = 12;
int portaLED = 4;
Adafruit_NeoPixel fitaLED = Adafruit_NeoPixel(numeroLED, portaLED, NEO_GRB + NEO_KHZ800);

//	GLOBAIS
int nAdares = 10;							//	Numero de Andares
int operante = 0;
//					T 1 2 3 4 5 6 7 8 9
int andares [10] = {0,0,0,0,0,0,0,0,0,0};	//	Lista de requisições
int andarAtual = 0; 						// 	Andar atual em que o elevador está
int andarProx = 0;							// 	Proximo andar que o elevador irá
int estado = 0; 							// 	1 = subindo	||	0 = parado	|| 	-1 = descendo
int porta = 0;								//	1 = Aberta || 0 = Fechada
int statusEmergencia = 0;
int portaAberta = 1;

void setup(){

	Serial.begin(9600);
	fitaLED.begin();
	pinMode( ledOperante, OUTPUT );	
	pinMode( ledPorta, OUTPUT );
	pinMode( ledEmergencia, OUTPUT );
	attachInterrupt(1, chamada, FALLING);
	digitalWrite(ledPorta, HIGH);

}

void chamada(){
	int bot = analogRead(A5);
	Serial.println(bot);
	switch (bot){
		//ANDARES
		case 25:  chamadaFIxa(0); break;	//T
		case 50:  chamadaFIxa(1); break;	//1
		case 73:  chamadaFIxa(2); break;	//2
		case 95:  chamadaFIxa(3); break;	//3
		case 116: chamadaFIxa(4); break;	//4
		case 136: chamadaFIxa(5); break;	//5
		case 155: chamadaFIxa(6); break;	//6
		case 173: chamadaFIxa(7); break;	//7
		case 191: chamadaFIxa(8); break;	//8
		case 208: chamadaFIxa(9); break;	//9
		case 240: operaPorta(); 		  break;	//Porta
		case 224: emergencia();   break;	//Emergencia
		case 269: opera(1); 	  break;	//ON
		case 255: opera(0);		  break;	//OFF
		//SOBE
		case 283: chamadaSobe(0); break;	//T
		case 296: chamadaSobe(1); break;	//1
		case 309: chamadaSobe(2); break;	//2
		case 322: chamadaSobe(3); break;	//3
		case 334: chamadaSobe(4); break;	//4
		case 346: chamadaSobe(5); break;	//5
		case 357: chamadaSobe(6); break;	//6
		case 368: chamadaSobe(7); break;	//7
		case 378: chamadaSobe(8); break;	//8
		//DESCE
		case 388: chamadaDesce(1); break;	//1
		case 398: chamadaDesce(2); break;	//2
		case 408: chamadaDesce(3); break;	//3
		case 417: chamadaDesce(4); break;	//4
		case 426: chamadaDesce(5); break;	//5
		case 435: chamadaDesce(6); break;	//6
		case 443: chamadaDesce(7); break;	//7
		case 452: chamadaDesce(8); break;	//8
		case 460: chamadaDesce(9); break;	//9
	}

}


int xeca(){
	int x = andarAtual;
	if (operante == 1)
	{
		if (x == 0) estado = 	1;
		if (x == 9) estado = 	-1;
		//caso ele n estiver descendo de volta conferir condicoes dentro do while
		while(x < nAdares || x > -1 && andarProx == andarAtual){
			x = x + (1 * estado);
			if (andares[x] == estado)
			{
				x = andarProx;
				movimento();
				//movimenta p/ tal andar
			}	
		} inverteSentido();
	}
	
}

void movimento(){
	while(andarAtual != andarProx) andarAtual = andarAtual + (1 * estado);
	/*
	fazer movimentação de luzes
	NAO MUDAR O ESTADO, isso ele vai ser mudado no loop
	*/
}

void inverteSentido(){
	if (estado == 1) estado = -1;
	else if (estado == -1) estado = 1;
	else if (estado == 0) estado = 0;
}


void chamadaFIxa(int floor){
	if (andarAtual > floor) { andares[floor] = -1 }
	if (andarAtual < floor) { andares[floor] = 1 }
}

void chamadaSobe(int floor){ andares[floor] = 1; }

void chamadaDesce(int floor){ andares[floor] = -1 }

// Tempo Recioso entre um andar e outro 

int tempoDelay = 100; int tempoDelayTotal = 20;
void movimento(int floor){
	int cont = 0;
	while (cont != tempoDelayTotal+1){
		// LED andar atual ligado
			fita.setPixelColor(floor, fita.Color(0, 0, 255)); //ON
		// LED do proximo andar piscando 
			fita.setPixelColor(floor + estado, fita.Color(0, 0, 255)); //ON
			delay(tempoDelay);
			fita.setPixelColor(floor + estado, fita.Color(0, 0, 0)); //OFF	
			delay(tempoDelay);
		cont++;
		// checar se algum andar foi requisitado
	}
}

void operaPorta(){
	if (portaAberta == 0){
		portaAberta = 1;	//Porta aberta
		digitalWrite(ledPorta, HIGH);	
		Serial.println("PORTA ABERTA");
	} else if (portaAberta == 1){
		portaAberta = 0;	//Porta Fechada
		digitalWrite(ledPorta, LOW);	
		Serial.println("PORTA FECHADA");
	} 
}

void emergencia(){
	if (statusEmergencia == 0){
		statusEmergencia = 1;	//Emergência acionada
		digitalWrite(ledEmergencia, HIGH);	
		Serial.println("EMERGENCIA ACIONADA");
	} else if (statusEmergencia == 1){
		statusEmergencia = 0;	//Emergência desativada
		digitalWrite(ledEmergencia, LOW);	
		Serial.println("EMERGENCIA DESATIVADA");
	} 
}

void loop(){}

/*
void opera(int o){
	if (o == 1){
		operante = 1;	//Elevador ON
		digitalWrite(ledOperante, HIGH);	
		Serial.println("ON");
	} else if (o == 0){
		operante = 0;	//Elevador OFF
		digitalWrite(ledOperante, LOW);	
		Serial.println("OFF");
	} 
}

void chama(){
}

void checagemGeral(){
	int c = 0;
	if(estado == 0 && operante == 1){
		while(estado == 0 || c != nAdares){
			if (andares[c] == 1)
			{
				proxAndar = c;
				estado = 1;
			} else if (andares[c] == -1)
			{
				proxAndar = c;
				estado = -1;
			}
			c++;
		}
	}
}

void mover(){
		while(andar != proxAndar){
			andar = andar + estado;
			movimento(andar);
			check();
		}
}

void check(){
	for (int i = andar; i < nAdares; ++i)
	{
		if (andares[i] == 1 && estado == 1)
		{
			proxAndar = i;
		} else if(andares[i] == -1 && estado == -1){
			proxAndar = i;
		}
	}
	if (andar == proxAndar)
	{
		//andar = proxAndar;
		andares[andar] = 0;
	}
}
*/


