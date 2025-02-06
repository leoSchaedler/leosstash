def criarVet(N): ## Exercício 1
    x=[]
    for i in range(0,N,1):
        x.append(0)
    return x


def preencherVetor(V,N):
    for i in range(0, N, 1):
        y = input('Digite um valor:')
        V[i]=int(y)
    return V

def menor(V,N):
    MENOR = V[0]
    posicao = 0
    for i in range(1,N,1):
        if MENOR > V[i]:
            MENOR = V[i]
            posicao = posicao + 1
    return MENOR

## TESTE

N=3
V=criarVet(N)
V=preencherVetor(V,N)
x=menor(V,N)
print("Menor valor é ", x, "e sua posicao é: ",)

## SUCESSO

## Exercício 2

a=[]
b=[]
c=[]
for i in range(0,2):
    a.append(int(input("Coloque o próximo número do valor a")))
    b.append(int(input("Coloque o próximo número do valor b")))
    c.append(int(a[i]*int(b[i])))

print(c)

## Sucesso


## Exerxício 3

def ex3(V):
    x = []
    y = []
    for i in range(0,2):
        x.append(int(input("Coloque o próximo número do valor x ")))
        y.append(V*int(x[i]))
    print(y)

ex3(2)

## SUCESSO


