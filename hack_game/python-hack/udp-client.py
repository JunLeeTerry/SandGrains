#!/usr/bin/env python

import socket

serverhost = "127.0.0.1"
serverport = 80

udpclient = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
udpclient.sendto("AASAABBBBAA",(serverhost,serverport))

data,addr = udpclient.recvfrom(4096)

print data
