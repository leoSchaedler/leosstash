# -*- coding: utf-8 -*-
"""
Created on Mon Aug 12 08:17:00 2019

@author: hrsch
"""

def login(a,b):
    novoLogin = a
    novaSenha = b
    print ("Seu novo login é ", novoLogin  ,"e sua nova senha é " , novaSenha)
    x = input("Deseja tentar realizar login?  [y/n]")
    if x == "n":
        pass
    
        login = input("Digite seu login")
        senha = input("Agora sua senha")
        if senha == novaSenha and login == novoLogin:
            print("Bem vindo, " , login)
        else:
            print("ACESSO NEGADO")
    
login("porra","caramba")