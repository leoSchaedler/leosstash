



import turtle # Comando para importar a biblioteca

my_window = turtle.Screen()   # Comando para criar uma janela gráfica

my_window.bgcolor("red")  # Comando para mudar a cor do background da janela gráfica para vermelho.

my_pen = turtle.Turtle() # Comando para especificar o tipo da caneta como uma turtle

my_pen.pensize(18)

for i in range(4): # Determina a quantidade de vezes que os comandos iram ser executados

    my_pen.forward(50) # Move a caneta para frente por 50 unidades

    my_pen.right(90) # Move a caneta para a direita por 90 graus


turtle.done() # Finaliza o desenho






