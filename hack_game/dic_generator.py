#!/usr/bin/env python

import itertools

'''#<----------------------------------
list = []
letters = [chr(i) for i in range(32,127)]
for j in letters:
    list.append(j)
letter = "".join(list) 
#----------------------------------->
'''
letter = str(raw_input("S >>>"))
length = int(raw_input("L >>>"))

res = itertools.product(letter,repeat=length)

file = file("password.txt","a+")

for i in res:
    file.write(''.join(i)+"\n")
    
