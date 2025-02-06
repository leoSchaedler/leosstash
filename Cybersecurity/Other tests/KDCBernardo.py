import random

def cesar(t,k):
    try:
        C = list(t) # transforma toda uma string em lista
        z = []
        for i in range(0,len(C)):
            z.append(chr(ord(C[i]) + k)) ## ORD vái ate o padrão ASCII e o CHR volta para o normal
        return (''.join(z)) ##concatena toda a lista novamente
    except:
        return t + k
def decesar(t,k):
    try:
        C = list(t) # transforma toda uma string em lista
        z = []
        for i in range(0,len(C)):
            z.append(chr(ord(C[i]) - k)) ## ORD vái ate o padrão ASCII e o CHR volta para o normal
        return (''.join(z)) ##concatena toda a lista novamente
    except:
        return t-k
def random(infra,supra):
    return random.randint(infra,supra)

class User:
    def __init__(self, name, ip, key, nonce):
        self.name = str(name)
        self.ip = int(ip)
        self.key = int(key)
        self.nonce = int(nonce)
class KDC:
    def __init__(self, k_bob,k_alice, ipB, ipA, k_kdc):
        self.k_bob = bob.key
        self.k_alice = alice.key
        self.k_kdc = k_kdc
        self.ipB = bob.ip
        self.ipA = alice.ip

bob = User("Bob", 12091970, 5, 3103)
alice = User("Alice", 4111967, 8, 2001)
kdc = KDC(bob.key, alice.key, bob.ip, alice.ip, 22)

def newChat(user,who,ip):
    if str(user) == bob.name:
        if decesar(ip,kdc.k_bob) == kdc.ipB:
            k0 = kdc.k_bob
            k1 = kdc.k_alice
        else:
            return "ERROR TYPE 01"
            exit()
        if decesar(who,kdc.k_bob) == alice.name:
            z = [cesar(kdc.k_kdc,k0),cesar(kdc.k_kdc,k1)]
            return z
        else:
            return "ERROR TYPE 02"
            exit()
    elif str(user) == alice.name:
        if decesar(ip,kdc.k_alice) == kdc.ipA:
            k0 = kdc.k_alice
            k1 = kdc.k_bob
        else:
            return "ERROR TYPE 03"
            exit()
        if decesar(who,kdc.k_alice) == bob.name:
            z = [cesar(kdc.k_kdc,k0),cesar(kdc.k_kdc,k1)]
            return z
    else:
        return "ERROR ERROR 10"
        exit()

newChat_bob = newChat("Bob",cesar("Alice",bob.key),cesar(bob.ip,bob.key))
newChat_alice = newChat("Alice",cesar("Bob",alice.key),cesar(alice.ip,alice.key))

print(newChat_bob)
print(newChat_alice)

def funcDeAutentifiacao(n): return n*2

def confirmationBob(nonce):
    if decesar(nonce,kdc.k_kdc)-kdc.k_kdc == funcDeAutentifiacao(bob.nonce):
        return "Bob: Nossa conversa está segura, Alice."
    else:
        return "ERROR"
        exit()

non = cesar(bob.nonce,kdc.k_kdc) #Bob esta gerando e cifrando o Nonce e envia para Alice

newnon = funcDeAutentifiacao(non) #Alice recebe, descriptografa e aplica o Nonce a função

chat = confirmationBob(newnon) # Bob recebe o nonce e confere
print(chat)