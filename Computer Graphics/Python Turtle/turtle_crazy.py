
import turtle # Comando para importar a biblioteca

my_window = turtle.Screen()   # Comando para criar uma janela gráfica

my_window.bgcolor("black")  # Comando para mudar a cor do background da janela gráfica para preto.

colors = ["yellow", "black"] # Define um vetor com dois valores de cores.

my_pen = turtle.Pen() # Comando para especificar o tipo da caneta como uma turtle


my_pen.speed(8000) # Determina a velocidade de execução dos comandos para a caneta.

for x in range(360): # Determina a quantidade de vezes que os comandos iram ser executados

   my_pen.pencolor(colors[x % 2]) # Divide o esquema de cor pelo vetor criado acima, que contém duas cores,
   # isso significa que cada vez que o comando for executado a caneta vai ter uma das duas cores, intercalando.

   my_pen.width(x/8 + 1) # Determina a espessura da caneta pra "x/8 +1" sendo x um número dinâmico de 0 até 360.

   my_pen.forward(x) # Move a caneta para frente por x unidades.

   my_pen.right(59) # Move a caneta para a direita por 59 graus, tornando o hexágono levemente modificado.



turtle.done() # Finaliza o desenho, porém não fecha o turtle

