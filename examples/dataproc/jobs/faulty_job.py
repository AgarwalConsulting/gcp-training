#!/usr/bin/python

import pyspark

sc = pyspark.SparkContext()
rdd = sc.parallelize(['Hello,', 'world!'])
words = sorted(rdd.collect())
raise RuntimeError()
print(words)
