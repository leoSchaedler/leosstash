# -*- coding: utf-8 -*-
"""
Created on Mon Aug 12 08:38:15 2019

@author: hrsch
"""

matrizCredenciais = [["vilmar", "123456"], ["junior", "abcdef"]]

usuario = input("Digite seu usuario: ")
senha = input("Digite sua senha ")


valido = 0

for credencial in matrizCredenciais:
    usuarioCorrente = credencial[0]
    senhaCorrente = credencial[1]
    
    if usuario == usuarioCorrente and senha == senhaCorrente:
        valido = 1
        
        
for i in range (0, 5):
    if valido == 0:
        print("Usuário ou senha inválida!")
    

else:
    print ("Usuário autenticado com sucesso!")
    
    