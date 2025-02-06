import random
import pandas as pd

#Função para gerar as combinações
def combinacao_tamanho_n(lista, n):
    #Se o n for 0 vai retornar uma lista de listas nula
    if n == 0:
        return [[]]
    #inicializa uma lista vazia
    l =[]
    #inicia o loop com o tamanho da lista de entrada
    for i in range(0, len(lista)): 
        #m recebe o elemento i da lista
        m = lista[i]
        #resto da lista é colocado numa variavel
        restoLista = lista[i + 1:]
        #loop recursivo da função até n -1
        for p in combinacao_tamanho_n(restoLista, n-1):
            #adicionamos o elemento m a todas as combinações posteriores
            l.append([m]+p)
    #retorna a lista
    return l


# - Programa 1

#Gerando combinação de 5 em 5 de 1 até 50
combinacao5 = [x for x in combinacao_tamanho_n([i for i in range(1, 51)], 5)]
#Gerando combinação de 4 em 4 de 1 até 50
combinacao4 = [x for x in combinacao_tamanho_n([i for i in range(1, 51)], 4)]
#Gerando combinação de 3 em 3 de 1 até 50
combinacao3 = [x for x in combinacao_tamanho_n([i for i in range(1, 51)], 3)]
#Gerando combinação de 2 em 2 de 1 até 50
combinacao2 = [x for x in combinacao_tamanho_n([i for i in range(1, 51)], 2)]

#Imprimindo a quantidade de combinações

print(len(combinacao5))

print(len(combinacao4))

print(len(combinacao3))

print(len(combinacao2))


# # - Programa 2

# def testeCartoes(combinacao5, combinacaoMenor, n):
#     #Inicializamos duas listas vazias
#     lista = []
#     cartoes = []
#     #Loop sobre cada elemento da combinação de 5 em 5
#     for i in combinacao5:
#         #pegamos cada subconjunto de n do elemento de combinação 5
#         combinacaosubconjunto = combinacao_tamanho_n(i, n)
#         #para cada combinação de n em n
#         for j in combinacaosubconjunto:
#             #se a combinação não estiver na lista vamos adicioná-la
#             if j not in lista:
#                 lista.append(j)
#                 if i not in cartoes:
#                     #se o cartão possuir uma combinação nova vamos incluí-lo nas lista de cartões
#                     cartoes.append(i)
#         if len(lista) == len(combinacaoMenor):
#             #se a lista for de mesmo tamanho da combinação menor podemos parar o programa
#             break
#     #por fim imprimimos o numero de cartões
#     resultado = f"Cartões: {len(cartoes)} \n"
#     print(resultado)

# #Se utilizarmos a combinação ordenada, podemos perceber que o número de cartões será muito maior do que se sortearmos os cartões
# #O número mínimo só pode ser obtido por modo de força bruta. Mesmo assim podemos perceber que a ordem aleátoria é muito superior a ordenada
# #De modo heurístico podemos dizer que é um bom número para se usar como mínimo

# print("Combinações de 2: \n")
# print("Com a combinação ordenada: \n")
# testeCartoes(combinacao5, combinacao2, 2)

# ShuffleList = list(combinacao5) 

# random.shuffle(ShuffleList)

# print("Com a combinação aleatória: \n")
# testeCartoes(ShuffleList, combinacao2, 2)

# # - Programa 3

# print("Combinações de 3: \n")
# print("Com a combinação ordenada: \n")
# testeCartoes(combinacao5, combinacao3, 3)

# ShuffleList = list(combinacao5) 

# random.shuffle(ShuffleList)

# print("Com a combinação aleatória: \n")
# testeCartoes(ShuffleList, combinacao3, 3)

# #Programa 4

# print("Combinações de 4: \n")
# print("Com a combinação ordenada: \n")
# testeCartoes(combinacao5, combinacao4, 4)

# ShuffleList = list(combinacao5) 

# random.shuffle(ShuffleList)

# print("Com a combinação aleatória: \n")
# testeCartoes(ShuffleList, combinacao4, 4)

# - Programa 5

# Para calcular o lucro dos cartões de 2 em 2 vamos usar como parâmetro o bilhete premiado
def lucroCartoes2(combinacao5, combinacaoMenor, n, premiado):
    lista = []
    cartoes = []
    for i in combinacao5:
        combinacaosubconjunto = combinacao_tamanho_n(i, n)
        for j in combinacaosubconjunto:
            if j not in lista:
                lista.append(j)
                if i not in cartoes:
                    cartoes.append(i)
        if len(lista) == len(combinacaoMenor):
            break
    #vamos sortear a lista de cartões escolhidos
    random.shuffle(cartoes)
    #Pegando apenas 20% do total
    bilhetes_comprados = slice(0, int((len(cartoes) * 0.2)))
    cartoes = cartoes[bilhetes_comprados]
    #inicializando a variavel de lucro
    lucro = 0
    print(f"Bilhete Premiado: {premiado}\n")
    #loop para cada cartao
    for cartao in cartoes:
        #inicializamos a variável abaixo para comparar nossos bilhetes com o premiado
        check_premiado = premiado
        #vamos pegar agora as possiveis combinações de n de cada cartão
        combinacoes = combinacao_tamanho_n(cartao, n)
        for combinacao in combinacoes:
            #para cada combinação
            if all(x in check_premiado for x in combinacao):
                #se a combinação constar no bilhete premiado
                #vamos imprimir nosso cartão, qual é o bilhete premiado, e qual a combinação usada
                print(f"Cartão: {cartao}\n")
                print(f"Sequência Pontuada: {combinacao}\n")
                #adicionamos o valor da dupla no lucro
                lucro += 4.16
    #calculamos o lucro ao subtrair o custo de todos os cartões comprados
    lucro = lucro - 2 * (len(cartoes))
    #imprimimos o valor do lucro
    print(f"O lucro foi de: {lucro}")


ShuffleList = list(combinacao5)

random.shuffle(ShuffleList)

lucroCartoes2(ShuffleList, combinacao2, 2, random.choice(combinacao5))

# - Programa 6

#para o lucro de 3 em 3, o algoritmo segue a mesma ideia, mas agora com limitações de cartões premiados
def lucroCartoes3(combinacao5, combinacaoMenor, n, premiado):
    lista = []
    cartoes = []
    for i in combinacao5:
        combinacaosubconjunto = combinacao_tamanho_n(i, n)
        for j in combinacaosubconjunto:
            if j not in lista:
                lista.append(j)
                #print(j)
                if i not in cartoes:
                    cartoes.append(i)
                    #print(i)
        if len(lista) == len(combinacaoMenor):
            break
    random.shuffle(cartoes)
    bilhetes_comprados = slice(0, int((len(cartoes) * 0.2)))
    cartoes = cartoes[bilhetes_comprados]
    lucro = 0
    print(f"Bilhete Premiado: {premiado}\n")
    for cartao in cartoes:
        check_premiado = premiado
        combinacoes = combinacao_tamanho_n(cartao, n)
        #agora também adicionamos as combinações de 2 em 2 na lista 
        combinacoes.extend(combinacao_tamanho_n(cartao, n-1))
        for combinacao in combinacoes:
            if all(x in check_premiado for x in combinacao):
                if len(combinacao) == 3:
                    # se a combinação premiada for de tamanho 3 e estiver abaixo do limite, consideramos o lucro e aumentamos o contador
                    lucro_cartao = 11.89
                    print(f"Cartão: {cartao}\n")
                    print(f"Sequência Pontuada: {combinacao}\n")
                    lucro += lucro_cartao
                                
                if len(combinacao) == 2:
                    # se a combinação premiada for de tamanho 2 e estiver abaixo do limite, consideramos o lucro e aumentamos o contador
                    lucro_cartao = 4.16
                    print(f"Cartão: {cartao}\n")
                    print(f"Sequência Pontuada: {combinacao}\n")
                    lucro += lucro_cartao
                    

    lucro = lucro - 2 * (len(cartoes))

    print(f"O lucro foi de: {lucro}")


ShuffleList = list(combinacao5)

random.shuffle(ShuffleList)

lucroCartoes3(ShuffleList, combinacao3, 3, random.choice(combinacao5))


# O lucro de 4 em 4 segue a mesma regra, com a adição de linhas para considerar as quadras, triplas e duplas

# - Programa 7

def lucroCartoes4(combinacao5, combinacaoMenor, n, premiado):
    lista = []
    cartoes = []
    for i in combinacao5:
        combinacaosubconjunto = combinacao_tamanho_n(i, n)
        for j in combinacaosubconjunto:
            if j not in lista:
                lista.append(j)
                #print(j)
                if i not in cartoes:
                    cartoes.append(i)
                    #print(i)
        if len(lista) == len(combinacaoMenor):
            break
    random.shuffle(cartoes)
    bilhetes_comprados = slice(0, int((len(cartoes) * 0.2)))
    cartoes = cartoes[bilhetes_comprados]
    lucro = 0
    print(f"Bilhete Premiado: {premiado}\n")
    for cartao in cartoes:
        check_premiado = premiado
        combinacoes = combinacao_tamanho_n(cartao, n)
        combinacoes.extend(combinacao_tamanho_n(cartao, n-1))
        combinacoes.extend(combinacao_tamanho_n(cartao, n-2))
        for combinacao in combinacoes:
            if all(x in check_premiado for x in combinacao):
                
                if len(combinacao) == 4:
                    lucro_cartao = 82.31
                    print(f"Cartão: {cartao}\n")
                    print(f"Sequência Pontuada: {combinacao}\n")
                    lucro += lucro_cartao
                           
                if len(combinacao) == 3:
                    lucro_cartao = 11.89
                    print(f"Cartão: {cartao}\n")
                    print(f"Sequência Pontuada: {combinacao}\n")
                    lucro += lucro_cartao
                                
                if len(combinacao) == 2:
                    lucro_cartao = 4.16
                    print(f"Cartão: {cartao}\n")
                    print(f"Sequência Pontuada: {combinacao}\n")
                    lucro += lucro_cartao
                    

    lucro = lucro - 2 * (len(cartoes))

    print(f"O lucro foi de: {lucro}")


ShuffleList = list(combinacao5)

random.shuffle(ShuffleList)

lucroCartoes4(ShuffleList, combinacao4, 4, random.choice(combinacao5))

# - Programa 8

# no lucro de 5 em 5, retiramos a parte inicial do algoritmo, já que não é mais necessário calcular subconjuntos
def lucroCartoes5(combinacao5, n, premiado, jackpot):
    #adicionamos também o tamanho do premio para quem acertar a sequencia de 5 números como parametro
    random.shuffle(combinacao5)
    bilhetes_comprados = slice(0, int((len(combinacao5) * 0.2)))
    cartoes = combinacao5[bilhetes_comprados]
    #colocamos o valor investido como variável para depois retorna-lo
    valor_investido = 2 * len(cartoes)
    lucro = 0
    print(f"Bilhete Premiado: {premiado}\n")
    for cartao in cartoes:
        check_premiado = premiado
        combinacoes = combinacao_tamanho_n(cartao, n)
        combinacoes.extend(combinacao_tamanho_n(cartao, n-1))
        combinacoes.extend(combinacao_tamanho_n(cartao, n-2))
        combinacoes.extend(combinacao_tamanho_n(cartao, n-3))
        for combinacao in combinacoes:
            if all(x in check_premiado for x in combinacao):

                if len(combinacao) == 5:
                    lucro_cartao = jackpot
                    print(f"Cartão: {cartao}\n")
                    print(f"Sequência Pontuada: {combinacao}\n")
                    lucro += lucro_cartao
                      
                
                if len(combinacao) == 4:
                    lucro_cartao = 82.31
                    print(f"Cartão: {cartao}\n")
                    print(f"Sequência Pontuada: {combinacao}\n")
                    lucro += lucro_cartao
                           
                if len(combinacao) == 3:
                    lucro_cartao = 11.89
                    print(f"Cartão: {cartao}\n")
                    print(f"Sequência Pontuada: {combinacao}\n")
                    lucro += lucro_cartao
                                
                if len(combinacao) == 2:
                    lucro_cartao = 4.16
                    print(f"Cartão: {cartao}\n")
                    print(f"Sequência Pontuada: {combinacao}\n")
                    lucro += lucro_cartao
                    

    lucro = lucro - valor_investido

    print(f"O lucro foi de: {lucro}")
    print(f"O valor investido foi de {valor_investido}")
    #por fim retornamos o lucro e o valor investido
    return(lucro, valor_investido)


ShuffleList = list(combinacao5)

random.shuffle(ShuffleList)

lucroCartoes5(ShuffleList, 5, random.choice(combinacao5), 182028000)

# - Programa 9

#Lemos o csv de backtest com a biblioteca de dataframes pandas
backtest = pd.read_csv("euro_concursos.csv")

#transformamos as colunas B1 a B5 em uma só coluna de lista para inserir como parametro na nossa função
backtest['serie'] = backtest[['B1', 'B2', 'B3', 'B4', 'B5']].apply(list, axis=1)

#inicializamos uma lista vazia
lista_backtest = []
#fazemos um loop por cada linha do dataframe
for row in range(0, len(backtest)):
    #e rodamos a função de lucro de cartoes de 5 em 5 com a série e premio de cada linha, e adicionamos o resultado numa lista
    lista_backtest.append([row, *lucroCartoes5(ShuffleList, 5, backtest['serie'][row], backtest['Jackpot'][row])])
    print(lista_backtest[row])

#transformamos a lista de resultado num dataframe
resultado = pd.DataFrame(lista_backtest)

#E salvamos ela como CSV
resultado.to_csv('resultado.csv')










