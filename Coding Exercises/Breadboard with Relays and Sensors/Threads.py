
## Sistemas Cíber-Físicos


import time
from threading import Thread

Encerre = False

def minhaThreadLegal(pausa, msg):
    for i in range (10):
        time.sleep(pausa)
        print(msg)
        if Encerre == True:
            print("Estou encerrando!")
            return
        

t1 = Thread(target=minhaThreadLegal, args=(1, "Sou a Thread 1"))
t2 = Thread(target=minhaThreadLegal, args=(2, "Sou a Thread 2"))

t1.start()
t2.start()
while t1.isAlive() or t2.isAlive():
    print("Oi, sou o Programa Principal!...")  
    time.sleep(.3)
    Encerre = True
     

