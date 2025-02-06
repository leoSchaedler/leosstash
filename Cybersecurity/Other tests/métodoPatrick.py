# -*- coding: utf-8 -*-
"""
Created on Mon Aug 12 09:46:51 2019

@author: hrsch
"""

tentativas = {"Patrick": 0, "Loko": 0}

def login():
    loginu = input(str("Favor inserir o login: "))
    senha = input(str("Favor inserir a senha: "))
    dic = {"Patrick": "senha" , "Loko": "areia"}
    
    if (tentativas[loginu]) >= 5:
        print("Conta inativa, favor contatar o admnistrador")
    else:
    
        if loginu in dic and senha != dic[loginu]:
            print("Login ou senha incorreto, tente novamente")
            tentativas[loginu] = tentativas[loginu] +1
            login()
        
        
        else:
            print("Bem vinda, minha jóia")
            

login()