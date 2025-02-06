import math
import Pyro5.api
import signal
from collections import Counter

@Pyro5.api.expose
class Voter(object):
    def __init__(self, ve, to):
        self.vr = 0
        self.ve = ve
        self.to = to
        self.msgList = []
        signal.signal(signal.SIGALRM, self.alarmHandler)

    def most_frequent(self):
       cnt = Counter()
       for word in self.msgList:
            cnt[word] += 1
       majority = math.ceil((self.vr+1)/2)
       greater = cnt.most_common(1)[0][1]
       if greater >= majority and greater > 1:
            return cnt.most_common(1)[0][0]
       return 'Inconclusive' 
    
    def consensus(self):
        winner = self.most_frequent()
        print(winner)
        self.vr = 0
        self.msgList = []
        return winner

    def alarmHandler(self, signum, frame):
        return self.consensus()
    
    def send(self, msg):
        self.vr += 1
        self.msgList.append(msg)
        print(self.msgList)
        if self.vr == 1:
            signal.alarm(self.to)
        if self.vr == self.ve:
            signal.alarm(0) 
            self.alarmHandler(0,0)

if __name__ == '__main__':
    deamon = Pyro5.api.Daemon()
    Voter = Voter(3,10)
    uri = deamon.register(Voter)
    ns = Pyro5.api.locate_ns()
    ns.register("Voter",uri)
    print("Ready. Object uri =", uri)
    deamon.requestLoop()
