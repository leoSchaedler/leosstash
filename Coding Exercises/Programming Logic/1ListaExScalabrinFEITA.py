# Hello World

# Primeira Lista de Exercícios de 2019, 1º Semestre, Prof. Scalabrin. Turma 1U - Primeiro Período Manhã.

# Temas:

# - Variáveis, Constantes, Atribuição,
# - Operadores Aritméticos,
# - Entrada e Saída de Dados,
# - Funções e Parâmetros,
# - Escopo de Variáveis

# Exercício 1: Defina o que é um algoritmo.
# Resposta: É uma sequuência de passos ordenados que visam atingir um objetivo bem-definido.

# Exercício 2: Diferencie variáveis de constantes.
# Resposta: Constantes são elementos que tem um valor pré-definido e imutável,
# já variáveis são elementos nos quais seus valores podem mudar em qualquer determinado momento.

# Exercício 3: Quais são os tipos de variáveis/constantes existentes no Python para representar valores numéricos?
# Quais são as diferenças entre eles?
# Resposta: Existem três tipos de variáveis/constantes no Python para representar valores numéricos,
# são elas: int, float e complex.
# int corresponde aos números inteiros, float aos números reais e complex aos números complexos
# , por exemplo, os imaginários.

# Exercício 4: Faça um programa que receba 3 valores que representam os lados de um triângulo, e retorne a sua área.

# Exercício 5: Faça um algoritmo que receba o raio de uma circunferência, e que retorne seu perímetro, diâmetro e área.

# Exercício 6: Faça um algoritmo que receba o raio de uma esfera, e que retorne seu volume e área da sua superfície.

# Exercício 7: Faça um algoritmo que receba as coordenadas de 2 pontos P1 = (x1,y1) e P2 = (x2,y2),
# e retorne a distância euclidiana entre eles.

# Exercício 8. Escreva um programa que leia uma temperatura em graus centígrados
# e apresente a temperatura convertida em graus Fahrenheit.

# Exercício 9. Dado o número da placa de um veículo composto por quatro algarismos,
# escreva um programa que leia este número e o retorne expresso em base binária.

# Exercício 10. Assumindo o código Python abaixo, qual é o resultado a ser apresentado na tela?

# >>> def f(x):
# ... return x + 1
# >>> def g(x):
# ... return x - 1
# >>> x = 3
# >>> resultado = f(g(f(g(x))))
# >>> print(resultado)

# Exercício 11. Dado o programa em Python abaixo, qual é o resultado a ser apresentado na tela?

# >>> ano = 2019
# >>> n1, ano = ano % 2, ano // 2
# >>> n2, ano = ano % 2, ano // 2
# >>> n3, ano = ano % 2, ano // 2
# >>> n4, ano = ano % 2, ano // 2
# >>> n5, ano = ano % 2, ano // 2
# >>> n6, ano = ano % 2, ano // 2
# >>> n7, ano = ano % 2, ano // 2
# >>> n8, ano = ano % 2, ano // 2
# >>> n9, ano = ano % 2, ano // 2
# >>> n10, ano = ano % 2, ano // 2
# >>> u = [ano,n10,n9,n8,n7,n6,n5,n4,n3,n2,n1]
# >>> v = 0
# >>> for i in range(0,len(u),1):
# ... v = v + u[(len(u) - 1) - i] * (2 ** i)
# >>> print(v, u)

# Exercício 12. Em  matemática, o binômio  de  Newton  permite  escrever  na  forma canônica
# o polinómio correspondente à potência de um binômio.
# - Fazer um programa que gere, imprima e cálculo o binômio de Newton.

# Exercício 13. Escrever uma função que calcula área de um retângulo.

# Exercíco 14.Exercício 12.Dada  à  equação  abaixo  faça  um  programa  que
# calcule  área  aproximada com a precisão de 0,0001 e desenhe o gráfico correspondente para x [1, 9].

# Fim da explicação, começo da resolução

import math

# Exercíco 4.

print("Informe o valor dos 3 lados do triângulo o qual deseja calcular a área:")

a = input("Lado A:")
a = int(a)
b = input("Lado B:")
b = int(b)
c = input("Lado C:")
c = int(c)

if a == b == c:
    areaTriangEQ = math.sqrt(3) / (4) * a ** 2
    print("A área de seu triângulo equilátero de lado", a, "é igual ", areaTriangEQ)

if a != b and c:
    pa = (a + b + c) / 2
    areaTriang = math.sqrt(pa * (pa - a) * (pa - b) * (pa - c))
    print("A área de seu triângulo de lados", a, b, c, "é igual ", areaTriang)

else:
     print("Os valores informado não constituem os de um triângulo.")


# SUCESSO

# Exercício 5.

print ("Informe o raio de uma circunferência:")

r = input ("Raio:")
r = float(r)

circPeri = 2 * math.pi * r
circDia = r * 2
circArea = math.pi * r ** 2

print ("Com base numa circunferência de raio ", r , " seu perímetro é ", circPeri , "seu diâmetro é", circDia , "e sua área corresponde a", circArea, ".")

# SUCESSO

# Exercício 6.

print ("Informe o raio de uma esfera:")

r2 = input ("Raio:")
r2 = float(r2)

esfVol = 4 / 3 * math.pi * r2 ** 3
esfSup = 4 * math.pi * r2 ** 2

print ("Em uma esfera de raio", r2, "temos um volume de" , esfVol, "e uma área superficial de", esfSup, ".")

# SUCESSO

# Exercício 7.

print ("Informe as coordenads de dois pontos para cálculo da distância entre eles:")
print ("P1:")
x1= input ("x1=")
x1= float (x1)
x2= input ("x2=")
x2= float (x2)
print ("P2:")
y1= input ("y1=")
y1= float (y1)
y2= input ("y2=")
y2= float (y2)

distEntre2Pontos = math.sqrt ((x2 - x1) ** 2 + (y2 - y1) ** 2)

print ("A distância entre os pontos dados é de: ", distEntre2Pontos)

# SUCESSO

# Exercício 8.

print ("Dê a temperatura em Celsius para conversão em Farenheit:")

cel = input ("Celsius:")
cel = float (cel)

conversao =  (9/5) * cel + 32

print ("A temperatura ", cel, "em Celsius, é convertida pra Farenheit no valor de:", conversao)

# SUCESSO

# Exercício 9.

print("Enter 'x' for exit.");
hexdec = input("Informe os 4 últimos números de uma placa de um carro: ");
if hexdec == 'x':
    exit();
else:
    dec = int(hexdec, 16);
    print(hexdec,"em Binário =",bin(dec));

# SUCESSO

# Exercício 10.

def f(x):
 return x + 1
def g(x):
 return x - 1
x = 3
resultado = f(g(f(g(x))))
print(resultado)

# Resposta: 3

# SUCESSO

#Exercício 11

ano = 2019
n1, ano = ano % 2, ano // 2
n2, ano = ano % 2, ano // 2
n3, ano = ano % 2, ano // 2
n4, ano = ano % 2, ano // 2
n5, ano = ano % 2, ano // 2
n6, ano = ano % 2, ano // 2
n7, ano = ano % 2, ano // 2
n8, ano = ano % 2, ano // 2
n9, ano = ano % 2, ano // 2
n10, ano = ano % 2, ano // 2
u = [ano,n10,n9,n8,n7,n6,n5,n4,n3,n2,n1]
v = 0
for i in range(0,len(u),1):
 v = v + u[(len(u) - 1) - i] * (2 ** i)
 print(v, u)

# 1 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 3 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 3 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 3 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 3 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 35 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 99 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 227 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 483 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 995 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]
# 2019 [1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1]

# SUCESSO

# Exercício 12

# Exercício 13

def areaRet():
    x = input("Informe o valor do menor lado do retãngulo: ")
    x = float(x)
    y = input("Informe o valor do maior lado do retãngulo: ")
    y = float(y)
    area = x * y
    print   ( "A área desse retângulo é:", area )
    return ( "A área desse retângulo é:", area )
areaRet()








