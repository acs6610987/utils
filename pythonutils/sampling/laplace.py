'''
Created on May 15, 2016

@author: zhihuang
'''
import numpy
import matplotlib.pyplot as plt

def laplace(mu, b):
    x = numpy.random.laplace(mu, b, 10000)

def uniformToLaplace(mu, b):
    u = -numpy.random.uniform(low = -0.5, high = 0.5, size=10000)
    signs = numpy.ones(10000)
    signs[u < 0] = -1
    x = mu - b*signs*numpy.log(1 - 2*numpy.abs(u))

    return x

def uniformTaylorToLaplace(mu, b):
    k = 100
    u = -numpy.random.uniform(low = -0.5, high = 0.5, size=10000)
    signs = numpy.ones(10000)
    signs[u < 0] = -1
    u = 2*numpy.abs(u)
    taylorU = numpy.zeros(len(u))
    for i in range(1, k+1):
        taylorU += -1.0 / i * numpy.power(u, i)
    x = mu - b*signs*taylorU
    return x

def additionOfTwoLaplace(mu, b):
    x = numpy.random.laplace(mu, b, 10000)
    y = numpy.random.laplace(mu, b, 10000)
    return x + y

def plot(x):
    minX = min(x)
    maxX = max(x)
    binNum = 1000
    binSize = (maxX - minX) / binNum
    sampleInd = (x - minX) / binSize
    binSamples = numpy.zeros(binNum+1)
    for i in range(len(sampleInd)):
        binSamples[int(sampleInd[i])] += 1
    
    figure = plt.figure()
    ax = figure.add_subplot(1, 1, 1)
    ax.bar(range(binNum+1), binSamples, width = 1)
    
    plt.show()
    
if __name__ == "__main__":
    x = uniformToLaplace(0, 1)
#     x = uniformTaylorToLaplace(0, 1)
#     x = additionOfTwoLaplace(0, 1)
    plot(x)