#!/usr/bin/env python3

# MSG := "origem:msg"
# (se origem == "NULL", inicia)
#
# Formato:
#      entidade.py <id> <v1> <v2> ...
#

#DUPLA: PATRICK CAETANO e LEONARDO RIBEIRO

from pika import BlockingConnection
from sys import argv
from enum import Enum

ok = False
pronto = False
iniciador = False
recebidos = []
prontos = []
first = ''
def envia(msg, dests):
    msg = idx + ":" + msg
    for d in dests:
        canal.basic_publish(exchange="", routing_key=d, body=msg)

def recebendo(msg, origem):
        global ok
        global recebidos
        global iniciador
        global pronto
        global first    
        if msg == "Pronto":
                print(f"{origem} pronto")
                if iniciador == True and pronto == False:
                        prontos.append(origem)
                        prontos.sort()
                        print(f"Prontos: {prontos}")
                        Nxo = Nx[:]
                        Nxo.sort()
                        print(f"Nxo: {Nxo}")
                        if prontos == Nxo:
                                print("Todos prontos, fim!")
                                pronto = True
        if pronto == False  and ok == True:
                recebidos.append(origem)
                recebidos.sort()
                print(f"Recebidos: {recebidos}")
                Nxo = Nx[:]
                Nxo.sort()
                print(f"Nxo: {Nxo}")
                if recebidos == Nxo:
                        pronto = True
                        envia(f"Pronto", first)
        if not ok:
                ok = True
                first = origem
                print("recebendo")
                print(msg, origem)
                Nxo = Nx[:]
                Nxo.remove(origem)
                Nxo.sort()
                recebidos.append(origem)
                recebidos.sort()
                print(f"Recebidos: {recebidos}")
                for v in Nxo:
                        envia(msg, v)
                Nxo.append(origem)
                print(f"Nxo: {Nxo}")

                if recebidos == Nxo:
                        envia("Pronto", first)
def iniciador(msg):
        global iniciador
        iniciador = True
        global ok
        if not ok:
                ok = True
                print("Iniciador:", idx)
                for v in Nx:
                        envia(msg, v)

def callback(ch, method, properties, body):
    m = body.decode().split(":")
    if len(m) < 2:
        origem = "NULL"
        msg = m[0]
    else:
        origem = m[0]
        msg = m[1]
    if origem.upper() == "NULL":
        iniciador(msg)
    else:
         recebendo(msg, origem)


if len(argv) < 2:
    print(f"USO: {argv[0]} <id> <c1> <c2> ...")
    exit(1)

idx = argv[1]
Nx = argv[2:]

with BlockingConnection() as conexao:
    with conexao.channel() as canal:
        canal.queue_declare(queue=idx, auto_delete=True)
        for v in Nx:
            canal.queue_declare(queue=v, auto_delete=True)
        canal.basic_consume(queue=idx,
                            on_message_callback=callback,

                            auto_ack=True)

        try:
            print(f"{idx}: aguardando mensagens...")
            canal.start_consuming()
        except KeyboardInterrupt:
            canal.stop_consuming()
