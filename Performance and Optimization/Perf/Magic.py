import numpy as np
def magic(N):
# Cria um quadrado magico  N x N magic square
# N deve ser impar
# https://scipython.com/book/chapter-6-numpy/examples/creating-a-magic-square/

    quadrado_magico = np.zeros((N,N), dtype=int)

    if N%2==0:
        return quadrado_magico


    n = 1
    i, j = 0, N//2  # Iniciar no meio da linha superior

    while n <= N**2:     # Quando n = N ao quadrado terminou
        quadrado_magico[i, j] = n   # Salvar n na posição i,j
        n += 1
        # Mover uma posição para cima e uma para direita
        # Se ultrapassar o limite à direita ir para primeira coluna
        # Se ultrapassar o limite acima ir para última linha
        newi, newj = (i-1) % N, (j+1)% N
        if quadrado_magico[newi, newj]: # se a posição já estiver preenchida
            i += 1                      # ir uma coluna para direita
        else:
            i, j = newi, newj

    return quadrado_magico



