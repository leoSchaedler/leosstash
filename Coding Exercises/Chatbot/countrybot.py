## Imports and Setups ##
import io
import random
import string
import warnings
import numpy as np

from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

import warnings
warnings.filterwarnings('ignore')

import nltk
from nltk.stem import WordNetLemmatizer
nltk.download('popular', quiet=True)
nltk.download('punkt')
nltk.download('wordnet')


## Pre Processamento ##

corpus = "machineLearn.txt"
file = open(corpus, "r")
raw = file.read()

sent_tokens = nltk.sent_tokenize(raw)
lemmer = nltk.stem.WordNetLemmatizer() ## Processo de Lemarization

sem_pontuacao = dict((ord(punct), None) for punct in string.punctuation)

## Functions ##

def LemTokens(tokens):
    return [lemmer.lemmatize(token) for token in tokens]

def LemNormalize(text):
    return LemTokens(nltk.word_tokenize(text.lower().translate(sem_pontuacao)))


GREETING_INPUTS = ("hello", "hi", "greetings", "sup", "what's up","hey","wassup")
GREETING_RESPONSES = ["hi", "hey", "*nods*", "hi there", "hello", "I am glad! You are talking to me"]

def greeting(sentence):
     for word in sentence.split():
        if word.lower() in GREETING_INPUTS:
            return random.choice(GREETING_RESPONSES)


def response(user_response):
    robo_response=''
    sent_tokens.append(user_response)

    TfidfVec = TfidfVectorizer(tokenizer=LemNormalize, stop_words='english')
    tfidf = TfidfVec.fit_transform(sent_tokens)
    vals = cosine_similarity(tfidf[-1], tfidf)

    idx=vals.argsort()[0][-2]
    flat = vals.flatten()

    flat.sort()
    req_tfidf = flat[-2]

    if(req_tfidf==0):
        robo_response=robo_response+"I'm having trouble understanding you, could you try a different sentence?"
        sent_tokens.remove(user_response)
        return robo_response
    else:
        robo_response = robo_response+sent_tokens[idx]
        sent_tokens.remove(user_response)
        return robo_response

flag=True
print("GeoBot: Hello! My name is GeoBot, I'm here to answer all kinds of different geography questions!")
while(flag==True):
    user_response = input()
    user_response=user_response.lower()
    if(user_response!='bye'):
        if(user_response=='thanks' or user_response=='thank you' ):
            flag=False
            print("GeoBot: You're welcome..")
        else:
            if(greeting(user_response)!=None):
                print("GeoBot: "+greeting(user_response))
            else:
                print(response(user_response))
    else:
        flag=False
        print("GeoBot: Bye! take care..")
