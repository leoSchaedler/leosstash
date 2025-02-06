from quick_sort_recursivo import quick_sort_recursivo_wapper
from quick_sort_random import quick_sort_recursivo_random_wapper
from merge_sort_interativo import Merge_Sort_interativo_wapper
from merge_sort_recursivo import merge_sort__recursivo_wapper
from merge_sort_recursivo_random import merge_sort_recursivo_random_wapper
from select_sort_recursivo import select_sort_recursivo_wapper
from select_sort_recursivo_random import select_sort_recursivo_random_wapper
from sellSort_base_line import shellSort_Wapper
from gerador import gerar_dados_crescente
from gerador import gerar_dados_random
from gerador import gerar_dados_decrescente
from gerador import agora
from gerador import dif_time
from bubble_sort_iterativo import bubbleSort_iterativo
from bubble_sort_recursivo import bubbleSort_recursivo
import sys

sys.setrecursionlimit(10000)

X = [58, 30, 97, 21, 81, 35, 48, 59, 24, 2, -1]
print(f'X : {X}')

QS1 = quick_sort_recursivo_wapper(X.copy())
QS2 = quick_sort_recursivo_random_wapper(X.copy())
print(f'Quick sort recursivo: {QS1}')
print(f'Quick sort recursivo randomizado: {QS2}')

MS1 = Merge_Sort_interativo_wapper(X.copy())
MS2 = merge_sort__recursivo_wapper(X.copy())
MS3 = merge_sort_recursivo_random_wapper(X.copy())
print(f'Merge sort interativo: {MS1}')
print(f'Merge sort recursivo: {MS2}')
print(f'Merge sort recursivo randomizado: {MS3}')

SS1 = select_sort_recursivo_wapper(X.copy())
SS2 = select_sort_recursivo_random_wapper(X.copy())
print(f'Select sort recursivo: {SS1}')
print(f'Select sort recursivo randomizado: {SS2}')

BASE_LINE = shellSort_Wapper(X.copy())
print(f'Sell Sort [Baseline]: {BASE_LINE}')

BUBBLE1 = bubbleSort_iterativo(X.copy())
BUBBLE2 = bubbleSort_recursivo(X.copy())
print(f'Bubble Sort Iterativo: {BUBBLE1}')
print(f'Bubble Sort Recursivo: {BUBBLE2}')

def execucao(X):
    D = []

    a = agora()
    QS1 = quick_sort_recursivo_wapper(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    a = agora()
    QS2 = quick_sort_recursivo_random_wapper(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    a = agora()
    MS1 = Merge_Sort_interativo_wapper(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    a = agora()
    MS2 = merge_sort__recursivo_wapper(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    a = agora()
    MS3 = merge_sort_recursivo_random_wapper(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    a = agora()
    SS1 = select_sort_recursivo_wapper(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    a = agora()
    SS2 = select_sort_recursivo_random_wapper(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    a = agora()
    BASE_LINE = shellSort_Wapper(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    a = agora()
    BUBBLE1 = bubbleSort_iterativo(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    a = agora()
    BUBBLE2 = bubbleSort_recursivo(X.copy())
    b = agora()
    D.append(dif_time(b,a))

    return D

T = 100
#N = 10
Decrescente = []
Crescente = []
Random = []

Listas = [Decrescente, Crescente, Random]

for N in range(1, 2001, 10):
    print(f'N = {N}')
            
    X = gerar_dados_decrescente( N )
    Y = gerar_dados_crescente(N)
    Z =   gerar_dados_random(N)
    Decrescente.append( execucao(X) )
    Crescente.append( execucao(Y) )
    Random.append(execucao(Z))

print('QS1,QS2,MS1,MS2,MS3,SS1,SS2,BASE_LINE,BUBBLE1,BUBBLE2')
for L in Listas:
    print("Método:\n")
    for x in L:
        c = len(x) - 1
        i = 0
        for y in x:
            if (i < c):
                print(y, end=',')
            else:
                print(y, end='')
            i +=1
        print()

