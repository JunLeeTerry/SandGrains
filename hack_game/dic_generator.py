#!/usr/bin/env python

lower = int(raw_input("length lower limit:"))
upper = int(raw_input("length upper limit:"))

def returnChar():
    for i in range(32,127):
        return chr(int(i))
        
print str(returnChar())*3



    
        
    
