from collections import OrderedDict

# lexico de palavras
lexico = ["abacate", "abacaxi", "abobora", "abobrinha", "ananás", "maça", "mamão", "manga", "melancia", "melão", "mexerica", "morango"]

input_string = input("Digite uma palavra: ")
input_string = input_string.lower()
n = 2

ngram_input = []

for i in range (0,len(input_string)-(n-1)):
		gram =  input_string[i:i+n]
		ngram_input.append(gram)

lista_input = list(OrderedDict.fromkeys(ngram_input))
A = len(lista_input)
print("Input: {}". format(input_string))
print("Input n-gram: {}". format(lista_input))
print("A: {}". format(A))
print("")

s_list = []
for j in range (0, len(lexico)):
	str_lexico = lexico[j]
	ngram_lexico = []
	for i in range (0,len(str_lexico)-(n-1)):
		gram =  str_lexico[i:i+n]
		ngram_lexico.append(gram)
	lista_lexico = list(OrderedDict.fromkeys(ngram_lexico))
	B = len(lista_lexico)
	inter = set(lista_input).intersection(lista_lexico)
	C = len(inter)
	print("{} B: {} C: {}". format(lista_lexico,B,C))
	S = 2*C/(A+B)
	s_list.append(S)
	print("S = {}". format(round(S,2)))
	print("")

maximo = max(s_list)
index = s_list.index(maximo)
if(maximo > 0.65):
	print("Maior similaridade encontrada: {} com {}%".format(lexico[index], round(s_list[index]*100),2))
else:
	print("Nenhuma palavra com grau de similaridade significativo foi encontrada no lexico")

print("")
