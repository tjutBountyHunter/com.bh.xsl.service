# encoding=utf-8
from __future__ import unicode_literals
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
sys.path.append("../")

import jieba
import jieba.posseg
import jieba.analyse

# 提取出关键词的数量
topN = 3

# 过滤关键词词性
allowKey = ('an', 'l', 'n','nz', 'vn', 'v')

# 加载自定义词典
jieba.load_userdict("/home/ftp/www/images/jieba-master-1/demo/demodict1.txt")
jieba.load_userdict("/home/ftp/www/images/jieba-master-1/demo/demodict2.txt")

jieba.analyse.set_stop_words("/home/ftp/www/images/jieba-master-1/extra_dict/stop_words.txt")

text = '悬赏百度云电影三元'

print('='*40)
print('1. 关键词提取')
print('-'*40)
print(' TextRank')
print('-'*40)

for x, w in jieba.analyse.textrank(text, withWeight=True, allowPOS=allowKey):
    print('%s %s' % (x, w))

print('='*40)
print('2. 词性标注')
print('-'*40)
words = jieba.posseg.cut(text)

for word, flag in words:
    print('%s %s' % (word, flag))

print('-'*40)

words = jieba.posseg.cut(text)
allowKey = ('an', 'l', 'n','nz','ns')
count = 0
for word, flag in words:
    if count < topN:
        if flag in allowKey:
            print('%s %s' % (word, flag))
            count = count+1
    else:
        break
