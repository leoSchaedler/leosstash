
T1 = [4,5,6,6,6,7,7,7,8,8,9,9,9,9,9,10,10,10,10,11,12,12,12,12,12,13,13,13,13,14,14,15,15,15,15,15,16,17,18,19,19,19,20,20,21,22,23,24,25,26]

T2 = [[4,1,4],[5,1,5],[6,3,18],[9,5,45],[10,4,40],[11,1,11],[12,5,60],[13,4,52],[14,2,28],[15,5,75],[16,1,16],[17,1,17],[18,1,18],[19,3,57],[20,2,40],[21,1,21],[22,1,22],[23,1,23],[24,1,24],[25,1,25],[26,1,26]]

# Questão 1:
print('QUESTÃO 1:')
    # Letra a:

def Mv(v):
    M = v[0]
    for i in v:
        if i >= M:
            M = i
    return M
print(f'LETRA A: O maior valor é igual a {Mv(T1)}')

    # Letra b:

def mv(v):
    m = v[0]
    for i in v:
        if i <= m:
            m = i
    return m
print(f'LETRA B: O menor valor é igual a {mv(T1)}')

    # Letra c:

print(f'LETRA C: A amplitude desses dados é igual a {Mv(T1)-mv(T1)}')

    # Letra d:

def compara(v):
    f = []
    q = []
    for i in v:
        z = [i]
        n = 0
        for j in v:
            if i == j:
                n = n + 1
                z.append(n)
        f.append(z)
    for j in range(0, len(f)):
        w = []
        a = f[j][len(f[j]) - 1]
        b = f[j][0]
        w.append(b)
        w.append(a)
        q.append(w)
    return q
def repetido(v):
    z = [v[0]]
    for i in range(1,len(v),1):
        if v[i] != v[i-1]:
            z.append(v[i])
    return z
def ld(v):
    for j in range(0,len(v),1):
        print(f'LETRA D: {v[j][0]} aparece {v[j][1]} vezes.')
ld(repetido(compara(T1)))

    # Letra e:

def oloco(v):
    z = []
    for i in range(0,len(v)):
        a = v[i][1]
        if 1 == a:
            z.append(v[i][0])
    print(f'LETRA E:{z}')
oloco(compara(T1))

# Questão 2:

def fr(v):
    z = []
    for i in range(0,len(v)):
        a = v[i][len(v[i])-1]
        b = a/50
        z.append(b)
    print(f'QUESTÃO 2: {z}')
fr(repetido(compara(T1)))

# Questão 3:

def somar(lista):
    soma = 0.0
    for v in lista:
        soma = soma + v
    return soma
def calcularMedia(L):
    return somar(L)/len(L)

print(f'QUESTÃO 3: {calcularMedia(T1)}')

# Questão 4:

def medf2(v):
    z = []
    for i in range(0,len(repetido(v)),1):
            a = v[i][1]
            z.append(a)
    b = somar(z)
    c = b/len(v)
    print(f'QUESTÃO 4: {c}')
medf2(repetido(compara(T1)))

# Questão 5:

def mediana(v):
    if len(v)%2 == 0:
        a = int((len(v) / 2) - 1)
        b = int(len(v) / 2)
        return (v[a]+v[b])/2
    else:
        return v[len(v)/2]
print(f'QUESTÃO 5: {mediana(T1)}')

# Questão 6:

def am(v):
    q = []
    z = v[0][1]
    for i in range(0,len(v),1):
        if v[i][1] > z:
            z = v[i][1]
    for j in range(0, len(v), 1):
        if z == v[j][1]:
           q.append(v[j][0])
    if len(q) == 0:
        print('QUESTÃO 6: Amodal')
    elif len(q) == 1:
        print(f'QUESTÃO 6: Moda: Unimodal Homogênea {q}')
    elif len(q) == 2:
        print(f'QUESTÃO 6: Moda: Bimodal Heterogênea {q}')
    elif len(q) >= 3:
        print(f'QUESTÃO 6: Moda:  moda{len(q)} Heterogênea {q}')
am(repetido(compara(T1)))

# Questão 7:

def variancia(v,m):
    z = []
    s = 0
    for i in range(0,len(v),1):
        a = (i-m)**2
        z.append(a)
    for j in range(0,len(z),1):
        s = s + z[j]
    return s / len(v)-1

print(f'QUESTÃO 7: {variancia(T1,calcularMedia(T1))}')

# Questão 8:

print(f'QUESTÃO 8: {variancia(T1,calcularMedia(T1))**1/2}')