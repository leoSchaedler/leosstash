# Este exemplo carrega a base Wine da UCI, e avalia dois KNNs
# Um usando holdout e outro usando validação cruzada com 10 pastas. 

# Importa bibliotecas necessárias 
import numpy as np
import urllib
from sklearn.neighbors import KNeighborsClassifier
from sklearn import  cross_validation
from sklearn.metrics import confusion_matrix
from sklearn.cross_validation import train_test_split
#from sklearn.cross_validation import StratifiedShuffleSplit
# Carrega uma base de dados do UCI
# Exemplo carrega a base Wine
url = "https://archive.ics.uci.edu/ml/machine-learning-databases/wine/wine.data"
raw_data = urllib.urlopen(url)

# Carrega arquivo como uma matriz
dataset = np.loadtxt(raw_data, delimiter=",")

# Imprime quantide de instâncias e atributos da base
print(dataset.shape)

# Coloca em X os 13 atributos de entrada e em y as classes
# Observe que na base Wine a classe é primeiro atributo 
X = dataset[:,1:13]
y = dataset[:,0]

# EXEMPLO USANDO HOLDOUT
# Holdout -> dividindo a base em treinamento (70%) e teste (30%), estratificada
X_train, X_test, y_train, y_test = train_test_split(X,y,test_size=.3, random_state=42, stratify=y)

# declara o classificador
clfa = KNeighborsClassifier(n_neighbors=3)

# treina o classificador
clfa = clfa.fit(X_train, y_train)

# testa usando a base de testes
predicted=clfa.predict(X_test)

# calcula a acurácia na base de teste
score=clfa.score(X_test, y_test)

# calcula a matriz de confusão
matrix = confusion_matrix(y_test, predicted)

# apresenta os resultados
print("Accuracy = %.2f " % score)
print("Confusion Matrix:")
print(matrix)

# EXEMPLO USANDO VALIDAÇÃO CRUZADA
clfb = KNeighborsClassifier(n_neighbors=3)
folds=10
result = cross_val_score(clfb, X, y, cv=folds)
print("\nCross Validation Results %d folds:" % folds)
print("Mean Accuracy: %.2f" % result.mean())
print("Mean Std: %.2f" % result.std())

# matriz de confusão da validação cruzada
Z = cross_validation.cross_val_predict(clfb, X, y, cv=folds)
cm=confusion_matrix(y, Z)
print("Confusion Matrix:")
print(cm)

