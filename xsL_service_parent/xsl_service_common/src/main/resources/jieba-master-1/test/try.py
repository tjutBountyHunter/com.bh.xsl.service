#coding:utf-8
#encoding=utf-8
from __future__ import unicode_literals
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
sys.path.append("../")


import jieba
import jieba.posseg
import jieba.analyse

website_string = haha.decode()
print website_string


words = jieba.posseg.cut(website_string)
for word, flag in words:
    print('%s %s' % (word, flag))