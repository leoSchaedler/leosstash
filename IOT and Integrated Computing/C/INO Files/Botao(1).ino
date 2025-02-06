const int LED_VERMELHO = 8;
const int LED_AZUL = 9;
const int BOTAO = 2;

const int TEMPO_VERMELHO_LIGADO = 5000;
const int TEMPO_VERMELHO_DESLIGADO = 2000;
const int TEMPO_AZUL_LIGADO = 3000;

bool botao_apertado = false;

//--------------------------------------------------------

void ligue(int porta)
{
  digitalWrite(porta, HIGH);
}

//--------------------------------------------------------

void desligue(int porta)
{
  digitalWrite(porta, LOW);
}

//--------------------------------------------------------
// função para tratamento da interrupção gerada pelo botão

void int_botao()
{
  botao_apertado = true;
}

//--------------------------------------------------------

void setup()
{
  pinMode(LED_VERMELHO, OUTPUT);
  pinMode(LED_AZUL, OUTPUT);
  attachInterrupt(BOTAO - 2, int_botao, RISING);
}

//--------------------------------------------------------

void loop()
{
  ligue(LED_VERMELHO);
  delay(TEMPO_VERMELHO_LIGADO);
  desligue(LED_VERMELHO);
  delay(TEMPO_VERMELHO_DESLIGADO);
  
  if (botao_apertado)
  {
    ligue(LED_AZUL);
    delay(TEMPO_AZUL_LIGADO);
    desligue(LED_AZUL);
    botao_apertado = false;
  }
}

//--------------------------------------------------------
