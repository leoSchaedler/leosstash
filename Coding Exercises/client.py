import Pyro5.api, Pyro5.errors
import sys

if __name__ == '__main__':
    eco = Pyro5.api.Proxy("Pyroname:" + 'Voter')

    try:
        e = eco.send(str(sys.argv[1]))
    except Pyro5.errors.CommunicationError:
        print("Unavailable")