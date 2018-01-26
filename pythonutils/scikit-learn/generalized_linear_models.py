'''
Created on Sep 13, 2016

@author: zhihuang
'''

#===============================================================================
# Ordinary least square
#===============================================================================
# import matplotlib.pyplot as plt
# import numpy as np
# from sklearn import datasets, linear_model
# 
# diabetes = datasets.load_diabetes()
# 
# diabetes_X = diabetes.data[:, 2:3]
# 
# diabetes_X_train = diabetes_X[:-20]
# diabetes_X_test = diabetes_X[-20:]
# 
# diabetes_y_train = diabetes.target[:-20]
# diabetes_y_test = diabetes.target[-20:]
# 
# regr = linear_model.LinearRegression()
# regr.fit(diabetes_X_train, diabetes_y_train)
# 
# print 'Coefficients: ', regr.coef_
# print 'Intercept: ', regr.intercept_
# print 'Residual sum of squares: %.2f' % np.mean((regr.predict(diabetes_X_test) - diabetes_y_test) ** 2)
# print 'Variance score: %.2f' % regr.score(diabetes_X_test, diabetes_y_test)
# 
# plt.scatter(diabetes_X_test, diabetes_y_test, color='black')
# plt.plot(diabetes_X_test, regr.predict(diabetes_X_test), color='blue', linewidth=3)
# plt.xticks(())
# plt.yticks(())
# plt.show()

#===============================================================================
# Ridge Regression
#===============================================================================
# # Plot ridge coefficients as a function of the regularization
# import numpy as np
# import matplotlib.pyplot as plt
# from sklearn import linear_model
# 
# X = 1. / (np.arange(1, 11) + np.arange(0, 10)[:, np.newaxis])
# y = np.ones(10)
#  
# n_alphas = 200
# alphas = np.logspace(-10, -2, n_alphas)
# clf = linear_model.Ridge(fit_intercept=False)
#  
# coefs = []
# for a in alphas:
#     clf.set_params(alpha = a)
#     clf.fit(X, y)
#     coefs.append(clf.coef_)
#      
# print coefs
#  
# ax = plt.gca()
# ax.set_color_cycle(['b', 'r', 'c', 'k', 'g', 'y', 'm'])
# ax.plot(alphas, coefs)
# ax.set_xscale('log')
# ax.set_xlim(ax.get_xlim()[::-1])
# plt.xlabel('alpha')
# plt.ylabel('weights')
# plt.title('Ridge coefficients as a function of the regularization')
# plt.axis('tight')
# plt.show()

# Classification of text documents using sparse features
import logging
import numpy as np
from optparse import OptionParser
import sys
from time import time
import matplotlib.pyplot as plt

from sklearn.datasets import fetch_20newsgroups
from sklearn.feature_extraction.text import TfidVectorizer
from sklearn.feature_extraction.text import HashingVectorizer
from sklearn.feature_selection import SelectKBest, chi2

logging.basicConfig(level = logging.INFO,
                    format = '%(asctime)s %(levelname)s %(message)s')

op = OptionParser()
op.add_option("--report",
              action="store_true", dest="print_report",
              help="Print a detailed classification report.")
op.add_option("--chi2_select",
              action="store", type="int", dest="select_chi2",
              help="Select some number of features using a chi-squared test")
op.add_option("--confusion_matrix",
              action="store_true", dest="print_cm",
              help="Print the confusion matrix")
op.add_option("--top10",
              action="store_true", dest="print_top10",
              help="Print ten most discriminative terms per class for every classifier")
op.add_option("--all_categories",
              action="store_true", dest="all_categories",
              help="Whether to use all categories or not.")