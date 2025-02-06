#!/usr/bin/env python3

# MSG := "origem:msg"
# (se origem == "NULL", inicia)
#
# Formato:
#      entidade.py <id> <v1> <v2> ...
#

from pika import BlockingConnection
from sys import argv
from enum import Enum

Estado = Enum('estado', 'INICIADOR OCIOSO OK VISITADO')


estado = Estado.OCIOSO
iniciador = False
entrada = ""
nao_visitados = []


def envia(msg, dests):
    msg = idx + ":" + msg
    canal.basic_publish(exchange="", routing_key=dests, body=msg)

def recebendo(msg, origem):
    global estado
    global entrada
    global nao_visitados
    if estado == Estado.OCIOSO:
        entrada = origem
        nao_visitados = Nx[:]
        nao_visitados.remove(origem)
        iniciador = False
        visita()

    if estado == Estado.VISITADO:
        if(msg == "T"):
           if(origem in nao_visitados):
              nao_visitados.remove(origem)
              estado = Estado.VISITADO
              envia("B", origem)
        if(msg == "R"):
           visita()
        if(msg == "B"):
           visita()

def espontaneamente(msg):
    global estado
    global iniciador
    global nao_visitados
    nao_visitados = Nx[:]
    iniciador = True
    visita()


def callback(ch, method, properties, body):
    global estado
    m = body.decode().split(":")
    print(m)
    if len(m) < 2:
        origem = "NULL"
        msg = m[0]
    else:
        origem = m[0]
        msg = m[1]
    if origem.upper() == "NULL":
        inciador = True
        espontaneamente(msg)
    else:
        recebendo(msg, origem)



def visita():
        global estado
        global entrada
        global nao_visitados
        if(len(nao_visitados) > 0):
                prox = nao_visitados[0]
                nao_visitados.remove(nao_visitados[0])
                estado = Estado.VISITADO
                envia("T",prox)
        else:
                estado = Estado.OK
                if(not iniciador):
                        envia("R", entrada)

if len(argv) < 2:
    print(f"USO: {argv[0]} <id> <c1> <c2> ...")
    exit(1)

Nx = argv[2:]
idx = argv[1]
estado = Estado.OCIOSO
nao_visitados = Nx[:]

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

