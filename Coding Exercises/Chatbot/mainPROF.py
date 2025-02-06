# -*- coding: utf-8 -*-
import io
import random
import string # to process standard python strings
import warnings
import numpy as np
#vou usar o sklearn para os cálculos
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
#novamente código velho é código chato.
import warnings
warnings.filterwarnings('ignore')

#importando tudo da nltk...
import nltk
from nltk.stem import WordNetLemmatizer
nltk.download('popular', quiet=True) # for downloading packages
nltk.download('punkt') # first-time use only
nltk.download('wordnet') # first-time use only

"""## carregando um corpus

O corpus é fundamental para permitir a definição das intensões... eu vou começar montando um arquivo com trechos da wikitpedia e outras páginas, convertido para txt e carregado aqui

"""


#importar o arquivo aqui -
#from google.colab import files #o corpus precisa ser adequado a sua necessidade
#arquivo = files.upload()




"""## Pre-processamento"""

#example1 = "/content/chat3 (1).txt"
example1 = "machineLearn.txt"
file1 = open(example1, "r")
raw = file1.read()
sent_tokens = nltk.sent_tokenize(raw)# lista de sentenças
#sent_tokens

#lemarization
lemmer = nltk.stem.WordNetLemmatizer()
# WordNet dicionário semântico em inglês, incluído na nltk.
def LemTokens(tokens):
    return [lemmer.lemmatize(token) for token in tokens]

#removendo a pontuação
sem_pontuacao = dict((ord(punct), None) for punct in string.punctuation)

def LemNormalize(text):
    return LemTokens(nltk.word_tokenize(text.lower().translate(sem_pontuacao)))

"""## só uma bricadeira

A maior parte das pessoas sempre começa com algum tipo de saudação... então, seria muito interessante se o nosso chat soubesse como responder a isso. Podemos usar um corpus com milhares de saudações ou simplismente usar um truque velho como a ELiZA
"""

GREETING_INPUTS = ("hello", "hi", "greetings", "sup", "what's up","hey",)
GREETING_RESPONSES = ["hi", "hey", "*nods*", "hi there", "hello", "I am glad! You are talking to me"]
# e escolher de forma randômica, uma resposta em um array,
# baseado na entrada que está em outro array.
def greeting(sentence):
     for word in sentence.split():
        if word.lower() in GREETING_INPUTS:
            return random.choice(GREETING_RESPONSES)

"""Vamos usar a distância de cosseno para testar nosso bot. E, vamos criar os vetores usando tf-idf"""

def response(user_response):
    robo_response=''
    #incluo a própria pergunta
    sent_tokens.append(user_response)

    #tfidf
    TfidfVec = TfidfVectorizer(tokenizer=LemNormalize, stop_words='english') #aqui é uma área sujeita a melhorias
    tfidf = TfidfVec.fit_transform(sent_tokens) #tfidf é muito simples e perde conteúdo semântico que tal o word2vec
    vals = cosine_similarity(tfidf[-1], tfidf)

    idx=vals.argsort()[0][-2]
    flat = vals.flatten()
    #ordenando o conjunto de similaridades
    flat.sort()
    #print(flat)
    #imediatamente antes do último será o maior coeficiente de similaridade
    req_tfidf = flat[-2]


    if(req_tfidf==0):
        robo_response=robo_response+"I am sorry! I don't understand you"
        sent_tokens.remove(user_response)
        return robo_response
    else:
        robo_response = robo_response+sent_tokens[idx]
        sent_tokens.remove(user_response)
        return robo_response

flag=True #este sistema especialista é muito pobre
print("ROBO: My name is Robo. I will answer your queries about Chatbots. If you want to exit, type Bye!")
while(flag==True):
    user_response = input()
    user_response=user_response.lower()
    if(user_response!='bye'):
        if(user_response=='thanks' or user_response=='thank you' ):
            flag=False
            print("ROBO: You are welcome..")
        else:
            if(greeting(user_response)!=None):
                print("ROBO: "+greeting(user_response))
            else:
                #print("ROBO: ",end="")
                print(response(user_response))

    else:
        flag=False
        print("ROBO: Bye! take care..")
