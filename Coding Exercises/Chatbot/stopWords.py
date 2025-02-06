import math

textoExtremamenteGrande = "Espelhos d'água refletem o sol, Meus olhos cegos transformam-se em pó Sal que " \
                                        "marca, não vai parar Corre, fuja, pra se salvar Você ainda está só? O sal da " \
                                        "terra somos nós. Doce sal separou, Nobre som do silêncio chegou Gosto amargo " \
                                        "do mal Me deixa fraco, surreal. Estranhos gestos não podem mudar Sabores vagos " \
                                        "a se conquistar Doce sal separou, Nobre som do silêncio chegou Gosto amargo do " \
                                        "mal Me deixa fraco, surreal. Me deixa fraco Me deixa fraco. Doce sal separou, " \
                                        "Nobre som do silêncio chegou Gosto amargo do mal Me deixa fraco, surreal."

def scannerDeRepetidos(l):
    dic = {}
    Milokov = {}
    for elemento in l:
        if elemento in dic:
            dic[elemento] += 1
        else:
            dic[elemento] = 1
    for key, value in dic.items():
        Milokov[key]=(math.sqrt((10**(-5))/value))
    print(dic, Milokov)

k = textoExtremamenteGrande.lower()
t = k.split()
scannerDeRepetidos(t)