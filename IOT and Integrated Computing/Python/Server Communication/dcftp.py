import socket
import sys
import time
import os

HOST = '127.0.0.1'  # IP do servidor
PORT = 9999  # Porta do servidor


# Coloque a função de Download aqui

def Download(path, conn):
    # AQUI 1: Obtenha o nome do arquivo a partir do path
    file = os.path.basename(path)
    # Abrir o arquivo em modo escrita
    f = open(file, 'wb')
    while True:
        # Receber os dados da conexão em blocos de 1000 bytes
        data = conn.recv(1000)
        print("data: ", len(data))
        if len(data) == 0 or data is None:
            break
        else:
            # Escrever os dados no arquivo
            f.write(data)
    # Quando o servidor encerrar a conexão, fechar o arquivo
    f.close()
    return None


def selPastaLocal():
    # AQUI 2: Lista o diretório corrente
    print('Diretório local corrente: ', os.getcwd())
    # AQUI 3: Lista as pastas do diretório corrente
    print('Conteúdo do diretório local corrente: ', os.listdir())
    # Cria uma pasta para receber os arquivos de download (opcional)
    dir = input('Digite o subdiretorio ou <ENTER> para manter o mesmo: ')
    if len(dir) > 0:
        try:
            # AQUI 4: cria e seta o novo diretório
            os.makedirs(dir)
            os.chdir(dir)
            print('Esqueci o AQUI 4')
        except:
            # AQUI 5: seta o novo diretório
            print('Esqueic o aqui 5')
            os.chdir(dir)


def arquivoRemoto(s):
    # Lista o diretorio no servidor remoto
    s.send(bytes('os.listdir()\n', 'utf-8'))
    time.sleep(2)  # essa espera é para receber a resposta completa sem ter que criarum while
    resposta = s.recv(2048).decode()
    print(resposta)

    # Seleciona um subdiretório
    dir = input('Digite o subdiretorio remoto ou <ENTER> para manter o mesmo: ')

    if len(dir) > 0:
        if dir not in eval(resposta):
            print('Esse diretorio não pode ser selecionado')
        else:
            # AQUI 6: Envia o comando para o servidor listar o diretorio selecionado
            s.send(bytes('os.listdir({})\n'.format(dir), 'utf-8'))
            time.sleep(2)
            resposta = s.recv(2048).decode()
            print(resposta)

    # Selecione o arquivo para download
    file = input('selecione o arquivo para download: ')
    if file not in eval(resposta):
        print('esse arquivo nao existe')
        # selecionar o primeiro arquivo
        file = eval(resposta)[0]

    # AQUI 7: Retorna o caminho para um arquivo da lista
    return os.path.join(dir, file)


# --------------------------------------------------------------------
# Incio do código principal

print('Diretório corrent: ', os.getcwd())
pydir = os.path.dirname(os.path.realpath(__file__))
print('Diretório do script: ', pydir)
os.chdir(pydir)
print('Diretório corrente: ', os.getcwd())

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    s.connect((HOST, PORT))
except:
    print('# erro de conexao')
    sys.exit()

# AQUI 8: Chame selPastaLocal() e imprima o diretório de Download escolhido
selPastaLocal()
print('Diretório corrente: ', os.getcwd())
# AQUI 9: Decomente a chamada de seleçao do arquivo remoto
remoto = arquivoRemoto(s)
print('Arquivo selecionado: ', remoto)

s2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    s2.bind(('', 9998))
    s2.listen(1)
except:
    print('# erro de bind')
    sys.exit()

# Envia ordem de upload aoservidor
# AQUI 10: Envia a ordem de Download para o servidor
s.send(bytes('download({})\n'.format(remoto), 'utf-8'))
# Aguarda a conexão para criaro canal de dados
conn, addr = s2.accept()
print('Servidor {} fez a conexao'.format(addr))

# AQUI 11: Faça a chamada para função de Download
Download(remoto, conn)
conn.close()
s2.close()

print('O arquivo foi transferido')
input('Digite <ENTER> para encerrar')

s.close()