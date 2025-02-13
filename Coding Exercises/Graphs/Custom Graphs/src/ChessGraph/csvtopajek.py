# -*- coding: utf-8 -*-
"""CsvToPajek.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1aXIJbzy7dszcfzV26a0O1KPR4qcUoLxq
"""

import pandas as pd

dataset = pd.read_csv('ChessWaL.csv', sep =";")

f = open("ChessWalOut.txt ", "x")
myset = dataset["source"].unique()
dic = {}
f.write("*Vertices " + str(len(myset)) + " \n")
for row in range(0, len(myset)):
    f.write(str(row) + " " + '"' + str(myset[row]) + '"' + " \n")
    dic[myset[row]] = row
f.write("\n")
f.write("*Arcs " + " \n")
for row in range(0, len(dataset["source"])):
    f.write(str(dic[dataset["source"][row]]) + " " + str(dic[dataset["target"][row]]) + " "  + str(dataset["weight"][row]) + " \n")

