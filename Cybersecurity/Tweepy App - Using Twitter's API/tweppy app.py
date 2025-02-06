from __future__ import absolute_import, print_function

from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import json
nike = 0
adidas = 0
puma = 0

class AssinanteTwitter(StreamListener):
    #Essa função será invocada automaticamente toda vez que um twitter for identificado
    def on_data(self, data):


        conteudoJSON = json.loads(data)
        print(conteudoJSON["text"])
        if conteudoJSON["text"].casefold().find('nike') >= 1:
            nike = nike + 1
        if conteudoJSON["text"].casefold().find('adidas') >= 1:
            adidas = adidas + 1
        if conteudoJSON["text"].casefold().find('puma') >= 1:
            puma = puma + 1
        print(nike, adidas, puma)
        return True

    # Essa função será invocada automaticamente toda vez que ocorrer um erro
    def on_error(self, status):
        print(status)

# Para executar esse exemplo é preciso possuir uma conta no twitter, caso não possua crie uma.
# Entre no site http://apps.twitter.com e crie uma nova applicação preenchendo as informações
# Será gerado o consumer key e o consumer secret, que são a identificação de sua aplicação no twitter.
print("Inicio do programa")
consumer_key="ixY3S9AEnRd1IkdGjqBh5YWCP"
consumer_secret="UNKjDHayHyXNsvk4chR49Uq4y5R2qfHOQszSew4nLQlksnNV0M"

#Você será redirecionado para outra página, clique na aba 'Keys and Access Tokens'
#Crie um token de acesso novo, ele será utilizado no lugar de suas credenciais
access_token="1544109991-vd8jcyHzumvjEKiAQrjyT6rY9qxhLwlI58yAEr0"
access_token_secret="XMvVMVJSIQi71lBc56LmtZy25aMb25b0pssbo4SBFVUpD"

assinante = AssinanteTwitter()
auth = OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

stream = Stream(auth, assinante)
stream.filter(track=['nike', 'puma', 'adidas'], languages=["en"])


