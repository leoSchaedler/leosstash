# https://mydevices.com/cayenne/docs_stage/cayenne-mqtt-api/#cayenne-mqtt-api-manually-publishing-subscribing
# Manually Publishing / Subscribing

# Biblioteca para o protocolo MQTT
import paho.mqtt.client as mqtt
import time
import RPi.GPIO as GPIO


# Variáveis de acesso ao servidor Cayenne
user = 'fef5d8a0-06ea-11ea-8221-599f77add412'
password = '7cbf6eb4802f4b02a167834a1deae3739cf34f9f'
client_id = '05da1140-06eb-11ea-84bb-8f71124cfdfb'
server = 'mqtt.mydevices.com'
port = 1883


# Cria o objeto para acessar o servidor (Client ID)
client = mqtt.Client(client_id)

# Informa ao objeto o usuÃ¡rio e senha para acesso ao servidor (MQTT username, MQTT password)
client.username_pw_set(user, password)

# Estabelece a conexÃ£o com o servidor (MQTT Server, MQTT Port)
client.connect(server, port)

# Realiza a assinatura para receber mensagens quando houver mudanÃ§as no canal 2 (botÃ£o)
client.subscribe('v1/'+user+'/things/'+client_id+'/cmd/2')


def callback1(channel):
    if GPIO.input(channel):
        print('Alagamento nível pequeno')
        client.publish('v1/' + user + '/things/' + client_id + '/data/1', GPIO.input(channel))
        print(GPIO.input(channel))
    else:
        print('Nenhuma água detectada')
        client.publish('v1/' + user + '/things/' + client_id + '/data/1', GPIO.input(channel))
        print(GPIO.input(channel))

def callback2(channel):
    if GPIO.input(channel):
        print('Alagamento nível extremo')
        client.publish('v1/' + user + '/things/' + client_id + '/data/2', GPIO.input(channel))
        print(GPIO.input(channel))
    else:
        print('Nenhuma água detectada')
        client.publish('v1/' + user + '/things/' + client_id + '/data/2', GPIO.input(channel))
        print(GPIO.input(channel))


# Configurações do pino GPIO
channel1 = 25
GPIO.setmode(GPIO.BCM)
GPIO.setup(channel1, GPIO.IN)

# Vamos cadastrar a função de callback para o nosso pino
GPIO.add_event_detect(channel1, GPIO.BOTH, bouncetime=300)
GPIO.add_event_callback(channel1, callback1)

# Configurações do pino GPIO
channel2 = 18
GPIO.setmode(GPIO.BCM)
GPIO.setup(channel2, GPIO.IN)

# Vamos cadastrar a função de callback para o nosso pino
GPIO.add_event_detect(channel2, GPIO.BOTH, bouncetime=300)
GPIO.add_event_callback(channel2, callback2)


while True:
    callback1(channel1)
    callback2(channel2)
    time.sleep(5)



