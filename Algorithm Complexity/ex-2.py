
# Pesquisa Sequencial 
def ex1(x, V, n):
    i = 0                           # C1 1
    k = -1                          # C2 1
    while (i < n) and (k == -1):    # C3 2
        if x == V[ i ]:             # C4 1
            k = i                   # C5 1
        else:                       # C6
            if V[ i ] < x:          # C7 0
                i = i + 1           # C8 0
            else:                   # C9
                k = -2              # C10 0
    return k                        # C11 1

X = [1,2,3,23,43,45,50,100]
print(ex1(101, X, len(X)))

# Ordenação por Bolha 
def ex2(V, n):
    nt = 0 # número de trocas       #C1 1
    while True:                     #C2 (n - 1)
        nt= 0                       #C3 (n - 1)
        for k in range(0, n-1, 1):  #C4 (n - 1) . (n - 1)
            if (V[k] > V[k+1]):     #C5 (n - 1) . (n - 1)
                aux = V[k]          #C6 somatorio([j=1, n-1]: tj)
                V[k] = V[k+1]       #C7 somatorio([j=1, n-1]: tj)
                V[k+1] = aux        #C8 somatorio([j=1, n-1]: tj)
                nt = nt + 1         #C9 somatorio([j=1, n-1]: tj)
        if nt == 0:                 #C10 (n - 1)
            break                   #C11 1

X = [9,8,7,6,5,4,3]
ex2(X, len(X))
print(X)

# Ordenação por Bolha Modificada 
def ex3(V, n):
    fim = n - 1                     #C1 1
    while True:                     #C2 (n - 1)
        kk = 0;                     #C3 (n - 1)
        for k in range(0, fim, 1):  #C4 somatorio([j=1, n-1]: tj)
            if V[k] > V[k + 1]:     #C5 somatorio([j=1, n-1]: tj)
                aux = V[k]          #C6 somatorio([j=1, n-1]: tj)
                V[k] = V[k + 1]     #C7 somatorio([j=1, n-1]: tj)
                V[k + 1] = aux      #C8 somatorio([j=1, n-1]: tj)
                kk = k              #C9 somatorio([j=1, n-1]: tj)
        fim = kk                    #C10 (n - 1)
        if kk == 0:                 #C11 (n - 1)
            break                   #C12 1

X = [9,8,7,6,5,4,3]

ex3(X, len(X))
print(X)

# Busca do Valor Máximo de um Vetor 
def ex4(V, n):
    max = V[ 0 ]                #C1 1
    for i in range(1, n, 1):    #C2 (n - 1)
        if V[i] > max:          #C3 (n - 1)
            max = V[i]          #C4 (n - 1)
    return max                  #C5 1

X = [9,8,7,6,5,4,3,50]

a = ex4(X, len(X))
print(a)

# Busca do Valor MinMax de um Vetor 
def ex5(V, n):
    min = V[0]                          #C1 1
    max = V[0]                          #C2 1
    for i in range(1, n, 1):            #C3 (n - 1)
        if V[i] < min:                  #C4 (n - 1)
            min = V[i]                  #C5 A < (n - 1)
        if V[i] > max:                  #C6 (n - 1)
            max = V[i]                  #C7 A < (n - 1)
    return [['max', max],['min', min]]  #C8 1


X = [9,8,7,6,5,4,3,50]

a = ex5(X, len(X))
print(a)

# Busca do Valor MinMax melhorado de um Vetor 
def ex6(V, n):
    min = V[0]                          #C1 1
    max = V[0]                          #C2 1
    k = 0
    for i in range(1, n, 1):            #C3 (n - 1)
        if V[i] < min:                  #C4 (n - 1)
            min = V[i]                  #C5 (n - 1)
        elif V[i] > max:                #C6 (n - 1)
            max = V[i]                  #C7 (n - 1)
    return [['max', max],['min', min]]  #C8 1


X = [10,9,8,7,6,5,4,3,2,1]
#X = [1,2,3,4,5,6,7,8,9,10]

a = ex6(X, len(X))
print(a)

# ordenação por inserção
def ex7(V, n):
    for j in range(1, n, 1):            #C1
        chave = V[ j ]                  #C2
        i = j - 1                       #C3
        while i >= 0 and V[i] > chave:  #C4
            V[i + 1] = V[ i ]           #C5
            i = i - 1                   #C6
        V[i + 1] = chave                #C7
    return V

X = [6,5,4,3,2,1]
#X = [1,2,3,4,5,6]
#X = [5,2,4,6,1,3]
print(ex7(X, len(X)))
