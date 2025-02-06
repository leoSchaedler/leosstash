
def trocar(V, i, j):
    aux= V[i]
    V[i]=V[j]
    V[j]=aux
    ##return V

def ordenar(V):
    N=len(V)
    for j in range(0,N-1,1):
        for i in range(0,N-1, 1):
            if(V[i]>V[i+1]):
                trocar(V,i,i+1)

def ordenar2(V):
    N=len(V)
    for i in range(0,N-1,1):
        for j in range(i+1,N,1):
            if (V[i]>V[j]):
                trocar(V,i,j)

X = [10,70,40,30,20]
print(X)
ordenar2(X)
print(X)

def qwerty (n):
    lst = []
    for i in range (1, n+1, 1):
        x = input('Defina o próximo número da lista: ')
        lst.append(x)
    print ('Esta é a lista' , lst)
    ordenar2(lst)
    print (lst)
    return lst
qwerty(5)


#####



# Testar:

x = [25,12,13]
i = 0
j = 1
trocar(x,i,j)
print (x)



def multiplicar(M,R,NL,NC,1):
    for colna in range(0, ,1):
        acc = 0
        for i in range(0, , 1):
            acc = acc + M[linha][]*M[][coluna]:
            R[linha][coluna] = coluna


Passagem de parâmetros por referência: compartilhando e alterando o mesmo espaço de memória e por valor se faz uma cópia para alteração.