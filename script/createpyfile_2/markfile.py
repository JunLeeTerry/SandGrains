#!/usr/bin/env python

import time
import os

def mark(filename,name):
    date = time.strftime("%d/%m/%y")
    print name,filename,date
    markfile = file(filename,"a+")
    length = max(len(name),len(filename),len(date))*3/2
        
    marklist = ["#"*length,__forstr(length,name),__forstr(length,date),"#"*length]
    for i in marklist:
        markfile.write(i+"\n")
    
    markfile.close()
        
def __forstr(length,string):
    '''
    formate string
    make the string has same length as ### line
    '''
    #lenth of space
    spacelen = length - len(string) -3
    return "# "+string+" "*spacelen + "#"

filename = raw_input("filename:")
name = raw_input("name:")
mark(filename,name)

