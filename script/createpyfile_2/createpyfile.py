#!/usr/bin/env python
import os 
import re
import markfile

def askmark(filename):
	ismark = raw_input("Do you want to mark the file(Y/N):")
	if ismark.lower()=="y":
		name = raw_input("Please input your name")
		markfile.mark(filename,name)
	elif ismark.lower()=="n":
		pass
	else:
		main()

def main():
	pi_type = raw_input("please enter your file type('m'ain or 'n'ormal)")
	if (pi_type.lower() == 'n' or pi_type.lower() == 'm'):
		filename = raw_input("please enter your file name:")
		p = re.compile('.py$')
		if p.search(filename):
			pifile = file(filename,"a+")
		else:
			filename = filename+".py"
			pifile = file(filename,"a+")

		pifile.write('#!/usr/bin/env python\n')
		pifile.seek(0)
		askmark(filename)
		if(pi_type == 'n'):
			pifile.write('def main():\n\
    pass\n\n\
if (__name__=="__main__"):\n\
    main()\n')
	else:
		print "type error"
		main()

if(__name__ == "__main__"):
	main()


