
import turtle # Comando para importar a biblioteca

my_wn = turtle.Screen() # Comando para criar uma janela gráfica

my_wn.bgcolor("yellow") # Comando para mudar a cor do background da janela gráfica para amarelo

my_wn.title("Turtle") # Comando para mudar o nome da janela gráfica

my_pen = turtle.Turtle() # Comando para especificar o tipo da caneta como uma turtle

my_pen.color("black") # Comando para especificar a cor da caneta para preto

my_pen.pensize(14) # Comando para especificar o tamanho da caneta para 14 unidades

def my_sqrfunc(size): # Define uma função com parâmetro "size".

   for i in range(4): # Determina a quantidade de vezes que os comandos iram ser executados

      my_pen.fd(size) # Move a caneta para frente pelo quantidade de unidades passadas no parâmetro

      my_pen.left(90) # Move a caneta para a direita por 90 graus

      # size = size # Atribui o valor de size para size

my_sqrfunc(240)
my_sqrfunc(220)
my_sqrfunc(200)
my_sqrfunc(180)
my_sqrfunc(160)
my_sqrfunc(140)                 #### CHAMAM A FUNÇÃO COM PARÂMETRO DECRESCENTES EM 20 UNIDADES####
my_sqrfunc(120)
my_sqrfunc(100)
my_sqrfunc(80)
my_sqrfunc(60)
my_sqrfunc(40)
my_sqrfunc(20)

turtle.done() # Finaliza o desenho, porém não fecha o turtle










