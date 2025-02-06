

import turtle # Comando para importar a biblioteca

my_window = turtle.Screen()   # Comando para criar uma janela gráfica

my_window.bgcolor("light blue")  # Comando para mudar a cor do background da janela gráfica para azul claro.

my_pen = turtle.Turtle() # Comando para especificar o tipo da caneta como uma turtle

my_pen.pencolor("red") # Comando para especificar a cor da caneta para vermelho

my_pen.pensize(24) # Comando para especificar o tamanho da caneta para 24 unidades

for i in range(20): # Determina a quantidade de vezes que os comandos iram ser executados


   my_pen.forward(300) # Move a caneta para frente por 300 unidades

   my_pen.dot() # Marca um ponto

   my_pen.right(144) # Move a caneta para a direita por 144 graus


turtle.done() # Finaliza o desenho, porém não fecha o turtle