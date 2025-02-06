import pandas as pd
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import train_test_split
import pandas as pd
import numpy as np
from scipy.stats import stats

def computeKS(y_true, y_prob_positive):
    """
    Description:
        Kolmogorov-Smirnov value obtained from ground-truth targets (y_true) and
        their probabilities (y_prob_positive).
    Params:
        y_true (pd.Series): Ground-truth labels
        y_prob_positive (pd.Series): The probabilities of TARGET=1
    Output:
        ks (float): The KS rate
    """
    vals = list(zip(y_true, y_prob_positive))
    positives = []
    negatives = []
    for a, b in vals:
        if a == 0:
            negatives.append(b)
        else:
            positives.append(b)
    ks = 100.0 * stats.ks_2samp(positives, negatives)[0]
    return ks

# carregamento de dados
df = pd.read_csv('train.csv')
df_test = pd.read_csv('test.csv')
df = pd.get_dummies(df)

# selecao manual de atributos
cols_treino = ['TARGET', 'TEMPOCPF', 'SOMARENDACASA', 'ESTIMATIVARENDA', 'PERCENTBOLSAFAMILIACEP',
        'QTDDECLARACAO10', 'QTDDECLARACAOPAGAR10']
cols_teste = cols_treino.copy()
cols_teste.remove('TARGET')
df = df[cols_treino]
df_train = df
df_test = df_test[cols_teste]

# divisao da base de dados em treino, validacao e teste
# treino: dados usados para treinamento do classificador
# validacao: dados usados para estimar o KS do classificador em dados nao usados no treino
# teste: dados que nao temos informacao do rotulo/classe
X_train, y_train = df_train.drop(['TARGET'], axis=1), df_train['TARGET']
X_train, X_validation, y_train, y_validation = train_test_split(X_train, y_train, test_size=0.33,
                                                                shuffle=True, random_state=101, stratify=y_train)
X_test = df_test

# treino do modelo com dados de treinamento
lr = LogisticRegression(max_iter=10000)
lr.fit(X_train, y_train)

# aplicacao do modelo nos dados de validacao para estimativa de KS
print('Calculando o KS nos dados de validacao...')
prob_val = lr.predict_proba(X_validation)
prob_val = [x[1] for x in prob_val] #obtendo apenas classificacoes da classe 1 (default)

ks = computeKS(y_validation, prob_val)
print(f'KS = {ks}')

# depois da estimativa sobre os dados de validacao, treinamos o modelo usando treino e validacao
# a ideia aqui eh: mais dados, maior a probabilidade do modelo ter resultado bom
X_train, y_train = df_train.drop(['TARGET'], axis=1), df_train['TARGET']
lr.fit(X_train, y_train)

# aplicacao do modelo dos dados de teste
prob_test = lr.predict_proba(X_test)
prob_test = [x[1] for x in prob_test]

df_output_jpb = pd.DataFrame({'TEAM_JPB': prob_test})
df_output_jpb.to_csv('TEAM_JPB.csv', index=False)