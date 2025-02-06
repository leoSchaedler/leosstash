

def sensor_t_u():
    from random import randrange as rr
    return rr(20, 100)

def relay(estado):
    if estado == '1':
        print('Rele ligado...')
    else:
        print('Rele desligado')




