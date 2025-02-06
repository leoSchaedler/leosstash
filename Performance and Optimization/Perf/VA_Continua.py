import numpy as np
import matplotlib.pyplot as plt
import scipy.stats as st

def plotUnifPDF(a, b):
    fig, ax = plt.subplots(1, 1)
    x = np.arange(st.uniform.ppf(0.01, a, b), st.uniform.ppf(0.99, a, b), (b)/1000)
    # print(x)
    ax.plot(x, st.uniform.pdf(x, a, b), 'bo', ms=1,
            label='uniforme pdf \nlinicio = {0:.2f} desloc = {1:.2f}'.format(a, b))
    ax.vlines([a, a+b], 0, st.uniform.pdf(x, a, b), colors='k', linestyle='dashed', lw=1, alpha=0.5)
    ax.legend(loc='best', frameon=False)
    plt.show()

def plotUnifCDF(a, b):
    fig, ax = plt.subplots(1, 1)
    x = np.arange(st.uniform.ppf(0.01, a, b), st.uniform.ppf(0.99, a, b), (b)/1000)
    # print(x)
    ax.plot(x, st.uniform.cdf(x, a, b), 'bo', ms=1,
            label='uniforme cdf \nlinicio = {0:.2f} desloc = {1:.2f}'.format(a, b))
    ax.legend(loc='best', frameon=False)
    plt.show()


def plotUnifRVS(ini,desl):
    fig, ax = plt.subplots(1, 1)
    a = st.uniform.rvs(ini, desl, size=10000)
    x = np.arange(ini, ini+desl, 1 / 100)
    ax.plot(x, st.uniform.pdf(x, ini, desl), 'b', ms=1,
        label='uniform pdf \ninicio = {0:.2f} \ndesloc = {1:.2f}'.format(ini,desl))
    ax.hist(a, bins=10, density=True, histtype='stepfilled', label='dados')
    # ax.legend(loc='best', frameon=False)
    ax.legend(loc='center left', bbox_to_anchor=(1, 0.5))
    plt.show()


#### Exponencial
def plotExpPDF(inic,mu):
    fig, ax = plt.subplots(1, 1)
    x = np.arange(inic, st.expon.ppf(0.999, inic, mu), 1/100)
    ax.plot(x, st.expon.pdf(x, inic, mu), 'bo', ms=1,
        label='exponencial pdf \ninic = {0:.2f} \nmu = {1:.2f}'.format(inic,mu))
    ax.legend(loc='best', frameon=False)
    plt.show()

def plotExpCDF(inic,mu):
    fig, ax = plt.subplots(1, 1)
    x = np.arange(inic, st.expon.ppf(0.999, inic, mu), 1/100)
    ax.plot(x, st.expon.cdf(x, inic, mu), 'bo', ms=1,
        label='exponencial pdf \ninic = {0:.2f} \nmu = {1:.2f}'.format(inic,mu))
    ax.legend(loc='best', frameon=False)
    plt.show()

def plotExpRVS(inic,mu):
    fig, ax = plt.subplots(1, 1)
    a = st.expon.rvs(inic, mu, size=10000)
    x = np.arange(inic, st.expon.ppf(0.9999, inic, mu), 1 / 100)
    ax.plot(x, st.expon.pdf(x, inic, mu), 'b', ms=1,
        label='exponencial pdf \ninic = {0:.2f} \nmu = {1:.2f}'.format(inic,mu))
    ax.hist(a, bins=20, density=True, histtype='stepfilled', label='dados')
    ax.legend(loc='best', frameon=False)
    plt.show()

#### Normal
def plotNormPDF(mu, sigma):
    fig, ax = plt.subplots(1, 1)
    x = np.arange(st.norm.ppf(0.0001, mu, sigma), st.norm.ppf(0.9999, mu, sigma), 1/100)
    ax.plot(x, st.norm.pdf(x, mu, sigma), 'bo', ms=1,
            label='normal pdf \nmu = {0:.2f} sigma = {1:.2f}'.format(mu, sigma))
    ax.legend(loc='best', frameon=False)
    plt.show()

def plotNormCDF(mu, sigma):
    fig, ax = plt.subplots(1, 1)
    x = np.arange(st.norm.ppf(0.0001, mu, sigma), st.norm.ppf(0.9999, mu, sigma), 1/100)
    ax.plot(x, st.norm.cdf(x, mu, sigma), 'bo', ms=1,
            label='normal cdf \nmu = {0:.2f} sigma = {1:.2f}'.format(mu, sigma))
    ax.legend(loc='best', frameon=False)
    plt.show()

def plotNormRVS(mu, sigma):
    fig, ax = plt.subplots(1, 1)
    a = st.norm.rvs(mu, sigma, size=10000)
    x = np.arange(st.norm.ppf(0.0001, mu, sigma), st.norm.ppf(0.9999, mu, sigma), 1 / 100)
    ax.plot(x, st.norm.pdf(x, mu, sigma), 'b', ms=1,
        label='normal pdf \nmu = {0:.2f} \nsigma = {1:.2f}'.format(mu, sigma))
    ax.hist(a, bins=20, density=True, histtype='stepfilled', label='dados')
    ax.legend(loc='best', frameon=False)

