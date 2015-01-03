#!/usr/bin/env python

import time
import os
def main():
    filename = raw_input("please input your filename:")
    name = "autor:"+raw_input("please input your name:")
    description = raw_input("please input your description:")
    date = time.strftime("%d/%m/%y")
    print name,filename,date
    markfile = file(filename,"a+")
    length = max(len(name),len(filename),len(date))*3/2
    
    marklist = ["#"*length,forstr(length,name),forstr(length,date),"#"*length]
    for i in marklist:
        markfile.write(i+"\n")
        
    markfile.close()
        
#formatstring,make the string has same length as ### line
def forstr(length,string):
    #lenth of space
    spacelen = length - len(string) -3
    return "# "+string+" "*spacelen + "#"

if (__name__ == "__main__"):
    main()
