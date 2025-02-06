import turtle
tartaruga = turtle.Turtle()
tartaruga.speed(20)
for i in range(80):
    tartaruga.circle(2*i)
    tartaruga.circle(-2*i)
    tartaruga.left(i)
turtle.done()