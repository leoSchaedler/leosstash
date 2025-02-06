import random

def modeloAnalitico(N):
    P = (1/365)**N
    for i in range((366-N),366):
        P = P * i
    result = 1 - P
    print("Quantidade de pessoas:", N)
    print("Probabilidade calculada pela fórmula analítica:", result)

def paradoxoDosAniversarios(K, N):
    # N e a quantidade de simulacoes
    # K e o tamanho do grupo
    Eventos = 0
    for n in range(N):
        datas = []
        for k in range(K):
            datas.append(random.randint(1,366))
        if (any(datas.count(i) > 1 for i in datas)): Eventos +=1
    return Eventos/N

# print(f"Me: ", paradoxoDosAniversarios(30, 10000))

def modeloDeSimulação(K, N):
    Eventos = 0
    for n in range(N):
        datas = []
        for k in range(K):
            datas.append(random.randint(1, 366))
        if (any(datas.count(i) > 1 for i in datas)): Eventos += 1
    result = float(Eventos/N)
    print("Quantidade de pessoas:", K)
    print("Quantidade de simulacoes:", N)
    print("Probabilidade calculada por simulação:", result)


print("||-----------------------------------------------------------------||")
print("||              Simulação do problema do aniversário               ||")
print("||-----------------------------------------------------------------||")
print()
K = int(input("Digite a quantidade de pessoas para a qual deve ser calculada a probabilidade: "))
print()
print("||-----------------------------------------------------------------||")
print("||       Cálculo da probabilidade através do modelo analítico      ||")
print("||-----------------------------------------------------------------||")
print()
modeloAnalitico(K)
print()
print("||-----------------------------------------------------------------||")
print("||           Cálculo da probabilidade através de simulação         ||")
print("||-----------------------------------------------------------------||")
print()
N = int(input("Digite a quantidade de vezes que deve ser realizada a simulação: "))
print()
modeloDeSimulação(K, N)