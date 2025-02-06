# Questão 1: -------------------------------------------------------------------
print('Questão 1:')
import random
def crivetale(f,q):
    e = []
    for i in range(1,q+1,1):
        n = random.randint(1,f)
        e.append(n)
    return e
def acharmenor(V):
    m = V[0]
    for pos,num in enumerate(V):
        if num < m:
            m = num
            p = pos
    print(f'O menor elemento é {m} e está na posição {p}.')
x = crivetale(20,10)
acharmenor(x)

# Questão 2: -------------------------------------------------------------------
print(' ')
print('Questão 2:')
v1 = crivetale(100,10)
v2 = crivetale(100,10)
def multvet(f,g,n):
    q = 1
    w = []
    for i in range(0,n,1):
        q = f[i]*g[i]
        w.append(q)
    return w
print(multvet(v1,v2,10))


# Questão 3: -------------------------------------------------------------------
print(' ')
print('Questão 3:')
a1 = crivetale(100,20)
def numale(r):
    a = random.randint(1,r)
    return a
av = numale(10)
def prodvet(a1,av):
    w = []
    for i in a1:
        i = i*av
        w.append(i)
    return w
print(prodvet(a1,av))

# Questão 4: -------------------------------------------------------------------
print(' ')
print('Questão 4:')
F = crivetale(100,20)
G = crivetale(100,20)
print(multvet(F,G,20))


# Questão 5: -------------------------------------------------------------------
print(' ')
print('Questão 5:')
S = crivetale(100,20)
print(f'Existem {len(list(filter(lambda x: x%2 == 0, S)))} números pares no Vetor')

# Questão 6: -------------------------------------------------------------------
print(' ')
print('Questão 6:')
z = crivetale(100,20)
print(z)
def trocadevetores(x):
    p = []
    j = 0
    for i in range(19,-1,-1):
        j = x[i]
        p.append(j)
    return p
print(trocadevetores(z))

# Questão 7: -------------------------------------------------------------------
print(" ")
print('Questão 7:')
X = crivetale(100,10)
Y = crivetale(100,10)
def difv(x,y,n):
    z1 = []
    a = 0
    for i in range(0,n,1):
        a = x[i] - y[i]
        z1.append(a)
    return z1
print(f'A diferença entre X e Y gera o Vetor Z = {difv(X,Y,10)}')
def somv(x,y,n):
    z2 = []
    b = 0
    for i in range(0,n,1):
        b = x[i] + y[i]
        z2.append(b)
    return z2
print(f'A soma entre X e Y gera o Vetor Z = {somv(X,Y,10)}')
print(f'O produto entre X e Y gera o Vetor Z = {multvet(X,Y,10)}')

# Questão 8: -------------------------------------------------------------------
print(" ")
print('Questão 8:')
def valrep(N):
    x = []
    for i in range(0, N):
        x.append(crivetale(N,100))
    print(x)
    for i in range(0, N):
        n = 0
        for j in range(0, N):
            if x[i] == x[j]:
                n = n + 1
        print(str(x[i]) + " foi inserido " + str(n) + " vezes.")
valrep(20)


# Questão 9: -------------------------------------------------------------------
print(' ')
print('Questão 9:')
def ordenar(V):
    N = len(V)
    for i in range(0,N,1):
        for j in range(i+1,N,1):
            if(V[i]>V[j]):
                aux = V[i]
                V[i] = V[j]
                V[j] = aux
l = crivetale(1000,500)
def divv(x,y):
    d = []
    for i in range(0,500,1):
        c = x[i]/y[499]
        d.append(c)
    return d
print(divv(l,ordenar(l)))

# Questão 10: -------------------------------------------------------------------
print(' ')
print('Questão 10:')
def numebota(q,m):
    a = len(q)
    b = len(q[0])
    c = len(m[0])
    r = []
    for i in range(a):
        r.append([])
        for j in range(c):
            val = 0
            for k in range(b):
                    val += q[i][k]*m[k][j]
            r[i].append(val)
    m= r[0][1] + r[1][1]
    j = r[1][0] + r[0][0]
    return m,j
q= [[3, 1, 3], [6, 5, 5]]
m= [[100, 50], [50, 100], [50, 50]]
print(f'No mês de maio foram gastos {numebota(q,m)[0]} botões, e nos mês de Junho foram gastos {numebota(q,m)[1]} botoões.')

# Questão 11: -------------------------------------------------------------------
print(' ')
print('Questão 11:')
def criamatr(nl,nc):
    u = []
    for i in range(1,nl+1,1):
        print()
        for j in range(1,nc+1,1):
            aij = 3*i - j
            print(aij, end= ' ')
nl = 2
nc = 2
criamatr(nl,nc)

# Questão 12: -------------------------------------------------------------------
print(' ')
print('Questão 12:')
def crimatale(c,l,r):
    m = []
    for i in range(1,c+1,1):
        e = []
        for i in range(1, l + 1, 1):
            n = random.randint(1, r)
            e.append(n)
        m.append(e)
    return m
v1 = crimatale(4,6,50)
print(v1)
v2 = crimatale(4,6,50)
print(v2)
def sommat(m1,m2,l,c):
    Q = []
    for i in range(0,l,1):
        for j in range(0,c,1):
            a = m1[i][j] + m2[i][j]
            Q.append(a)
    return Q
print(sommat(v1,v2,4,6))
def submat(m1,m2,l,c):
    Q = []
    for i in range(0,l,1):
        for j in range(0,c,1):
            a = m1[i][j] - m2[i][j]
            Q.append(a)
    return Q
print(submat(v1,v2,4,6))

# Questão 13: -------------------------------------------------------------------
print(' ')
print('Questão 13:')
V = crivetale(36,100)
A = numale(10)
def multvet(v,t,A):
    l = []
    a = 0
    for i in range(0,t,1):
        a = v[i] * A
        l.append(a)
    return l
print(f'O Vetor V é {multvet(V,36,A)}')

# Questão 14: -------------------------------------------------------------------
print(' ')
print('Questão 14:')
def crimatale(c,l,r):
    m = []
    for i in range(1,c+1,1):
        e = []
        for i in range(1, l + 1, 1):
            n = random.randint(i, r)
            e.append(n)
        m.append(e)
    return m
M = crimatale(5,5,100)
print(M)
def somlinespl(l,L,t):
    s = 0
    for i in range(0,t,1):
        s += L[i][l-1]
    return s
print('A soma da linha 4 de M é:',somlinespl(4,M,5))
def somlinespc(c,L,t):
    s = 0
    for i in range(0,t,1):
        s += L[c-1][i]
    return s
print('A soma da coluna 2 de M é:',somlinespc(2,M,5))
def somlinespdp(L,t):
    s = 0
    for i in range(0,t,1):
        s += L[i][i]
    return s
print('A soma da diagonal principal de M é:',somlinespdp(M,5))
def somlinespds(L,t):
    s = 0
    for i in range(0,t,1):
        for j in range(i-1,i,1):
            s += L[i][j]
    return s
print('A soma da diagonal secundária de M é:',somlinespds(M,5))
def somlinespt(L,l,c):
    s = 0
    for i in range(0,l,1):
        for j in range(0,c,1):
            s += L[i][j]
    return s
print('A soma de todos os elementos da matriz M é:',somlinespt(M,5,5))

# Questão 15: -------------------------------------------------------------------
print(' ')
print('Questão 15:')
a = crivetale(10,100)
def lervet(n,a):
    v = []
    for i in range(0, n,1):
        v.append(a)
    return v
def gema(l,c):
    r = []
    for i in range(0,l):
        r.append(lervet(c,a))
    return r
def repetidos():
    m = gema(15,5)
    for c in range(len(m)):
        for i in range(len(m[0])):
            n = 0
            for q in range(len(m)):
                for w in range(len(m[0])):
                    if m[c][i] == m[q][w]:
                        n = n + 1
                        print(str(m[c][i]) + " aparece " + str(n) + " vezes")
repetidos()

# Questão 16: -------------------------------------------------------------------
print(' ')
print('Questão 16:')
M = crimatale(13,12,500)
def multpormodlinha(m):
    maior = bigger = 1
    mod = []
    x = []
    for c in m:
        print(c)
    for c in range(len(m)):
        maior = m[c][0]
        for i in range(len(m[0])):
            x.append(m[c][i]/bigger)
            if m[c][i] > maior:
                maior = m[c][i]
        bigger = maior
        mod.append(x)
        x = []
    print('módulo')
    for c in mod:
        print(c)
multpormodlinha(M)


# Questão 17: -------------------------------------------------------------------
print(' ')
print('Questão 17:')
def mudalinha(m):
    l2, l8, c4, c10 = [], [], [], []
    x = []
    t = []
    for c in m:
        print(c)
    for n, c in enumerate(m):
        c4.append(c[3])
        c10.append(c[9])
        for e, i in enumerate(c):
            if n == 1:
                l2.append(i)
            if n == 7:
                l8.append(i)
    for c in range(0, len(m)):
        m[c][3] = c10[c]
        m[c][9] = c4[c]
    m[1] = l8
    m[7] = l2
    for c in m:
        print(c)
a = crimatale(10,10,200)
mudalinha(a)

# Questão 18: -------------------------------------------------------------------
print(' ')
print('Questão 18:')
m = crimatale(10,10,1000)
q = 1
def trocar(m,a):
    dp = []
    ds = []
    c10 = []
    l4 = []
    for n, c in enumerate(m):
        c10.append(c[9])
        for e, i in enumerate(c):
            if n == 3:
                l4.append(i)
            if n == e:
                dp.append(i)
            if e == len(m[0]) - a:
                a += 1
                ds.append(i)
    for c in m:
        print(c)
    a = 1
    for n, c in enumerate(m):
        m[n][9] = l4[n]
        for e, i in enumerate(c):
            if n == e:
                m[n][e] = ds[n]
            if e == len(m[0]) - a:
                a += 1
                m[n][e] = dp[n]
    m[3] = c10
    print(50 * '-')
    for c in m:
        print(c)
trocar(m,q)

# Questão 19: -------------------------------------------------------------------
print(' ')
print('Questão 19:')
def trocarlinhas(m):
    for c in m:
        print(c)
    c1, c2, c3, c4, c5, c6 = [], [], [], [], [], []
    for i in range(0, 2):
        for n, c in enumerate(m):
            if i == 0:
                c1.append(c[0])
                c2.append(c[1])
                c3.append(c[2])
                c4.append(c[3])
                c5.append(c[4])
                c6.append(c[5])
            elif i == 1:
                m[n][1] = c1[n]
                m[n][0] = c2[n]
                m[n][3] = c3[n]
                m[n][2] = c4[n]
                m[n][5] = c5[n]
                m[n][4] = c6[n]
    print(25 * '-')
    for c in m:
        print(c)
b = crimatale(6,6,100)
trocarlinhas(b)

# Questão 20: -------------------------------------------------------------------
print(' ')
print('Questão 20:')
M = crimatale(11,11,500)
def diapri(m,l,c):
    z = []
    med = 0
    for i in range(0,l,1):
        for j in range(0,c,1):
            a = m[l][c]
            z.append(a)
    for k in range(0,l,1):
        med += z[k]
    return med/l
print(diapri(M,10,10))

# Questão 21: -------------------------------------------------------------------
print(' ')
print('Questão 21:')
def muldp(m):
    maior = m[0][0]
    for w in range(2):
        for n, c in enumerate(m):
            for e, i in enumerate(c):
                if i > maior and w == 0:
                    maior = i
                elif n == e and w == 1:
                    m[n][e] = i * maior
    for c in m:
        print(c)
B = crimatale(50, 50, 500)
muldp(B)

# Questão 22: -------------------------------------------------------------------
print(' ')
print('Questão 22:')
def minimax(m):
    maior = m[0][0]
    pos = [0, 0]
    minamax = 0
    for c in m:
        print(c)
    for w in range(0, 2):
        for n, c in enumerate(m):
            for e, i in enumerate(c):
                if i > maior and w == 0:
                    maior = i
                    pos = [n, e]
                    minamax = m[n][e]
                if i < minamax and n == pos[0] and w == 1:
                    minamax = i
                    posx = [n + 1, e + 1]
    print(maior, ' ', pos)
    print(minamax, ' ', posx)
C = crimatale(10,10,200)
minimax(C)

# Questão 23: -------------------------------------------------------------------
print(' ')
print('Questão 23:')
def gerMat1(l,c):
    z,x,y = [], [], []
    for i in range(1,l+1,1):
        for j in range(1,c+1,1):
            if i == 1:
                z.append(1)
            elif j == 1:
                z.append(1)
            elif i == 6:
                z.append(1)
            elif j == 1:
                z.append(1)
            elif i == 2 and i>1 and i<6:
                z.append(2)
            elif i==3 and j==2:
                z.append(2)
            elif i==4 and j==2:
                z.append(2)
            elif i==5 and i>1 and i<6:
                z.append(2)
            elif i==3 and j==5:
                z.append(2)
            elif i==4 and j==5:
                z.append(2)
            elif i==3 and j==3:
                z.append(3)
            elif i==4 and j==4:
                z.append(3)
            elif i==3 and j==4:
                z.append(3)
            else:
                z.append(3)
        y.append(z)
        z = []
    for C in y:
        print(C)
print(gerMat1(6,6))

# Questão 24: -------------------------------------------------------------------
print(' ')
print('Questão 24:')
def gerMat2(l,c):
    x, y = [], []
    for i in range(0, l, 1):
        for j in range(0, c, 1):
            if i == j:
                x.append(1)
            elif i+j == 5:
                x.append(2)
            else:
                x.append(3)
        y.append(x)
        x = []
    for C in y:
        print(C)
gerMat2(6,6)

# Questão 25: -------------------------------------------------------------------
print(' ')
print('Questão 25:')

    # a) --------------------
print('a)')
def hachurada1(m,l,c):
    z = []
    som = 0
    for i in range(0,l,1):
        for j in range(0,c,1):
            a = m[i][j]
            if i==0 and j>=10:
                z.append(a)
            elif i==1 and j>=9:
                z.append(a)
            elif i==2 and j>=8:
                z.append(a)
            elif i==3 and j>=7:
                z.append(a)
            elif i==4 and j>=6:
                z.append(a)
            elif i==5 and j>=5:
                z.append(a)
            elif i==6 and j>=4:
                z.append(a)
            elif i==7 and j>=3:
                z.append(a)
            elif i==8 and j>=2:
                z.append(a)
            elif i==9 and j>=1:
                z.append(a)
            elif i==10 and j==0:
                z.append(a)
    for k in range(0,len(z),1):
        som = som + z[k]
    return som
a = crimatale(12,12,400)
print(hachurada1(a,12,12))

    #b) --------------------
print('b)')
def hachurada2(m,l,c):
    z = []
    som = 0
    for i in range(0,l,1):
        for j in range(0,c,1):
            a = m[i][j]
            if i==0 and j==0 or j == 11:
                z.append(a)
            elif i==1 and j>=1 or j>=10:
                z.append(a)
            elif i==2 and j>=2 or j>=9:
                z.append(a)
            elif i==3 and j>=3 or j>=8:
                z.append(a)
            elif i==4 and j>=4 or j>=7:
                z.append(a)
            elif i==5 and j>=5 or j>=6:
                z.append(a)
            elif i==6 and j>=5 or j>=6:
                z.append(a)
            elif i==7 and j>=4 or j>=7:
                z.append(a)
            elif i==8 and j>=3 or j>=8:
                z.append(a)
            elif i==9 and j>=2 or j>=9:
                z.append(a)
            elif i==10 and j>=1 or j>=10:
                z.append(a)
            elif i==11 and j==0 or j==11:
                z.append(a)
    for k in range(0,len(z),1):
        som = som + z[k]
    return som
a = crimatale(12,12,400)
b = hachurada2(a,12,12)
print(b)

# Questão 26: -------------------------------------------------------------------
print(' ')
print('Questão 26:')

    # a) --------------------
print('a)')
def hachurada3(m,l,c):
    z = []
    for i in range(0,l,1):
        for j in range(0,c,1):
            a = m[i][j]
            if i==0 and j==11:
                z.append(a)
            elif i==1 and j>=10:
                z.append(a)
            elif i==2 and j>=9:
                z.append(a)
            elif i==3 and j>=8:
                z.append(a)
            elif i==4 and j>=7:
                z.append(a)
            elif i==5 and j>=6:
                z.append(a)
            elif i==6 and j>=5:
                z.append(a)
            elif i==7 and j>=4:
                z.append(a)
            elif i==8 and j>=3:
                z.append(a)
            elif i==9 and j>=2:
                z.append(a)
            elif i==10 and j>=1:
                z.append(a)
            elif i==11 and j>=0:
                z.append(a)
    return z
a = crimatale(12,12,400)
b = hachurada3(a,12,12)
def acharnamatr(m):
    z = [ordenar(m)[0]]
    for i in range(0,len(m),1):
        if m[i] == ordenar(m)[0]:
            z.append(i)
    return z
print(acharnamatr(b))
def mediavet(m):
    med = 0
    for i in range(0,len(m),1):
        med = med + m[i]
    return med
print('A média dos elementos da área hachurada é: ',mediavet(b))

# b) --------------------
print('b)')
def hachurada4(m, l, c):
    z = []
    for i in range(0, l, 1):
        for j in range(0, c, 1):
            a = m[i][j]
            if i == 0 and j >= 1 and j <= 10:
                z.append(a)
            if i == 1 and j >= 2 and j <= 9:
                z.append(a)
            if i == 2 and j >= 3 and j <= 8:
                z.append(a)
            if i == 3 and j >= 4 and j <= 7:
                z.append(a)
            if i == 4 and j >= 5 and j <= 6:
                z.append(a)
            if i == 7 and j >= 5 and j <= 6:
                z.append(a)
            if i == 8 and j >= 4 and j <= 7:
                z.append(a)
            if i == 9 and j >= 3 and j <= 8:
                z.append(a)
            if i == 10 and j >= 2 and j <= 9:
                z.append(a)
            if i == 11 and j >= 1 and j <= 10:
                z.append(a)
    return z
A = crimatale(12, 12, 400)
B = hachurada4(A, 12, 12)
print(acharnamatr(B))
print('A média dos elementos da área hachurada é: ', mediavet(B))
