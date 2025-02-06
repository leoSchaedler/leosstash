import socket
import sys
import time
import os

HOST = '127.0.0.1'          # IP do servidor
PORT = 9999                 #Porta do servidor
diretorio = 'TOPSECRET'

def Upload(file, conn):
    f = open(file, 'r')
    for line in f:
        conn.send(line.encode())
    f.close()
# Incio do código principal

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    s.connect((HOST, PORT))
except:
    print('# erro de conexao')
    sys.exit()

# Verifica se o diretório já foi criado (resposta deve conter a lista de diretórios)

# PASSO1: Substitua COMANDO pelo comando de listar diretórios
s.send(bytes('os.listdir()\n', 'utf-8'))
time.sleep(2) #essa espera é para receber a resposta completa sem ter que criarum while
# talvez você deva usar eval() ...
resposta = s.recv(2048).decode()
resposta = eval(resposta)

# Se não estiver, cria o diretório
if diretorio not in resposta:
    print('Diretorio nao encontrado')

# PASSO2: Substitua COMANDO pelo comando de criar diretório
    s.send(bytes('os.makedirs({})\n'.format(diretorio),'utf-8'))
    time.sleep(2)
    print(s.recv(2048).decode())
else:
    print('Conteudo do diretorio:')
    # INCLUA O CÓDIGO PARA MOSTRAR OS ARQUIVOS NA PASTA
    s.send(bytes('os.listdir({})\n'.format(diretorio), 'utf-8'))
    time.sleep(2)
    resposta = s.recv(2048).decode()
    print(resposta)

# PASSO3: Inclua o código para verificar se o arquivo existe
arquivo = input('Digite o nome do arquivo para transferir: ')
if not arquivo:
    print('<ENTER> encerra o programa')
    sys.exit()
else:
    if os.path.isfile(arquivo) == False:
        print('o arquivo não existe')
        sys.exit()

s2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    s2.bind(('', 9998))
    s2.listen(1)
except:
    print('# erro de bind')
    sys.exit()

# Envia ordem de upload aoservidor
s.send(bytes('upload({}\\{})\n'.format(diretorio,arquivo), 'utf-8'))

# Aguarda a conexão para criaro canal de dados
conn, addr = s2.accept()
print('Servidor {} fez a conexao'.format(addr))

# Chama a função que transfereo arquivo pelo canal de dados
Upload(arquivo, conn)
conn.close()
s2.close()

print('O arquivo foi transferido')
input('Digite <ENTER> para encerrar')