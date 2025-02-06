def convdec():
    num = input("Digite o número que será convertido: ")
    base = int(input("Digite a base numérica do número inserido: "))
    digitos = "0123456789ABCDEF"
    
    dig_esquerda = digitos.find(num[0])
    dig_meio = digitos.find(num[1])
    dig_direita = digitos.find(num[2])
    
    if len(num) != 3:
        print ("Valor inválido! Você deve inserir um valor com 3 digitos")
        
    else:
        resultado = dig_esquerda*(base**2) + dig_meio*(base**1) + dig_direita*(base**0)
        
    print(resultado)


def cconverdec():
    num = input("Digite o número que será convertido: ")
    base = int(input("Digite a base numérica do número inserido"))
    
    digitos = "0123456789ABCDEF"
    
    resultado = digitos.find(num[0])*(base**2) +\
                digitos.find(num[1])*(base**1) +\
                digitos.find(num[2])*(base**0)
    print(resultado)
    
def dectohexa():
    num = input("Digite o número decimal que será convertido para hexadecimal: ")
    
    