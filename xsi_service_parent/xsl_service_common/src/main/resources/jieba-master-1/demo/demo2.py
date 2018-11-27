# encoding=utf-8
from __future__ import unicode_literals
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
sys.path.append("../")

import jieba
import jieba.posseg
import jieba.analyse


# 加载自定义词典
jieba.load_userdict("/home/ftp/www/images/jieba-master-1/demo/demodict1.txt")
jieba.load_userdict("/home/ftp/www/images/jieba-master-1/demo/demodict2.txt")
jieba.analyse.set_stop_words("/home/ftp/www/images/jieba-master-1/extra_dict/stop_words.txt")

text = text.decode()

words = jieba.posseg.cut(text)

for word, flag in words:
    print('%s %s' % (word, flag))

