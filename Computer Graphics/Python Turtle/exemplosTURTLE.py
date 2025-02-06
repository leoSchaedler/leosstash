
import os

import turtle

elsa = turtle.Turtle()
turtle.Screen().bgcolor("blue")
elsa.color("cyan")
for i in range(2):
    for i in range(2):
        elsa.forward(100)
        elsa.left(60)
        elsa.forward(100)
        elsa.left(60)
    elsa.right(60)
os.system("pause")
elsa.clear()

import random

turtle.Screen().bgcolor("cyan")
elsa = turtle.Turtle()
for n in range(3):
    elsa.penup()
    elsa.goto(random.randint(-400, 400), random.randint(-400, 400))
    elsa.pendown()

    red_amount   = random.randint( 0,  30) / 100.0
    green_amount = random.randint( 0,  30) / 100.0
    blue_amount  = random.randint(50, 100) / 100.0
    elsa.pencolor((red_amount, green_amount, blue_amount))

    circle_size = random.randint(10, 40)
    elsa.pensize(random.randint(1, 5))

    for i in range(3):
        elsa.circle(circle_size)
        elsa.left(120)

os.system("pause")

elsa.clear()
turtle.Screen().bgcolor("white")
elsa = turtle.Turtle()
for angle in range(0, 360, 15):
    elsa.setheading(angle)
    elsa.forward(100)
    elsa.write(str(angle) + '°')
    elsa.backward(100)

os.system("pause")
elsa.clear()

elsa = turtle.Turtle()
for i in range(10):
    elsa.forward(100)
    elsa.left(90)
    elsa.forward(10)
    elsa.left(90)
    elsa.forward(100)
    elsa.right(90)
    elsa.forward(10)
    elsa.right(90)

os.system("pause")

for i in range(10):
    elsa.undo()

os.system("pause")
elsa.clear()

elsa = turtle.Turtle()
elsa.penup()

for i in range(30, 0, -1):
    elsa.write(str(i))
    elsa.stamp()
    elsa.left(i)
    elsa.forward(20)

elsa.fillcolor('red')
for i in range(0, 30, 1):
    elsa.write(str(i))
    elsa.stamp()
    elsa.left(i)
    elsa.forward(20)

os.system("pause")



