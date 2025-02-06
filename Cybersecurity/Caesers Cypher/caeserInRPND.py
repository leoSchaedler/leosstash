
alfabeto = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A',
            'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']

def cifraDeCesarInRPND():
    print('Os caracteres aceitos são os pertencentes ao alfabeto de A até Z, maiúsculos ou minúsculos.')
    textoParaConversao = input("Qual seria o texto que você gostaria de criptografar? ").upper()
    chaveDeInicio = input("Qual seria a chave de início? ")
    textoParaConversaoLista = list(textoParaConversao)
    chaveDeInicioInt = (ord(chaveDeInicio) - 97)
    resultadoDaCifra = []

    for i in range(0, len(textoParaConversaoLista)):
        if textoParaConversaoLista[i] != ' ' and textoParaConversaoLista[i] != '[' and \
                textoParaConversaoLista[i] != ']' and textoParaConversaoLista[i] != "'" and \
                textoParaConversaoLista[i] != ',' and textoParaConversaoLista[i] != '-' and \
                textoParaConversaoLista[i] != '.' and textoParaConversaoLista[i] != ';' and \
                textoParaConversaoLista[i] != '0' and textoParaConversaoLista[i] != '1' and \
                textoParaConversaoLista[i] != '2' and textoParaConversaoLista[i] != '3' and \
                textoParaConversaoLista[i] != '4' and textoParaConversaoLista[i] != '5' and \
                textoParaConversaoLista[i] != '6' and textoParaConversaoLista[i] != '7' and \
                textoParaConversaoLista[i] != '8' and textoParaConversaoLista[i] != '9':
            resultadoDaCifra.append(alfabeto[alfabeto.index((textoParaConversaoLista[i])) + chaveDeInicioInt])
        else:
            resultadoDaCifra.append(textoParaConversaoLista[i])

    print('O texto em português foi criptografado para: ')
    print(''.join(resultadoDaCifra))

def cifraDeCesarOutRPND():
    print('Os caracteres aceitos são os pertencentes ao alfabeto de A até Z, maiúsculos ou minúsculos.')
    textoParaConversao = input("Qual seria o texto que você gostaria de descriptografar? ")
    chaveDeInicio = input("Qual seria a chave de início? ")
    textoParaConversaoLista = list(textoParaConversao)
    chaveDeInicioInt = (ord(chaveDeInicio) - 97)
    resultadoDaCifra = []

    for i in range(0, len(textoParaConversaoLista)):
        if textoParaConversaoLista[i] != ' ' and textoParaConversaoLista[i] != '[' and \
        textoParaConversaoLista[i] != ']' and textoParaConversaoLista[i] != "'" and \
        textoParaConversaoLista[i] != ',' and textoParaConversaoLista[i] != '-' and \
        textoParaConversaoLista[i] != '.' and textoParaConversaoLista[i] != ';' and \
        textoParaConversaoLista[i] != '0' and textoParaConversaoLista[i] != '1' and \
        textoParaConversaoLista[i] != '2' and textoParaConversaoLista[i] != '3' and \
        textoParaConversaoLista[i] != '4' and textoParaConversaoLista[i] != '5' and \
        textoParaConversaoLista[i] != '6' and textoParaConversaoLista[i] != '7' and \
        textoParaConversaoLista[i] != '8' and textoParaConversaoLista[i] != '9':
            resultadoDaCifra.append(alfabeto[alfabeto.index((textoParaConversaoLista[i])) - chaveDeInicioInt])
        else:
            resultadoDaCifra.append(textoParaConversaoLista[i])

    print('O texto criptografado foi convertido para Português: ')
    print(''.join(resultadoDaCifra))

cifraDeCesarInRPND()
cifraDeCesarOutRPND()

# Extra: como a Análise combinatória é utilizada/aplicada nessa criptografia?

# Quando pensamos na quantidade de diferentes resultados que podemos gerar com este algoritmo, com ajuda da análise
# combinatória, fica claro porque a criptografia existe, somente mudando a chave (dentre 26 opções) se pode obter resul-
# tados radicalmente diferentes e a partir de um resultado de criptografia em uma chave "d" por exemplo, se pode cripto-
# grafar novamente com outra chave ou a mesma e o resultado será outro.
#
