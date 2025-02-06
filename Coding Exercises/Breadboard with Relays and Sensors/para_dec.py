
num = input('Qual o # de origem: ')
base = int(input('Qual a base deste #: '))

digitos = '0123456789ABCDEF'

resultado = digitos.find(num[0])*(base**2) +\
            digitos.find(num[1])*(base**1) +\
            digitos.find(num[2])*(base**0)

print(resultado)
